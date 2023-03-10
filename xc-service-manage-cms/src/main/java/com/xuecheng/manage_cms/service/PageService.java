package com.xuecheng.manage_cms.service;

import com.alibaba.fastjson.JSON;
import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSDownloadStream;
import com.mongodb.client.gridfs.model.GridFSFile;
import com.xuecheng.framework.domain.cms.CmsConfig;
import com.xuecheng.framework.domain.cms.CmsPage;
import com.xuecheng.framework.domain.cms.CmsSite;
import com.xuecheng.framework.domain.cms.CmsTemplate;
import com.xuecheng.framework.domain.cms.request.QueryPageRequest;
import com.xuecheng.framework.domain.cms.response.CmsCode;
import com.xuecheng.framework.exception.ExceptionCast;
import com.xuecheng.framework.model.response.CommonCode;
import com.xuecheng.framework.model.response.QueryResult;
import com.xuecheng.manage_cms.config.RabbitmqConfig;
import com.xuecheng.manage_cms.dao.CmsConfigRepository;
import com.xuecheng.manage_cms.dao.CmsPageRepository;
import com.xuecheng.manage_cms.dao.CmsSiteRepository;
import com.xuecheng.manage_cms.dao.CmsTemplateRepository;
import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.bson.types.ObjectId;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @author Administrator
 * @version 1.0
 * @create 2018-09-12 18:32
 **/
@Service
public class PageService {

    @Autowired
    CmsPageRepository cmsPageRepository;

    @Autowired
    CmsConfigRepository cmsConfigRepository;

    @Autowired
    CmsSiteRepository cmsSiteRepository;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    CmsTemplateRepository cmsTemplateRepository;

    @Autowired
    GridFsTemplate gridFsTemplate;

    @Autowired
    GridFSBucket gridFSBucket;

    @Autowired
    RabbitTemplate rabbitTemplate;

    /**
     * ??????????????????
     *
     * @param page             ????????????1????????????
     * @param size             ???????????????
     * @param queryPageRequest ????????????
     * @return
     */
    public QueryResult<CmsPage> findList(int page, int size, QueryPageRequest queryPageRequest) {
        if (queryPageRequest == null) {
            queryPageRequest = new QueryPageRequest();
        }
        //?????????????????????
        //?????????????????????
        ExampleMatcher exampleMatcher = ExampleMatcher.matching()
                .withMatcher("pageAliase", ExampleMatcher.GenericPropertyMatchers.contains());
        //???????????????
        CmsPage cmsPage = new CmsPage();
        //????????????????????????id???
        if (StringUtils.isNotEmpty(queryPageRequest.getSiteId())) {
            cmsPage.setSiteId(queryPageRequest.getSiteId());
        }
        //????????????id??????????????????
        if (StringUtils.isNotEmpty(queryPageRequest.getTemplateId())) {
            cmsPage.setTemplateId(queryPageRequest.getTemplateId());
        }
        //????????????????????????????????????
        if (StringUtils.isNotEmpty(queryPageRequest.getPageAliase())) {
            cmsPage.setPageAliase(queryPageRequest.getPageAliase());
        }
        //??????????????????Example
        Example<CmsPage> example = Example.of(cmsPage, exampleMatcher);
        //????????????
        if (page <= 0) {
            page = 1;
        }
        page = page - 1;
        if (size <= 0) {
            size = 10;
        }
        Pageable pageable = PageRequest.of(page, size);
        Page<CmsPage> all = cmsPageRepository.findAll(example, pageable);//?????????????????????????????????????????????
        QueryResult<CmsPage> queryResult = new QueryResult();
        queryResult.setList(all.getContent());//????????????
        queryResult.setTotal(all.getTotalElements());//??????????????????
        return queryResult;
    }

    //????????????
    public CmsPage add(CmsPage cmsPage) {

        //???????????????????????????Id?????????webpath????????????
        //???????????????????????????Id?????????webpath???cms_page????????????????????????????????????????????????????????????????????????????????????
        CmsPage cmsPage1 = cmsPageRepository.findByPageNameAndSiteIdAndPageWebPath(cmsPage.getPageName(), cmsPage.getSiteId(), cmsPage.getPageWebPath());
        if (cmsPage1 != null) {
            //??????????????????
            //???????????????????????????????????????????????????
            ExceptionCast.cast(CmsCode.CMS_ADDPAGE_EXISTSNAME);
        }

        //??????dao????????????
        cmsPage.setPageId(null);
        cmsPageRepository.save(cmsPage);
        return cmsPage;

    }

    public CmsPage getById(String id) {
        Optional<CmsPage> optional = cmsPageRepository.findById(id);
        return optional.orElse(null);
    }

    public void update(String id, CmsPage cmsPage) {

        cmsPage.setPageId(id);
        cmsPageRepository.save(cmsPage);
    }

    public void delete(String id) {
        cmsPageRepository.deleteById(id);
    }

    public CmsConfig getConfigById(String id) {
        Optional<CmsConfig> optional = cmsConfigRepository.findById(id);
        return optional.orElse(null);
    }


    /**
     * ??????????????????????????????DataUrl
     * <p>
     * ???????????????????????????DataUrl?????????????????????
     * <p>
     * ??????????????????????????????????????????
     * <p>
     * ?????????????????????
     */
    public String getPageHtml(String pageId) {

        //??????????????????
        Map model = getModelByPageId(pageId);
        if (model == null) {
            //????????????????????????
            ExceptionCast.cast(CmsCode.CMS_GENERATEHTML_DATAISNULL);
        }

        //???????????????????????????
        String template = getTemplateByPageId(pageId);
        if (StringUtils.isEmpty(template)) {
            ExceptionCast.cast(CmsCode.CMS_GENERATEHTML_TEMPLATEISNULL);
        }

        //???????????????
        return generateHtml(template, model);

    }

    //???????????????
    private String generateHtml(String templateContent, Map model) {
        //??????????????????
        Configuration configuration = new Configuration(Configuration.getVersion());
        //?????????????????????
        StringTemplateLoader stringTemplateLoader = new StringTemplateLoader();
        stringTemplateLoader.putTemplate("template", templateContent);
        //???configuration?????????????????????
        configuration.setTemplateLoader(stringTemplateLoader);
        //????????????
        try {
            Template template = configuration.getTemplate("template");
            //??????api???????????????
            return FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    //???????????????????????????
    private String getTemplateByPageId(String pageId) {
        //?????????????????????
        CmsPage cmsPage = this.getById(pageId);
        if (cmsPage == null) {
            //???????????????
            ExceptionCast.cast(CmsCode.CMS_PAGE_NOTEXISTS);
        }
        //?????????????????????id
        String templateId = cmsPage.getTemplateId();
        if (StringUtils.isEmpty(templateId)) {
            ExceptionCast.cast(CmsCode.CMS_GENERATEHTML_TEMPLATEISNULL);
        }
        //??????????????????
        Optional<CmsTemplate> optional = cmsTemplateRepository.findById(templateId);
        if (optional.isPresent()) {
            CmsTemplate cmsTemplate = optional.get();
            //??????????????????id
            String templateFileId = cmsTemplate.getTemplateFileId();
            //???GridFS????????????????????????
            //????????????id????????????
            GridFSFile gridFSFile = gridFsTemplate.findOne(Query.query(Criteria.where("_id").is(templateFileId)));

            //???????????????????????????
            GridFSDownloadStream gridFSDownloadStream = gridFSBucket.openDownloadStream(gridFSFile.getObjectId());
            //??????GridFsResource??????????????????
            GridFsResource gridFsResource = new GridFsResource(gridFSFile, gridFSDownloadStream);
            //??????????????????
            try {
                String content = IOUtils.toString(gridFsResource.getInputStream(), "utf-8");
                return content;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return null;

    }

    //??????????????????
    private Map getModelByPageId(String pageId) {
        //?????????????????????
        CmsPage cmsPage = this.getById(pageId);
        if (cmsPage == null) {
            //???????????????
            ExceptionCast.cast(CmsCode.CMS_PAGE_NOTEXISTS);
        }
        //???????????????dataUrl
        String dataUrl = cmsPage.getDataUrl();
        if (StringUtils.isEmpty(dataUrl)) {
            //??????dataUrl??????
            ExceptionCast.cast(CmsCode.CMS_GENERATEHTML_DATAURLISNULL);
        }
        //??????restTemplate??????dataUrl????????????
        ResponseEntity<Map> forEntity = restTemplate.getForEntity(dataUrl, Map.class);
        return forEntity.getBody();

    }

    //????????????
    public void post(String pageId) {
        //?????????????????????
        String pageHtml = this.getPageHtml(pageId);
        //?????????????????????????????????GridFs???
        CmsPage cmsPage = saveHtml(pageId, pageHtml);
        //???MQ?????????
        sendPostPage(pageId);
    }

    //???mq ????????????
    private void sendPostPage(String pageId) {
        //??????????????????
        CmsPage cmsPage = this.getById(pageId);
        if (cmsPage == null) {
            ExceptionCast.cast(CommonCode.INVALID_PARAM);
        }
        //??????????????????
        Map<String, String> msg = new HashMap<>();
        msg.put("pageId", pageId);
        //??????json???
        String jsonString = JSON.toJSONString(msg);
        //?????????mq
        //??????id
        String siteId = cmsPage.getSiteId();
        rabbitTemplate.convertAndSend(RabbitmqConfig.EX_ROUTING_CMS_POSTPAGE, siteId, jsonString);
    }

    //??????html???GridFS
    private CmsPage saveHtml(String pageId, String htmlContent) {
        //?????????????????????
        CmsPage cmsPage = this.getById(pageId);
        if (cmsPage == null) {
            ExceptionCast.cast(CommonCode.INVALID_PARAM);
        }
        ObjectId objectId = null;
        //???htmlContent?????????????????????
        InputStream inputStream = IOUtils.toInputStream(htmlContent, "utf-8");
        //???html?????????????????????GridFS
        objectId = gridFsTemplate.store(inputStream, cmsPage.getPageName());

        //???html??????id?????????cmsPage???
        cmsPage.setHtmlFileId(objectId.toHexString());
        cmsPageRepository.save(cmsPage);
        return cmsPage;
    }

    //?????????????????????????????????????????????
    public CmsPage save(CmsPage cmsPage) {
        //????????????????????????
        return cmsPageRepository.save(cmsPage);

    }

    //??????????????????
    public String postPageQuick(CmsPage cmsPage) {

        //????????????????????????cms_page ?????????
        CmsPage cmsPageSave = this.save(cmsPage);

        //???????????????id
        String pageId = cmsPageSave.getPageId();

        //??????????????????????????????????????????GridFS??????MQ???????????????
        this.post(pageId);

        //????????????Url= cmsSite.siteDomain+cmsSite.siteWebPath+ cmsPage.pageWebPath + cmsPage.pageName
        //????????????id
        String siteId = cmsPageSave.getSiteId();
        CmsSite cmsSite = this.findCmsSiteById(siteId);
        //??????url
        return cmsSite.getSiteDomain() + cmsSite.getSiteWebPath() + cmsPageSave.getPageWebPath() + cmsPageSave.getPageName();
    }

    //????????????id??????????????????
    public CmsSite findCmsSiteById(String siteId) {
        Optional<CmsSite> optional = cmsSiteRepository.findById(siteId);
        return optional.orElse(null);
    }
}

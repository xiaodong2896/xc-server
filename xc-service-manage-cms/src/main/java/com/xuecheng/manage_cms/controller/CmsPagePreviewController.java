package com.xuecheng.manage_cms.controller;

import com.xuecheng.framework.web.BaseController;
import com.xuecheng.manage_cms.service.PageService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletOutputStream;
import java.nio.charset.StandardCharsets;

/**
 * @author Administrator
 * @version 1.0
 * @create 2018-09-12 17:24
 **/
@Slf4j
@Controller
@RequestMapping("/preview")
public class CmsPagePreviewController extends BaseController {

    @Autowired
    private PageService pageService;

    @RequestMapping(value = "/{pageId}", method = RequestMethod.GET)
    public void preview(@PathVariable("pageId") String pageId) {
        String pageHtml = pageService.getPageHtml(pageId);
        if (StringUtils.isNotEmpty(pageHtml)) {
            try {
                ServletOutputStream outputStream = response.getOutputStream();
                response.setHeader("Content-type", "text/html;charset=utf-8");
                outputStream.write(pageHtml.getBytes(StandardCharsets.UTF_8));
            } catch (Exception e) {
                log.error("预览页面异常： {}", e.getMessage());
            }
        }
    }
}

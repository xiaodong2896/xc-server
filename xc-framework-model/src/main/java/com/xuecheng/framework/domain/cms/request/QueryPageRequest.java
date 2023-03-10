package com.xuecheng.framework.domain.cms.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author Administrator
 * @version 1.0
 * @create 2018-09-12 14:59
 **/
@Data
public class QueryPageRequest {
    //接收页面查询的查询条件
    //站点id
    //站点id
    @Schema(name = "站点id")
    private String siteId;
    //页面ID
    private String pageId;
    //页面名称
    private String pageName;
    //别名
    private String pageAliase;
    //模版id
    private String templateId;
    //页面类型
    private String pageType;
}

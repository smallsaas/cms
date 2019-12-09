package com.jfeat.am.module.cms.services.definition;

public enum ArticleStatus {
    Draft,
    PublishArticle,
    Forbidden,
    Expired,        //已过期
    Deprecated,      //下架
    Wait_Audit,       //待审核
    Audit_Rejected        //审核拒绝

}

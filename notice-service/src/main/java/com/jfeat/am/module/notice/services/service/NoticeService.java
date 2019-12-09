package com.jfeat.am.module.notice.services.service;

import com.jfeat.am.module.notice.services.persistence.model.Notice;
import com.jfeat.crud.plus.CRUDServiceOnly;


/**
 * <p>
 * service interface
 * </p>
 *
 * @author Code Generator
 * @since 2017-11-02
 */
public interface NoticeService extends CRUDServiceOnly<Notice> {

    /**
     * 发布公告
     * @param id
     * @return
     */
    Integer publishNotice(Long id);

    /**
     * 下架公告
     * @param id
     * @return
     */
    Integer deprecateNotice(Long id);


    /**
     * 启停公告
     * @param id
     * @param enable
     * @return
     */
    Integer enableNotice(Long id, Integer enable);

    /**
     * 启停公告切换
     * @param id
     * @return
     */
    Integer switchEnabled(Long id);

}

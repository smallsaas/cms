package com.jfeat.am.module.notice.services.domain.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.jfeat.am.module.notice.services.domain.model.NoticeRequest;
import com.jfeat.am.module.notice.services.persistence.model.Notice;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Code Generator on 2017-11-02
 */
public interface QueryNoticeDao extends BaseMapper<Notice> {

    /**
     * 搜索公告
     * @param page
     * @param notice
     * @param expired  是否过期 0-未过期  1-过期
     * @param type 类型列表, 多类型查询
     * @return
     */
    List<NoticeRequest> findNotices(Page<NoticeRequest> page,
                             @Param("notice") Notice notice,
                             @Param("expired") Integer expired,
                             @Param("type") String[] type,
                             @Param("search") String search);


    /**
     * 已过期的公告
     * @return
     */
    List<Notice> findExpiredNotices();


    /**
     * 已发布的最新公告
     * @param page
     * @param type 类型 External-外部公告（C端）  Internal-内部公告（B端）
     * @return
     */
    List<Notice> findRecentNotices(Page<Notice> page, @Param("type") String type);

}
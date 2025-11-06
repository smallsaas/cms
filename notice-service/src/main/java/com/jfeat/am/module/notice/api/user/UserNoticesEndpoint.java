package com.jfeat.am.module.notice.api.user;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jfeat.am.module.notice.services.persistence.dao.NoticeMapper;
import com.jfeat.am.module.notice.services.persistence.model.Notice;
import com.jfeat.crud.base.tips.SuccessTip;
import com.jfeat.crud.base.tips.Tip;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api/cms/notices")
public class UserNoticesEndpoint {

    @Resource
    NoticeMapper noticeMapper;
    /**
     * 以命名查询 公告
     * @param name
     * @return
     */

    @GetMapping("/{name}")
    public Tip getNoticesByName(@PathVariable("name") String name){
        QueryWrapper<Notice> noticeQueryWrapper = new QueryWrapper<>();
        noticeQueryWrapper.eq(Notice.NAME,name);
        return SuccessTip.create(noticeMapper.selectOne(noticeQueryWrapper));
    }
}

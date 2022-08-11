package com.jfeat.am.module.notice.task;

import com.jfeat.am.module.notice.services.domain.dao.QueryNoticeDao;
import com.jfeat.am.module.notice.services.persistence.dao.NoticeMapper;
import com.jfeat.am.module.notice.services.persistence.model.Notice;
import com.jfeat.am.module.notice.services.service.NoticeService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by kang on 2018/4/11.
 */
@Component
public class AuditNoticeJob {

    @Resource
    QueryNoticeDao queryNoticeDao;

    @Resource
    NoticeService noticeService;

    @Resource
    NoticeMapper noticeMapper;

    /**
     * 每10分钟执行一次，检查到期的notice，把其禁用
     * Fix: 每10分钏执行一次浪费资源，改为每天执行一次，暂时设置为不检查
     */
    //@Scheduled(cron = "* 1/10 * * * ?")
    public void auditNotice() {
        List<Notice> expiredNotices = queryNoticeDao.findExpiredNotices();
        if (expiredNotices !=null && expiredNotices.size() > 0) {
            for (Notice expiredNotice : expiredNotices) {
                if (expiredNotice.getEnabled() == 0) {
                    continue;
                }

                Notice updateNotice = new Notice();
                updateNotice.setEnabled(0);
                updateNotice.setId(expiredNotice.getId());
                noticeMapper.updateById(updateNotice);
            }
        }
    }
}

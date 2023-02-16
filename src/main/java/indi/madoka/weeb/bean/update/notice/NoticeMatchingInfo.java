package indi.madoka.weeb.bean.update.notice;

import indi.madoka.weeb.bean.MatchingInfo;
import indi.madoka.weeb.enums.NoticeType;

/**
 * @author Arcueid
 */
public class NoticeMatchingInfo extends MatchingInfo {
    private final NoticeType noticeType;
    public NoticeMatchingInfo(Object clazz, String plugin, NoticeType noticeType) {
        super(clazz, plugin);
        this.noticeType = noticeType;
    }

    public NoticeType getNoticeType() {
        return noticeType;
    }
}

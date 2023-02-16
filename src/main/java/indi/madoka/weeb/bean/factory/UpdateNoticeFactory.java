package indi.madoka.weeb.bean.factory;

import com.alibaba.fastjson.JSONObject;
import indi.madoka.weeb.bean.update.notice.GroupRecallNotice;
import indi.madoka.weeb.bean.update.notice.UpdateNotice;
import org.springframework.stereotype.Component;

/**
 * @author Arcueid
 */
@Component
public class UpdateNoticeFactory {
    private static final String NOTICE_TYPE = "notice_type";
    private static final String GROUP_RECALL = "group_recall";
    public UpdateNotice getNoticeInJavaObject(JSONObject noticeJsonObject){
        String noticeType = noticeJsonObject.getString(NOTICE_TYPE);
        if(GROUP_RECALL.equals(noticeType)){
            return JSONObject.toJavaObject(noticeJsonObject, GroupRecallNotice.class);
        }
        return null;
    }
}

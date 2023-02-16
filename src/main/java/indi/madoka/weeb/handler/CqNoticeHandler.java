package indi.madoka.weeb.handler;

import com.alibaba.fastjson.JSONObject;
import indi.madoka.weeb.annotations.HandlerMethodMapping;
import indi.madoka.weeb.bean.factory.UpdateNoticeFactory;
import indi.madoka.weeb.bean.update.notice.NoticeMatchingInfo;
import indi.madoka.weeb.bean.update.notice.UpdateNotice;
import indi.madoka.weeb.enums.NoticeType;
import indi.madoka.weeb.enums.PostType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * @author Arcueid
 */
@Component
@Slf4j
public class CqNoticeHandler implements CqUpdateHandler<Serializable> {
    private JSONObject jsonObj;
    private UpdateNotice updateNotice;
    private final UpdateNoticeFactory updateNoticeFactory;
    private final Map<NoticeMatchingInfo, Method> NOTICE_MATCH_METHOD_MAP;

    @Autowired
    public CqNoticeHandler(UpdateNoticeFactory updateNoticeFactory) {
        this.updateNoticeFactory = updateNoticeFactory;
        NOTICE_MATCH_METHOD_MAP = HandlerMethodMapping.NOTICE_MATCH_METHOD_MAP;
    }

    @Override
    public PostType getPostType() {
        return PostType.NOTICE;
    }

    @Override
    public void init(JSONObject jsonObj) {
        this.jsonObj = jsonObj;
        this.updateNotice = updateNoticeFactory.getNoticeInJavaObject(jsonObj);
    }

    @Override
    public void handle() {
        for (NoticeMatchingInfo matchingInfo : NOTICE_MATCH_METHOD_MAP.keySet()) {
            if(matchingInfo.getNoticeType().equals(NoticeType.valueOf(updateNotice.getNoticeType().toUpperCase()))){
                try{
                    NOTICE_MATCH_METHOD_MAP.get(matchingInfo).invoke(matchingInfo.getClazz(), updateNotice);
                } catch (InvocationTargetException | IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        log.info("[TYPE NOTICE]" + jsonObj.toString());
    }
}

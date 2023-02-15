package indi.madoka.weeb.core.handler;

import com.alibaba.fastjson.JSONObject;
import indi.madoka.weeb.core.annotations.HandlerMethodMapping;
import indi.madoka.weeb.core.bean.MatchingInfo;
import indi.madoka.weeb.core.bean.factory.UpdateMessageFactory;
import indi.madoka.weeb.core.bean.update.message.UpdateMessage;
import indi.madoka.weeb.core.enums.PostType;
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
public class CqMessageHandler implements CqUpdateHandler<Serializable> {
    private JSONObject jsonObj;
    private UpdateMessage updateMessage;
    private final UpdateMessageFactory updateMessageFactory;
    private final Map<MatchingInfo, Method> KEYWORD_MATCH_METHOD_MAP;

    @Autowired
    public CqMessageHandler(UpdateMessageFactory updateMessageFactory) {
        this.KEYWORD_MATCH_METHOD_MAP = HandlerMethodMapping.KEYWORD_MATCH_METHOD_MAP;
        this.updateMessageFactory = updateMessageFactory;
    }


    @Override
    public PostType getPostType() {
        return PostType.MESSAGE;
    }

    @Override
    public void init(JSONObject jsonObj) {
        this.jsonObj = jsonObj;
        this.updateMessage = updateMessageFactory.getMessageInJavaObject(jsonObj);
    }

    @Override
    public void handle() {
        String rawMessage = jsonObj.getString("raw_message");
        for (MatchingInfo match : KEYWORD_MATCH_METHOD_MAP.keySet()) {
            if (match.matches(rawMessage)) {
                try {
                    // todo 动态参数 e.g: 方法指定只要求 String groupId, 则反射传入 groupId
                    KEYWORD_MATCH_METHOD_MAP.get(match).invoke(match.getClazz(),updateMessage);
                } catch (InvocationTargetException | IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        log.info("[TYPE MESSAGE]" + jsonObj.toString());
    }

}

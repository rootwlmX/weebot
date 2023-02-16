package indi.madoka.weeb.handler;

import com.alibaba.fastjson.JSONObject;
import indi.madoka.weeb.annotations.HandlerMethodMapping;
import indi.madoka.weeb.bean.MatchingInfo;
import indi.madoka.weeb.bean.send.Message;
import indi.madoka.weeb.bean.update.message.MessageMatchingInfo;
import indi.madoka.weeb.bean.update.message.UpdateMessage;
import indi.madoka.weeb.bean.factory.UpdateMessageFactory;
import indi.madoka.weeb.enums.PostType;
import indi.madoka.weeb.utils.RedisOperator;
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
    private final Map<MessageMatchingInfo, Method> KEYWORD_MATCH_METHOD_MAP;
    private final RedisOperator redisOperator;

    @Autowired
    public CqMessageHandler(UpdateMessageFactory updateMessageFactory, RedisOperator redisOperator) {
        this.KEYWORD_MATCH_METHOD_MAP = HandlerMethodMapping.KEYWORD_MATCH_METHOD_MAP;
        this.updateMessageFactory = updateMessageFactory;
        this.redisOperator = redisOperator;
    }


    @Override
    public PostType getPostType() {
        return PostType.MESSAGE;
    }

    @Override
    public void init(JSONObject jsonObj) {
        this.jsonObj = jsonObj;
        this.updateMessage = updateMessageFactory.getMessageInJavaObject(jsonObj);
        redisOperator.set(jsonObj.getString("message_id"), jsonObj.toString(), 600);
    }

    @Override
    public void handle() {
        String rawMessage = jsonObj.getString("raw_message");
        for (MessageMatchingInfo match : KEYWORD_MATCH_METHOD_MAP.keySet()) {
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

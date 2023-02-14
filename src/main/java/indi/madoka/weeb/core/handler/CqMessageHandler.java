package indi.madoka.weeb.core.handler;

import com.alibaba.fastjson.JSONObject;
import indi.madoka.weeb.core.annotations.HandlerMethodMapping;
import indi.madoka.weeb.core.annotations.Keyword;
import indi.madoka.weeb.core.annotations.Plugin;
import indi.madoka.weeb.core.bean.MatchingInfo;
import indi.madoka.weeb.core.enums.PostType;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Arcueid
 */
@Component
@Slf4j
public class CqMessageHandler implements CqUpdateHandler<Serializable> {
    private JSONObject jsonObj;
    private final Map<MatchingInfo, Method> KEYWORD_MATCH_METHOD_MAP;

    @Autowired
    public CqMessageHandler(HandlerMethodMapping handlerMethodMapping) {
        this.KEYWORD_MATCH_METHOD_MAP = HandlerMethodMapping.KEYWORD_MATCH_METHOD_MAP;
    }


    @Override
    public PostType getPostType() {
        return PostType.MESSAGE;
    }

    @Override
    public void init(JSONObject jsonObj) {
        this.jsonObj = jsonObj;
    }

    @Override
    public void handle() {
        String rawMessage = jsonObj.getString("raw_message");
        for (MatchingInfo match : KEYWORD_MATCH_METHOD_MAP.keySet()) {
            if (match.matches(rawMessage)) {
                try {
                    KEYWORD_MATCH_METHOD_MAP.get(match).invoke(match.getClazz());
                } catch (InvocationTargetException | IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        log.info("[TYPE MESSAGE]" + jsonObj.toString());
    }

}

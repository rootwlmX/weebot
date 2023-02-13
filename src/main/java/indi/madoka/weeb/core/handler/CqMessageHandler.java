package indi.madoka.weeb.core.handler;

import com.alibaba.fastjson.JSONObject;
import indi.madoka.weeb.core.annotations.Keyword;
import indi.madoka.weeb.core.annotations.Plugin;
import indi.madoka.weeb.core.bean.KeywordMatch;
import indi.madoka.weeb.core.enums.PostType;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
public class CqMessageHandler implements CqUpdateHandler<Serializable>, InitializingBean, ApplicationContextAware {
    private ApplicationContext applicationContext;
    private JSONObject jsonObj;
    public static final Map<KeywordMatch, Method> KEYWORD_MATCH_METHOD_MAP = new HashMap<>();

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

        for (KeywordMatch match : KEYWORD_MATCH_METHOD_MAP.keySet()) {
            if(match.matches(rawMessage)){
                try {
                    KEYWORD_MATCH_METHOD_MAP.get(match).invoke(match.getClazz());
                } catch (InvocationTargetException | IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        log.info("[TYPE MESSAGE]" + jsonObj.toString());
    }

    public JSONObject getJsonObj() {
        return jsonObj;
    }

    @Override
    public void afterPropertiesSet() {
        Map<String, Object> beansWithAnnotation = applicationContext.getBeansWithAnnotation(Plugin.class);
        Collection<Object> values = beansWithAnnotation.values();
        log.info("Initializing Plugins");
        for (Object value : values) {
            String pluginName = value.getClass().getAnnotation(Plugin.class).value();
            log.info("Initialized Plugin: "+ pluginName);
            Method[] methods = value.getClass().getMethods();
            for (Method method : methods) {
                if (method.isAnnotationPresent(Keyword.class)){
                    Keyword annotation = method.getAnnotation(Keyword.class);
                    log.info("Scanned Method Keyword: {}  Match Type: {}",annotation.value(), annotation.matchType().name());
                    KEYWORD_MATCH_METHOD_MAP.put(new KeywordMatch(value,pluginName,annotation.value(), annotation.matchType()), method);
                }
            }
        }
    }

    @Override
    public void setApplicationContext(@NotNull ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }


}

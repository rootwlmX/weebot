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
    private static final Map<KeywordMatch, Method> KEYWORD_MATCH_METHOD_MAP = new HashMap<>();

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
        String raw_message = jsonObj.getString("raw_message");

        for (KeywordMatch match : KEYWORD_MATCH_METHOD_MAP.keySet()) {
            if(match.getKeyword().equals(raw_message)){
                try {
                    KEYWORD_MATCH_METHOD_MAP.get(match).invoke(match.getClazz());
                } catch (InvocationTargetException | IllegalAccessException e) {
                    throw new RuntimeException(e);
                }

            }
        }

        log.info("[TYPE MESSAGE]" + jsonObj.toString());
/*        WebClient client = WebClient.create();
        Flux<Map> mapFlux = client.post()
                .uri("localhost:5700/send_group_msg")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(jsonObj.toString())
                .retrieve()
                .bodyToFlux(Map.class);
        mapFlux.collectList()
                .subscribe(list -> log.info(list.toString()));*/
    }

    public JSONObject getJsonObj() {
        return jsonObj;
    }

    @Override
    public void afterPropertiesSet() {
        Map<String, Object> beansWithAnnotation = applicationContext.getBeansWithAnnotation(Plugin.class);
        Collection<Object> values = beansWithAnnotation.values();
        for (Object value : values) {
            log.info("加载插件---"+value.getClass().getAnnotation(Plugin.class).value());
            Method[] methods = value.getClass().getMethods();
            for (Method method : methods) {
                if (method.isAnnotationPresent(Keyword.class)){
                    Keyword annotation = method.getAnnotation(Keyword.class);
                    log.info("扫描到注解的关键字为--"+annotation.value());
                    log.info("扫描到注解的匹配类型为--"+annotation.matchType().name());
                    KEYWORD_MATCH_METHOD_MAP.put(new KeywordMatch(value,annotation.value(), annotation.matchType()), method);
                }
            }
        }
    }

    @Override
    public void setApplicationContext(@NotNull ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}

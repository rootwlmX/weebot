package indi.madoka.weeb.core.annotations;

import indi.madoka.weeb.core.bean.MatchingInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Arcueid
 */
@Component
@Slf4j
public class HandlerMethodMapping implements InitializingBean, ApplicationContextAware {
    private ApplicationContext applicationContext;
    public static final Map<MatchingInfo, Method> KEYWORD_MATCH_METHOD_MAP = new HashMap<>();

    @Override
    public void afterPropertiesSet() {
        init();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    private void init() {
        Map<String, Object> beansWithAnnotation = applicationContext.getBeansWithAnnotation(Plugin.class);
        Collection<Object> beans = beansWithAnnotation.values();
        log.info("Initializing Plugins");
        initPlugins(beans);
    }

    private void initPlugins(Collection<Object> beans){
        for (Object value : beans) {
            String pluginName = value.getClass().getAnnotation(Plugin.class).value();
            log.info("Initialized Plugin: " + pluginName);
            Method[] methods = value.getClass().getMethods();
            for (Method method : methods) {
                if (method.isAnnotationPresent(Keyword.class)) {
                    Keyword annotation = method.getAnnotation(Keyword.class);
                    log.info("Scanned Method Keyword: {}  Match Type: {}", annotation.value(), annotation.matchType().name());
                    KEYWORD_MATCH_METHOD_MAP.put(new MatchingInfo(value, pluginName, annotation.value(), annotation.matchType()), method);
                }
            }
        }
    }
}

package indi.madoka.weeb.core.factory;

import com.alibaba.fastjson.JSONObject;
import indi.madoka.weeb.core.enums.PostType;
import indi.madoka.weeb.core.handler.CqUpdateHandler;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.EnumMap;
import java.util.Map;

/**
 * @author Arcueid
 */
@Component
public class CqUpdateHandlerFactory implements InitializingBean, ApplicationContextAware {

    private static final Map<PostType, CqUpdateHandler<Serializable>> REQUEST_HANDLER_MAP = new EnumMap<>(PostType.class);
    private static final String POST_TYPE_STRING = "post_type";
    private ApplicationContext applicationContext;

    public CqUpdateHandler<Serializable> getHandler(JSONObject jsonObj) {
        String postTypeMsg = jsonObj.getString(POST_TYPE_STRING);
        PostType postType = PostType.getPostType(postTypeMsg);
        CqUpdateHandler<Serializable> handler = REQUEST_HANDLER_MAP.get(postType);
        handler.init(jsonObj);
        return handler;
    }

    @Override
    public void afterPropertiesSet() {
        applicationContext.getBeansOfType(CqUpdateHandler.class)
                .values()
                .forEach(handler -> REQUEST_HANDLER_MAP.put(handler.getPostType(), handler));
    }

    @Override
    public void setApplicationContext(@NotNull ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}

package indi.madoka.weeb.core.interceptor;

import com.alibaba.fastjson.JSONObject;
import indi.madoka.weeb.core.factory.CqUpdateHandlerFactory;
import indi.madoka.weeb.core.handler.CqUpdateHandler;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.Serializable;

/**
 * @author Arcueid
 */
@Component
@Slf4j
public class CqHttpInterceptor implements HandlerInterceptor {

    private final CqUpdateHandlerFactory updateHandlerFactory;

    @Autowired
    public CqHttpInterceptor(CqUpdateHandlerFactory updateHandlerFactory) {
        this.updateHandlerFactory = updateHandlerFactory;
    }


    @Override
    public boolean preHandle(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull Object handler) throws Exception {
        handleRequest(request);
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull Object handler, Exception ex) throws Exception {
        log.info("afterCompletion");
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }

    private void handleRequest(HttpServletRequest request) throws IOException {
        JSONObject jsonObj = parseRequest(request);
        CqUpdateHandler<Serializable> handler = updateHandlerFactory.getHandler(jsonObj);
        handler.handle();
    }

    private JSONObject parseRequest(HttpServletRequest request) throws IOException {
        BufferedReader reader = request.getReader();
        StringBuilder builder = new StringBuilder();
        String line = reader.readLine();
        while(line != null){
            builder.append(line);
            line = reader.readLine();
        }
        reader.close();

        return JSONObject.parseObject(builder.toString());
    }
}

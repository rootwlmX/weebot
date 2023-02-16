package indi.madoka.weeb.handler;

import com.alibaba.fastjson.JSONObject;
import indi.madoka.weeb.enums.PostType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
@Slf4j
public class CqRequestHandler implements CqUpdateHandler<Serializable> {

    private JSONObject jsonObj;
    @Override
    public PostType getPostType() {
        return PostType.REQUEST;
    }

    @Override
    public void init(JSONObject jsonObj) {
        this.jsonObj = jsonObj;
    }

    @Override
    public void handle() {
        log.info("[TYPE REQUEST]" + jsonObj.toString());
    }
}

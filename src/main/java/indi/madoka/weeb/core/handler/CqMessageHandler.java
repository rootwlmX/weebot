package indi.madoka.weeb.core.handler;

import com.alibaba.fastjson.JSONObject;
import indi.madoka.weeb.core.enums.PostType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
@Slf4j
public class CqMessageHandler implements CqUpdateHandler<Serializable> {
    private JSONObject jsonObj;

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
        log.info("[TYPE MESSAGE]" + jsonObj.toString());
    }

    public JSONObject getJsonObj() {
        return jsonObj;
    }
}

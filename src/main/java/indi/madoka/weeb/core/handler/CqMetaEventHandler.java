package indi.madoka.weeb.core.handler;

import com.alibaba.fastjson.JSONObject;
import indi.madoka.weeb.core.enums.PostType;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class CqMetaEventHandler implements CqUpdateHandler<Serializable> {
    private JSONObject jsonObj;
    @Override
    public PostType getPostType() {
        return PostType.META_EVENT;
    }

    @Override
    public void init(JSONObject jsonObj) {
        this.jsonObj = jsonObj;
    }

    @Override
    public void handle() {
    }
}

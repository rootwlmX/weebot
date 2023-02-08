package indi.madoka.weeb.core.handler;

import com.alibaba.fastjson.JSONObject;
import indi.madoka.weeb.core.enums.PostType;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class CqNoticeHandler implements CqUpdateHandler<Serializable> {
    @Override
    public PostType getPostType() {
        return PostType.NOTICE;
    }

    @Override
    public void init(JSONObject jsonObj) {

    }

    @Override
    public void handle() {
    }
}

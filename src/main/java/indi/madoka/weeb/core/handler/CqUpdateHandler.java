package indi.madoka.weeb.core.handler;

import com.alibaba.fastjson.JSONObject;
import indi.madoka.weeb.core.enums.PostType;

import java.io.Serializable;

public interface CqUpdateHandler<T extends Serializable> {
    PostType getPostType();

    void init(JSONObject jsonObj);

    void handle();
}

package indi.madoka.weeb.handler;

import com.alibaba.fastjson.JSONObject;
import indi.madoka.weeb.enums.PostType;

import java.io.Serializable;

/**
 * @author Arcueid
 */
public interface CqUpdateHandler<T extends Serializable> {
    /**
     * 获取该handler接受的上报类型
     * @author Arcueid
     * @date 2023/2/15 11:24
     * @return PostType
     */
    PostType getPostType();
    /**
     * 初始化handler内部参数
     * @author Arcueid
     * @date 2023/2/15 11:28
     * @param jsonObj 上报消息的JSONObject
     */
    void init(JSONObject jsonObj);
    /**
     * 调用方法处理消息
     * @author Arcueid
     * @date 2023/2/15 11:28
     */
    void handle();
}

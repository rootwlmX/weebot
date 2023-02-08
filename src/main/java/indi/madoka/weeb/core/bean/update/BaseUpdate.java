package indi.madoka.weeb.core.bean.update;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public abstract class BaseUpdate {
    /**
     * 事件发生的unix时间戳
     */
    private Integer time;
    /**
     * 收到事件的机器人的 QQ 号
     */
    private Integer selfId;
    /**
     * 表示该上报的类型, 消息, 消息发送, 请求, 通知, 或元事件
     */
    private String postType;

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public Integer getSelfId() {
        return selfId;
    }

    public void setSelfId(Integer selfId) {
        this.selfId = selfId;
    }

    public String getPostType() {
        return postType;
    }

    public void setPostType(String postType) {
        this.postType = postType;
    }
}

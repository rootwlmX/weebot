package indi.madoka.weeb.bean.update.message;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

/**
 * @author Arcueid
 */
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class PrivateMessage extends UpdateMessage{
    protected Integer targetId;

    public Integer getTargetId() {
        return targetId;
    }

    public void setTargetId(Integer targetId) {
        this.targetId = targetId;
    }

    @Override
    public String toString() {
        return "私聊消息: PrivateMessage{" +
                "targetId='" + targetId + '\'' +
                ", message=" + message +
                ", messageType='" + messageType + '\'' +
                ", subType='" + subType + '\'' +
                ", messageId=" + messageId +
                ", userId=" + userId +
                ", rawMessage='" + rawMessage + '\'' +
                ", font=" + font +
                ", sender=" + sender.toString() +
                ", time=" + time +
                ", selfId=" + selfId +
                ", postType='" + postType + '\'' +
                '}';
    }
}

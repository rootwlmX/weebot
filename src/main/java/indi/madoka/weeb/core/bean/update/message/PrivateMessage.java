package indi.madoka.weeb.core.bean.update.message;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import indi.madoka.weeb.core.bean.send.Message;

import java.util.List;

/**
 * @author Arcueid
 */
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class PrivateMessage extends UpdateMessage{
    protected Integer targetId;
    protected List<Message> message;

    public Integer getTargetId() {
        return targetId;
    }

    public void setTargetId(Integer targetId) {
        this.targetId = targetId;
    }

    public List<Message> getMessage() {
        return message;
    }

    public void setMessage(List<Message> message) {
        this.message = message;
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

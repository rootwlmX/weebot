package indi.madoka.weeb.core.bean.update.message;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import indi.madoka.weeb.core.bean.send.Message;

import java.util.List;

/**
 * @author Arcueid
 */
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class GroupMessage extends UpdateMessage{
    protected List<Message> message;

    protected String groupId;

    protected Long messageSeq;

    public List<Message> getMessage() {
        return message;
    }

    public void setMessage(List<Message> message) {
        this.message = message;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public Long getMessageSeq() {
        return messageSeq;
    }

    public void setMessageSeq(Long messageSeq) {
        this.messageSeq = messageSeq;
    }

    @Override
    public String toString() {
        return "群聊消息: PrivateMessage{" +
                "message=" + message +
                ", groupId='" + groupId + '\'' +
                ", messageSeq=" + messageSeq +
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

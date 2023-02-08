package indi.madoka.weeb.core.bean;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class CqMessage {
    private String postType;
    private String messageType;
    private Integer time;
    private Integer selfId;
    private String subType;
    private String rawMessage;
    private Sender sender;
    private Integer userId;
    private Integer messageId;
    private Anonymous anonymous;
    private String font;
    private Integer groupId;
    private List<Message> message;
    private String messageSeq;
}

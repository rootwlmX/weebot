package indi.madoka.weeb.core.bean.send;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.util.ArrayList;
import java.util.List;

@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class SendMessage {

    private static final String TEXT = "text";
    private static final String AT = "at";
    private static final String IMAGE = "image";

    private List<Message> message;

    private Integer groupId;

    private SendMessage(Builder builder){

    }

    public static Builder builder(){
        return new Builder();
    }

    public static class Builder{

        private List<Message> messageList = new ArrayList<>();

        public Builder text(String text){
            Message msg = new Message();
            msg.setData(new TextData(text));
            msg.setType(TEXT);
            messageList.add(msg);
            return this;
        }

        public Builder img(String url){
            return this;
        }

        public Builder at(String qq){
            return this;
        }

        public SendMessage build(){
            return new SendMessage(this);
        }
    }
}

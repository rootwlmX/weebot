package indi.madoka.weeb.core.bean.send;

import java.util.ArrayList;
import java.util.List;

public class SendMessage {

    private List<Message> message;

    private SendMessage(Builder builder){

    }

    public static Builder builder(){
        return new Builder();
    }

    public static class Builder{

        private List<Message> message = new ArrayList<>();

        public Builder text(String text){
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

    private class Message{
        private Message(Data data, String type){
            this.data = data;
            this.type = type;
        }
        private Data data;
        private String type;
    }

    private class Data{
        private String text;
        private String qq;
    }
}

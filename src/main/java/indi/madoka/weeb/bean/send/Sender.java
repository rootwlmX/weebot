package indi.madoka.weeb.bean.send;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import indi.madoka.weeb.bean.update.message.GroupMessage;
import indi.madoka.weeb.bean.update.message.UpdateMessage;
import indi.madoka.weeb.config.CqApiConfig;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

/**
 * @author Arcueid
 */
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
@Slf4j
public class Sender {
    private static final String GROUP_ID = "group_id";
    private static final String USER_ID = "user_id";
    private final UpdateMessage updateMessage;
    private final WebClient webClient;
    private final JSONObject replyJsonObject;
    private Sender(Builder builder){
        this.updateMessage = builder.updateMessage;
        this.replyJsonObject = builder.replyObject;
        webClient = WebClient.create();
    }

    public static class Builder{
        private static final String DATA = "data";
        private static final String IMAGE = "image";
        private static final String TYPE = "type";
        private static final String TEXT = "text";
        private static final String FILE = "file";
        private final UpdateMessage updateMessage;
        private final JSONArray message;
        private final JSONObject replyObject;
        public Sender build(){
            if(message != null){
                this.replyObject.put("message",message);
            }
            return new Sender(this);
        }

        public Builder(UpdateMessage updateMessage){
            this.updateMessage = updateMessage;
            this.message = new JSONArray();
            this.replyObject = new JSONObject();
        }

        public Builder(String jsonString){
            this.replyObject = JSON.parseObject(jsonString);
            this.updateMessage = null;
            this.message = replyObject.getJSONArray("message");
        }

        public Builder addImage(String imageUrl){
            return addMessage(imageUrl, FILE, IMAGE);
        }

        public Builder addText(String text){
            return addMessage(text, TEXT, TEXT);
        }

        public Builder addTextAtBegin(String text){
            return addMessage(text, TEXT,TEXT, 0);
        }

        private Builder addMessage(String text, String text1, String text2, int i) {
            this.message.add(i, buildMessage(text,text1,text2));
            return this;
        }

        @NotNull
        private Builder addMessage(String text, String text1, String text2) {
            this.message.add(buildMessage(text,text1,text2));
            return this;
        }

        private JSONObject buildMessage(String text, String text1, String text2){
            JSONObject data = new JSONObject();
            data.put(text1, text);
            JSONObject messageItem = new JSONObject();
            messageItem.put(DATA,data);
            messageItem.put(TYPE, text2);
            return messageItem;
        }
    }

    public void sendAll(){
        if(updateMessage instanceof GroupMessage){
            sendGroup();
        }else{
            sendPrivate();
        }
    }

    public void sendGroup(){
        if(updateMessage instanceof GroupMessage){
            GroupMessage groupMessage = (GroupMessage) updateMessage;
            this.replyJsonObject.put(GROUP_ID, groupMessage.getGroupId());
            send(CqApiConfig.CQ_HTTP_SEND_GROUP_MSG);
        }
    }

    public void sendPrivate(){
        this.replyJsonObject.put(USER_ID, updateMessage.getUserId());
        send(CqApiConfig.CQ_HTTP_SEND_PRIVATE_MSG);
    }

    public void sendBackJsonInGroup(){
        send(CqApiConfig.CQ_HTTP_SEND_GROUP_MSG);
    }

    public void sendBackJsonInPrivate(){
        send(CqApiConfig.CQ_HTTP_SEND_PRIVATE_MSG);
    }

    private void send(String api){
        sendByJsonString(api, this.replyJsonObject.toString());
    }

    private void sendByJsonString(String api, String jsonString){
        this.webClient.post()
                .uri(api)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(jsonString)
                .retrieve()
                .bodyToFlux(Map.class)
                .subscribe();
    }
}

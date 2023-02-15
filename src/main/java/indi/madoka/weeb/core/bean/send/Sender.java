package indi.madoka.weeb.core.bean.send;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import indi.madoka.weeb.core.bean.update.message.GroupMessage;
import indi.madoka.weeb.core.bean.update.message.UpdateMessage;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

import static indi.madoka.weeb.core.config.CqApiConfig.CQ_HTTP_SEND_GROUP_MSG;
import static indi.madoka.weeb.core.config.CqApiConfig.CQ_HTTP_SEND_PRIVATE_MSG;

/**
 * @author Arcueid
 */
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
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
        private static final String IMAGE = "IMAGE";
        private static final String TYPE = "type";
        private static final String TEXT = "text";
        private static final String URL = "url";
        private final UpdateMessage updateMessage;
        private final JSONArray message;
        private final JSONObject replyObject;
        public Sender build(){
            this.replyObject.put("message",message);
            return new Sender(this);
        }

        public Builder(UpdateMessage updateMessage){
            this.updateMessage = updateMessage;
            this.message = new JSONArray();
            this.replyObject = new JSONObject();
        }

        public Builder addImage(String imageUrl){
            return buildMessage(imageUrl, URL, IMAGE);
        }

        public Builder addText(String text){
            return buildMessage(text, TEXT, TEXT);
        }

        @NotNull
        private Builder buildMessage(String text, String text2, String text3) {
            JSONObject data = new JSONObject();
            data.put(text2, text);
            JSONObject messageItem = new JSONObject();
            messageItem.put(DATA,data);
            messageItem.put(TYPE, text3);
            this.message.add(messageItem);
            return this;
        }
    }

    public void sendGroup(){
        if(updateMessage instanceof GroupMessage){
            GroupMessage groupMessage = (GroupMessage) updateMessage;
            this.replyJsonObject.put(GROUP_ID, groupMessage.getGroupId());
            send(CQ_HTTP_SEND_GROUP_MSG);
        }
    }

    public void sendPrivate(){
        this.replyJsonObject.put(USER_ID, updateMessage.getUserId());
        send(CQ_HTTP_SEND_PRIVATE_MSG);
    }

    private void send(String api){
        this.webClient.post()
                .uri(api)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(this.replyJsonObject.toJSONString())
                .retrieve()
                .bodyToFlux(Map.class)
                .subscribe();
    }
}

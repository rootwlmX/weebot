package indi.madoka.weeb.core.handler;

import com.alibaba.fastjson.JSONObject;
import indi.madoka.weeb.core.enums.PostType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.io.Serializable;
import java.util.Map;

@Component
@Slf4j
public class CqMessageHandler implements CqUpdateHandler<Serializable> {
    private JSONObject jsonObj;

    @Override
    public PostType getPostType() {
        return PostType.MESSAGE;
    }

    @Override
    public void init(JSONObject jsonObj) {
        this.jsonObj = jsonObj;
    }

    @Override
    public void handle() {
        log.info("[TYPE MESSAGE]" + jsonObj.toString());
        WebClient client = WebClient.create();
        Flux<Map> mapFlux = client.post()
                .uri("localhost:5700/send_group_msg")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(jsonObj.toString())
                .retrieve()
                .bodyToFlux(Map.class);
        mapFlux.collectList()
                .subscribe(list -> log.info(list.toString()));
    }

    public JSONObject getJsonObj() {
        return jsonObj;
    }
}

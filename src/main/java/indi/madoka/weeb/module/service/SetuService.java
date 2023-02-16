package indi.madoka.weeb.module.service;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

/**
 * @author Arcueid
 */
@Service
public class SetuService {
    private final WebClient webClient;
    private static final String LOLICON_API_URL = "https://api.lolicon.app/setu/v2";

    public SetuService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl(LOLICON_API_URL).build();
    }

    public String getRandomPicture(){
        Mono<String> apiRequest = loliconApiRequest();
        return getResultImageUrl(apiRequest.block());
    }

    public String getTaggedPicture(String tag){
        Mono<String> apiRequest = loliconApiRequest(tag);
        return getResultImageUrl(apiRequest.block());
    }

    private Mono<String> loliconApiRequest() {
        return this.webClient.get()
                .retrieve()
                .bodyToMono(String.class);
    }

    private Mono<String> loliconApiRequest(String tag){
        return this.webClient.get()
                .uri("?tag=" + tag)
                .retrieve()
                .bodyToMono(String.class);
    }

    private String getResultImageUrl(String result) {
        JSONObject resultInJson = parseResultToJson(result);
        return resultInJson.getJSONArray("data")
                .getJSONObject(0)
                .getJSONObject("urls")
                .getString("original");
    }

    private JSONObject parseResultToJson(String result){
        return JSONObject.parseObject(result);
    }
}

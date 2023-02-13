package indi.madoka.weeb.core.module;

import indi.madoka.weeb.core.annotations.Keyword;
import indi.madoka.weeb.core.annotations.Plugin;
import indi.madoka.weeb.core.enums.MatchType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

@Plugin("测试插件")
@Slf4j
public class TestModule {
    @Keyword(value = "command", matchType = MatchType.EQUALS)
    public void test(){
        log.info("{\"group_id\": 791782725,\"message\": \"测试成功\"}");
        WebClient client = WebClient.create();
        client.post()
                .uri("localhost:5700/send_group_msg")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue("{\"group_id\":791782725,\"message\":\"测试成功\"}")
                .retrieve()
                .bodyToFlux(Map.class)
                .subscribe();
    }

    @Keyword(value = "#前缀", matchType = MatchType.STARTS_WITH)
    public void testStart(){
        WebClient client = WebClient.create();
        client.post()
                .uri("localhost:5700/send_group_msg")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue("{\"group_id\":791782725,\"message\":\"前缀测试成功\"}")
                .retrieve()
                .bodyToFlux(Map.class)
                .subscribe();
    }
}


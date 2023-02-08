package indi.madoka.weeb.core.websocket;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft_6455;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;

@Slf4j
public class WeebWebSocketClient extends WebSocketClient {

    public WeebWebSocketClient(URI serverUri){
        super(serverUri, new Draft_6455());
    }

    @Override
    public void onOpen(ServerHandshake serverHandshake) {
        log.info("[websocket] 连接成功");
    }

    @Override
    public void onMessage(String message) {
        JSONObject jsonObject = JSON.parseObject(message);
        if(jsonObject.get("post_type").equals("message")){
            log.info("[websocket] 收到消息={}",message);
        }
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        log.info("[websocket] 退出连接");
    }

    @Override
    public void onError(Exception ex) {
        log.info("[websocket] 连接错误={}",ex.getMessage());
    }
}

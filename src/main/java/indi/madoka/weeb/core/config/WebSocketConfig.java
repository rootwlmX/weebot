package indi.madoka.weeb.core.config;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import indi.madoka.weeb.core.utils.HttpRequestUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft_6455;
import org.java_websocket.handshake.ServerHandshake;
import org.springframework.stereotype.Component;

import java.net.URI;

@Slf4j
@Component
public class WebSocketConfig {

    /**
     *
     * 开启WebSocketClient<br>
     *
     * Example:<br>
     *[websocket] 收到消息={"post_type":"message","message_type":"group","time":1670228166,
     * "self_id":1170895958,"sub_type":"normal","raw_message":"[CQ:at,qq=1170895958] ",
     * "sender":{"age":0,"area":"","card":"我的狗叫波奇塔你狗叫什么","level":"","nickname":"あさがみ ふじの",
     * "role":"admin","sex":"unknown","title":"","user_id":867610697},"user_id":867610697,
     * "message_id":1719902542,"anonymous":null,"font":0,"group_id":791782725,
     * "message":[{"type":"at","data":{"qq":"1170895958"}},
     * {"type":"text","data":{"text":" "}}],"message_seq":106207}
     */

    //TODO Spring WebSocket
    //@Bean
    public WebSocketClient webSocketClient() {
        try {
            WebSocketClient webSocketClient = new WebSocketClient(new URI("ws://127.0.0.1:5701"),new Draft_6455()) {
                @Override
                public void onOpen(ServerHandshake handshakedata) {
                    log.info("[websocket] 连接成功");
                }

                @Override
                public void onMessage(String message) {
                    log.info("[websocket] 收到消息={}",message);
                    JSONObject msgObject = JSON.parseObject(message);
                    String rawMsg = (String)msgObject.get("raw_message");
                    if(StringUtils.equals(rawMsg.trim(), "[CQ:at,qq=2131409924]")){
                        HttpRequestUtil.doGet("http://127.0.0.1:5700/send_group_msg?group_id=791782725&message=狗修金，呆斯ki");
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
            };
            webSocketClient.connect();
            return webSocketClient;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}

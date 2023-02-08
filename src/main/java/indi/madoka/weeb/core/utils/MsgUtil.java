package indi.madoka.weeb.core.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class MsgUtil {
    //TODO
    public static int checkMsgType(String messageText){
        JSONObject msgObj = JSON.parseObject(messageText);
        String postType = (String)msgObj.get("post_type");
        return 0;
    }
}

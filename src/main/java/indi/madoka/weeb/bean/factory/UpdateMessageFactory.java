package indi.madoka.weeb.bean.factory;

import com.alibaba.fastjson.JSONObject;
import indi.madoka.weeb.bean.update.message.GroupMessage;
import indi.madoka.weeb.bean.update.message.PrivateMessage;
import indi.madoka.weeb.bean.update.message.UpdateMessage;
import org.springframework.stereotype.Component;

/**
 * @author Arcueid
 */
@Component
public class UpdateMessageFactory {
    private static final String MESSAGE_TYPE = "message_type";
    private static final String PRIVATE_MESSAGE_TYPE = "private";

    private static final String GROUP_MESSAGE_TYPE = "group";

    public UpdateMessage getMessageInJavaObject(JSONObject messageJsonObject){
        String messageType = messageJsonObject.getString(MESSAGE_TYPE);
        assert messageType != null;
        if(PRIVATE_MESSAGE_TYPE.equals(messageType)){
            return JSONObject.toJavaObject(messageJsonObject, PrivateMessage.class);
        } else if (GROUP_MESSAGE_TYPE.equals(messageType)) {
            return JSONObject.toJavaObject(messageJsonObject, GroupMessage.class);
        }
        return null;
    }
}

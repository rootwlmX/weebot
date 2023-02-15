package indi.madoka.weeb.core.bean.factory;

import com.alibaba.fastjson.JSONObject;
import indi.madoka.weeb.core.bean.update.message.GroupMessage;
import indi.madoka.weeb.core.bean.update.message.PrivateMessage;
import indi.madoka.weeb.core.bean.update.message.UpdateMessage;
import org.springframework.stereotype.Component;

/**
 * @author Arcueid
 */
@Component
public class UpdateMessageFactory {
    private static final String MESSAGE_TYPE = "message_type";
    private static final String PRIVATE_MESSAGE_TYPE = "private";

    private static final String GROUP_MESSAGE_TYPE = "group";

    public UpdateMessage getMessageInJavaObject(JSONObject messageJsonObj){
        String messageType = messageJsonObj.getString(MESSAGE_TYPE);
        assert messageType != null;
        if(PRIVATE_MESSAGE_TYPE.equals(messageType)){
            return JSONObject.toJavaObject(messageJsonObj, PrivateMessage.class);
        } else if (GROUP_MESSAGE_TYPE.equals(messageType)) {
            return JSONObject.toJavaObject(messageJsonObj, GroupMessage.class);
        }
        return null;
    }
}

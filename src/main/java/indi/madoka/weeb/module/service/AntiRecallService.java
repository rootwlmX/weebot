package indi.madoka.weeb.module.service;

import indi.madoka.weeb.bean.update.notice.GroupRecallNotice;
import indi.madoka.weeb.bean.update.notice.UpdateNotice;
import indi.madoka.weeb.utils.RedisOperator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author Arcueid
 */
@Service
@Slf4j
public class AntiRecallService {

    private final RedisOperator redisOperator;

    public AntiRecallService(RedisOperator redisOperator) {
        this.redisOperator = redisOperator;
    }

    public String getRecallMessageInJsonString(UpdateNotice updateNotice) {
        if(updateNotice instanceof GroupRecallNotice){
            String messageId = getMessageId((GroupRecallNotice) updateNotice);
            String s = redisOperator.get(messageId);
            log.info("从redis中取出字符串: "+ s);
            return s;
        }
        return null;
    }

    private String getMessageId(GroupRecallNotice updateNotice){
        return String.valueOf(updateNotice.getMessageId());
    }
}

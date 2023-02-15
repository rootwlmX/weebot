package indi.madoka.weeb.core.module;

import indi.madoka.weeb.core.annotations.Keyword;
import indi.madoka.weeb.core.annotations.Plugin;
import indi.madoka.weeb.core.bean.send.Sender;
import indi.madoka.weeb.core.bean.update.message.UpdateMessage;
import indi.madoka.weeb.core.enums.MatchType;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Arcueid
 */
@Plugin("测试插件")
@Slf4j
public class TestModule {
    @Keyword(value = "command", matchType = MatchType.EQUALS)
    public void test(UpdateMessage updateMessage){
        Sender sender = new Sender.Builder(updateMessage)
                .addImage("https://i.pixiv.re/img-original/img/2022/12/03/05/54/01/103291885_p0.jpg")
                .addText("图文测试")
                .build();
        sender.sendAll();
    }

    @Keyword(value = "#前缀", matchType = MatchType.STARTS_WITH)
    public void testStart(){

    }
}


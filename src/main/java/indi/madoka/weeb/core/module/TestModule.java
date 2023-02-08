package indi.madoka.weeb.core.module;

import indi.madoka.weeb.core.annotations.Keyword;
import indi.madoka.weeb.core.annotations.Plugin;
import indi.madoka.weeb.core.bean.send.SendMessage;

@Plugin
public class TestModule {
    @Keyword(value = "command")
    public void test(){
        SendMessage message = SendMessage.builder()
                .at("1170895958")
                .text("回复")
                .build();
    }
}

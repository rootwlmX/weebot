package indi.madoka.weeb.core.module;

import indi.madoka.weeb.core.annotations.Keyword;
import indi.madoka.weeb.core.annotations.Plugin;
import indi.madoka.weeb.core.enums.MatchType;

@Plugin
public class TestModule {
    @Keyword(value = "command", matchType = MatchType.EQUALS)
    public void test(){

    }
}

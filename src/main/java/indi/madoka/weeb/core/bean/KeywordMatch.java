package indi.madoka.weeb.core.bean;

import indi.madoka.weeb.core.enums.MatchType;
import indi.madoka.weeb.core.utils.RegUtil;

public class KeywordMatch {
    private final Object clazz;
    private final String plugin;
    private final String keyword;
    private final MatchType matchType;

    public KeywordMatch(Object clazz, String plugin, String keyword, MatchType matchType) {
        this.clazz = clazz;
        this.plugin = plugin;
        this.keyword = keyword;
        this.matchType = matchType;
    }

    public Object getClazz() {
        return clazz;
    }

    public String getKeyword() {
        return keyword;
    }

    public boolean matches(String keyword){
        return RegUtil.regMatchByMatchType(this.keyword,keyword,matchType);
    }

    public MatchType getMatchType() {
        return matchType;
    }

    public String getPlugin() {
        return plugin;
    }
}

package indi.madoka.weeb.core.bean;

import indi.madoka.weeb.core.enums.MatchType;

public class KeywordMatch {
    private final Object clazz;
    private final String keyword;
    private final MatchType matchType;

    public KeywordMatch(Object clazz, String keyword, MatchType matchType) {
        this.clazz = clazz;
        this.keyword = keyword;
        this.matchType = matchType;
    }

    public Object getClazz() {
        return clazz;
    }

    public String getKeyword() {
        return keyword;
    }

    public MatchType getMatchType() {
        return matchType;
    }
}

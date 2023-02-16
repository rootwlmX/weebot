package indi.madoka.weeb.bean.update.message;

import indi.madoka.weeb.bean.MatchingInfo;
import indi.madoka.weeb.enums.MatchType;
import indi.madoka.weeb.utils.RegUtil;

/**
 * @author Arcueid
 */
public class MessageMatchingInfo extends MatchingInfo {
    private final String keyword;
    private final MatchType matchType;

    public MessageMatchingInfo(Object clazz, String plugin, String keyword, MatchType matchType) {
        super(clazz,plugin);
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

package indi.madoka.weeb.utils;

import indi.madoka.weeb.enums.MatchType;

/**
 * @author Arcueid
 */
public class RegUtil {
    public static boolean regMatchByMatchType(String source,String target, MatchType matchType){
        if(matchType.equals(MatchType.EQUALS)){
            return target.equals(source);
        }
        if(matchType.equals(MatchType.STARTS_WITH)){
            return target.startsWith(source);
        }
        if(matchType.equals(MatchType.ENDS_WITH)){
            return target.endsWith(source);
        }
        return false;
    }
}

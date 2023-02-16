package indi.madoka.weeb.annotations;

import indi.madoka.weeb.enums.MatchType;
import indi.madoka.weeb.enums.MsgTarget;

import java.lang.annotation.*;

/**
 * @author Arcueid
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Keyword{
    String value() default "";
    MatchType matchType() default MatchType.EQUALS;
    MsgTarget receiveTarget() default MsgTarget.ALL;
    MsgTarget replyTarget() default MsgTarget.ALL;
}

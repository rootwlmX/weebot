package indi.madoka.weeb.core.annotations;

import indi.madoka.weeb.core.enums.MatchType;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Keyword {
    String value() default "";

    MatchType matchType() default MatchType.EQUALS;
}

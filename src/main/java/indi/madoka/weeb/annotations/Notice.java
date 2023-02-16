package indi.madoka.weeb.annotations;

import indi.madoka.weeb.enums.NoticeType;

import java.lang.annotation.*;

/**
 * @author Arcueid
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Notice {
    NoticeType noticeType();
}

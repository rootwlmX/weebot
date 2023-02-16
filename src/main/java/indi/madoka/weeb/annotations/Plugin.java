package indi.madoka.weeb.annotations;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * @author Arcueid
 */
@Component
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Plugin {
    String value() default "Plugin with no name";
}

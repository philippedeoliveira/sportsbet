package fr.philippedeoliveira.annotations;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * Annotation that will be post-processed by spring. 
 * 
 * @author waddle
 *
 */
@Retention(RUNTIME)
@Target(FIELD)
@Documented
public @interface Log {
}


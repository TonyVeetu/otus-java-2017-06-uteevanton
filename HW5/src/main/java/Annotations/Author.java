package Annotations;

import com.sun.tools.javac.api.ClientCodeWrapper;

import java.lang.annotation.*;
/**
 * Created by anton on 26.07.17.
 */
@Target(value= ElementType.ANNOTATION_TYPE)
@Retention(value= RetentionPolicy.CLASS)
public @interface Author {
    String firstName()  default "Anton";
    String secondName()  default "Uteev";
}

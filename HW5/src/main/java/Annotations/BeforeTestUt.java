package Annotations;

import java.lang.annotation.*;
/**
 * Created by anton on 26.07.17.
 */
@Target(value=ElementType.METHOD)
@Retention(value= RetentionPolicy.RUNTIME)
@Author
public @interface BeforeTestUt {
}

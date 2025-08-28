package com.app.Auth.Validator;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UniqueUsernameValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueUsername {

    // Error Message
    String message() default "Username Already Exists" ;

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};


}

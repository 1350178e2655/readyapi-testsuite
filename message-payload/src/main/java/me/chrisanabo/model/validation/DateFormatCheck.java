package me.chrisanabo.model.validation;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = DateFormatValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface DateFormatCheck {

	String message() default "Date format is incorrect";

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };

}

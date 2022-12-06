package b2c.app.constraint;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = PasswordValidator.class)
@Target({FIELD})
@Retention(RUNTIME)
public @interface UserPassword {

	String message() default "Login musi sie skladac z malej i wielkiej litery"
			+ ", mieć co najmniej 8 znaków oraz zawierać co najmniej dwie cyfry";
	Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

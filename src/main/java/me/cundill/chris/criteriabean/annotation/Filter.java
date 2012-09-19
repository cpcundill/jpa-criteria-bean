package me.cundill.chris.criteriabean.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(value = ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Filter {
	
	String propertyName() default "";
	Operator operator() default Operator.EQ;
}

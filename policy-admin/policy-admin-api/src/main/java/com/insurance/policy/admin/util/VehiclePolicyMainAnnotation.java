package com.insurance.policy.admin.util;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @Author 潘升
 * @Description //TODO 自定义验证注解$
 * @Date 2020/8/14 11:08
 */

@Target({TYPE})
@Retention(RUNTIME)
//指定验证器
@Constraint(validatedBy = VehiclePolicyValidator.class)
@Documented
public @interface VehiclePolicyMainAnnotation {
    String message() default "校验异常";
    //分组
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}

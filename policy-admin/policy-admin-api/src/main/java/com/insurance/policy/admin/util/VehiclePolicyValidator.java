package com.insurance.policy.admin.util;

import com.insurance.policy.admin.domain.VehiclePolicyMain;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Date;

/**
 * @Author 潘升
 * @Description //TODO $ 自定义验证类
 * @Date 2020/8/14 11:09
 */
public class VehiclePolicyValidator implements ConstraintValidator<VehiclePolicyMainAnnotation, VehiclePolicyMain> {

    @Override
    public void initialize(VehiclePolicyMainAnnotation constraintAnnotation) {

    }

    @Override
    public boolean isValid(VehiclePolicyMain value, ConstraintValidatorContext context) {
        //获取生效日期
        Date effectiveDate = value.getEffectiveDate();
        //获取到期日
        Date expiryDate = value.getExpiryDate();
        if(effectiveDate.getTime() > expiryDate.getTime() ){
            context.buildConstraintViolationWithTemplate("保单生效终止日期要晚于保单生效的开始日期")
                    .addConstraintViolation();
            return false;
        }



        return true;
    }
}

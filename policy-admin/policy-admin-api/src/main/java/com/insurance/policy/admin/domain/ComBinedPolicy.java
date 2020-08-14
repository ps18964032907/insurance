package com.insurance.policy.admin.domain;

import lombok.Data;

import javax.validation.Valid;


/**
 * 交强险和商业险组合对象
 */
@Data
public class ComBinedPolicy {

//    @Valid
    private CommercialPolicy commercialPolicy;
//    @Valid
    private CompulsoryPolicy compulsoryPolicy;
}

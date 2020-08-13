package com.insurance.policy.admin.domain;

import javax.validation.Valid;


/**
 * 交强险和商业险组合对象
 */
public class ComBinedPolicy {

    @Valid
    private CommercialPolicy commercialPolicy;
    @Valid
    private CompulsoryPolicy compulsoryPolicy;
}

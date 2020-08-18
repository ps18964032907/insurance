package com.insurance.policy.pay.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 保单收费对象 t_prem_collection
 * 
 * @author ruoyi
 * @date 2020-08-16
 */
public class VehicleCollection extends BaseEntity implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 保单主键 */
    @Excel(name = "保单主键")
    private Long policyId;

    /** 关联的另一张保单主键 */
    @Excel(name = "关联的另一张保单主键")
    private Long associatedPolicyId;

    /** 应收保费 */
    @Excel(name = "应收保费")
    private BigDecimal duePremium;

    /** 是否已收到保费1-已收 0-未收 */
    @Excel(name = "是否已收到保费1-已收 0-未收")
    private String status;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date insertTime;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setPolicyId(Long policyId) 
    {
        this.policyId = policyId;
    }

    public Long getPolicyId() 
    {
        return policyId;
    }
    public void setAssociatedPolicyId(Long associatedPolicyId) 
    {
        this.associatedPolicyId = associatedPolicyId;
    }

    public Long getAssociatedPolicyId() 
    {
        return associatedPolicyId;
    }
    public void setDuePremium(BigDecimal duePremium) 
    {
        this.duePremium = duePremium;
    }

    public BigDecimal getDuePremium() 
    {
        return duePremium;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }
    public void setInsertTime(Date insertTime) 
    {
        this.insertTime = insertTime;
    }

    public Date getInsertTime() 
    {
        return insertTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("policyId", getPolicyId())
            .append("associatedPolicyId", getAssociatedPolicyId())
            .append("duePremium", getDuePremium())
            .append("status", getStatus())
            .append("insertTime", getInsertTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}

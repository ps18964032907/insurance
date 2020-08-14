package com.insurance.policy.admin.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.insurance.policy.admin.util.VehiclePolicyMainAnnotation;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;

/**
 * 保单主要信息对象 t_vehicle_policy_main
 * 
 * @author ruoyi
 * @date 2020-08-12
 */
@Validated
@VehiclePolicyMainAnnotation
public class VehiclePolicyMain extends BaseEntity
{
    private static final long serialVersionUID = 1L;


    /** 自增主键 */
    private Long id;

    /** 保单/投保单号 */
    @Excel(name = "保单/投保单号")
    private String policyNo;

    /** 关联的保单/投保单主键 */
    @Excel(name = "关联的保单/投保单主键")
    private Long associatedPolicyId;

    /** 归属经办人id */
    @Excel(name = "归属经办人id")
    private Long belongToHanderId;

    /** 归属机构id */
    @Excel(name = "归属机构id")
    private Long belongToOrgId;

    /** 业务来源代码 */
    @Excel(name = "业务来源代码")
    private String businessSourceCode;

    /** 保单/投保单状态 */
    @Excel(name = "保单/投保单状态")
    private String policyStatus;

    /** 保单生效日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Future
    @Excel(name = "保单生效日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date effectiveDate;

    /** 保单终止日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "保单终止日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date expiryDate;

    /** 标准保费 */
    @Excel(name = "标准保费")
    private BigDecimal standPremium;

    /** 应缴纳保费 */
    @Excel(name = "应缴纳保费")
    private BigDecimal duePremium;

    /** 版本号 */
    @Excel(name = "版本号")
    private Long version;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Past(message="创建时间必须大于当前时间")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date insertTime;

    /** 是否是交强险：0-商业险 1-交强险 */

    @Excel(name = "是否是交强险：0-商业险 1-交强险")
    private String compulsory;

    /** 电子保单文件地址 */
    @Excel(name = "电子保单文件地址")
    private String epolicyUrl;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setPolicyNo(String policyNo) 
    {
        this.policyNo = policyNo;
    }

    public String getPolicyNo() 
    {
        return policyNo;
    }
    public void setAssociatedPolicyId(Long associatedPolicyId) 
    {
        this.associatedPolicyId = associatedPolicyId;
    }

    public Long getAssociatedPolicyId() 
    {
        return associatedPolicyId;
    }
    public void setBelongToHanderId(Long belongToHanderId) 
    {
        this.belongToHanderId = belongToHanderId;
    }

    public Long getBelongToHanderId() 
    {
        return belongToHanderId;
    }
    public void setBelongToOrgId(Long belongToOrgId) 
    {
        this.belongToOrgId = belongToOrgId;
    }

    public Long getBelongToOrgId() 
    {
        return belongToOrgId;
    }
    public void setBusinessSourceCode(String businessSourceCode) 
    {
        this.businessSourceCode = businessSourceCode;
    }

    public String getBusinessSourceCode() 
    {
        return businessSourceCode;
    }
    public void setPolicyStatus(String policyStatus) 
    {
        this.policyStatus = policyStatus;
    }

    public String getPolicyStatus() 
    {
        return policyStatus;
    }
    public void setEffectiveDate(Date effectiveDate) 
    {
        this.effectiveDate = effectiveDate;
    }

    public Date getEffectiveDate() 
    {
        return effectiveDate;
    }
    public void setExpiryDate(Date expiryDate) 
    {
        this.expiryDate = expiryDate;
    }

    public Date getExpiryDate() 
    {
        return expiryDate;
    }
    public void setStandPremium(BigDecimal standPremium) 
    {
        this.standPremium = standPremium;
    }

    public BigDecimal getStandPremium() 
    {
        return standPremium;
    }
    public void setDuePremium(BigDecimal duePremium) 
    {
        this.duePremium = duePremium;
    }

    public BigDecimal getDuePremium() 
    {
        return duePremium;
    }
    public void setVersion(Long version) 
    {
        this.version = version;
    }

    public Long getVersion() 
    {
        return version;
    }
    public void setInsertTime(Date insertTime) 
    {
        this.insertTime = insertTime;
    }

    public Date getInsertTime() 
    {
        return insertTime;
    }
    public void setCompulsory(String compulsory)
    {
        this.compulsory = compulsory;
    }

    public String getCompulsory()
    {
        return compulsory;
    }
    public void setEpolicyUrl(String epolicyUrl) 
    {
        this.epolicyUrl = epolicyUrl;
    }

    public String getEpolicyUrl() 
    {
        return epolicyUrl;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("policyNo", getPolicyNo())
            .append("associatedPolicyId", getAssociatedPolicyId())
            .append("belongToHanderId", getBelongToHanderId())
            .append("belongToOrgId", getBelongToOrgId())
            .append("businessSourceCode", getBusinessSourceCode())
            .append("policyStatus", getPolicyStatus())
            .append("effectiveDate", getEffectiveDate())
            .append("expiryDate", getExpiryDate())
            .append("standPremium", getStandPremium())
            .append("duePremium", getDuePremium())
            .append("version", getVersion())
            .append("insertTime", getInsertTime())
            .append("updateTime", getUpdateTime())
            .append("compulsoryPolicy", getCompulsory())
            .append("epolicyUrl", getEpolicyUrl())
            .toString();
    }
}

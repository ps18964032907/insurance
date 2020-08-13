package com.insurance.policy.admin.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 险种责任信息对象 t_vehicle_coverage
 * 
 * @author ruoyi
 * @date 2020-08-12
 */
public class VehicleCoverage extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 自增主键 */
    private Long id;

    /** 保单/投保单表主键 */
    @Excel(name = "保单/投保单表主键")
    private Long policyId;

    /** 产品定义中的责任代码 */
    @Excel(name = "产品定义中的责任代码")
    private String productionCoverageCode;

    /** 标准保费 */
    @Excel(name = "标准保费")
    private BigDecimal standPremium;

    /** 保额 */
    @Excel(name = "保额")
    private BigDecimal sumInsured;

    /** 应缴保费 */
    @Excel(name = "应缴保费")
    private BigDecimal duePremium;

    /** 版本号 */
    @Excel(name = "版本号")
    private Long version;

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
    public void setProductionCoverageCode(String productionCoverageCode) 
    {
        this.productionCoverageCode = productionCoverageCode;
    }

    public String getProductionCoverageCode() 
    {
        return productionCoverageCode;
    }
    public void setStandPremium(BigDecimal standPremium) 
    {
        this.standPremium = standPremium;
    }

    public BigDecimal getStandPremium() 
    {
        return standPremium;
    }
    public void setSumInsured(BigDecimal sumInsured) 
    {
        this.sumInsured = sumInsured;
    }

    public BigDecimal getSumInsured() 
    {
        return sumInsured;
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

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("policyId", getPolicyId())
            .append("productionCoverageCode", getProductionCoverageCode())
            .append("standPremium", getStandPremium())
            .append("sumInsured", getSumInsured())
            .append("duePremium", getDuePremium())
            .append("version", getVersion())
            .append("insertTime", getInsertTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}

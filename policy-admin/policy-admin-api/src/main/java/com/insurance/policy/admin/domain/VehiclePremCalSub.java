package com.insurance.policy.admin.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 保费计算附加信息对象 t_vehicle_prem_cal_sub
 * 
 * @author ruoyi
 * @date 2020-08-12
 */
public class VehiclePremCalSub extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 自增主键 */
    private Long id;

    /** 保单/投保单id */
    @Excel(name = "保单/投保单id")
    private Long policyId;

    /** 行业平台返回的投保查询码 */
    @Excel(name = "行业平台返回的投保查询码")
    private String proposalQueryNo;

    /** 行业平台返回的投保预确认码 */
    @Excel(name = "行业平台返回的投保预确认码")
    private String proposalPreConfirmNo;

    /** 行业平台返回的投保确认码 */
    @Excel(name = "行业平台返回的投保确认码")
    private String proposalConfirmNo;

    /** 投保查询码失效日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "投保查询码失效日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date proposalQueryNoExpireDate;

    /** 投保预确认码失效日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "投保预确认码失效日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date proposalPreConfirmNoExpireDate;

    /** 平台返回的无赔款优待系数 */
    @Excel(name = "平台返回的无赔款优待系数")
    private BigDecimal ncdRate;

    /** 平台返回的交通违法系数 */
    @Excel(name = "平台返回的交通违法系数")
    private BigDecimal trafficViolationRate;

    /** 保险公司内部的自主核保系数 */
    @Excel(name = "保险公司内部的自主核保系数")
    private BigDecimal uwRate;

    /** 监管平台返回的基准纯风险保费 */
    @Excel(name = "监管平台返回的基准纯风险保费")
    private BigDecimal platformBenchRiskPrem;

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
    public void setProposalQueryNo(String proposalQueryNo) 
    {
        this.proposalQueryNo = proposalQueryNo;
    }

    public String getProposalQueryNo() 
    {
        return proposalQueryNo;
    }
    public void setProposalPreConfirmNo(String proposalPreConfirmNo) 
    {
        this.proposalPreConfirmNo = proposalPreConfirmNo;
    }

    public String getProposalPreConfirmNo() 
    {
        return proposalPreConfirmNo;
    }
    public void setProposalConfirmNo(String proposalConfirmNo) 
    {
        this.proposalConfirmNo = proposalConfirmNo;
    }

    public String getProposalConfirmNo() 
    {
        return proposalConfirmNo;
    }
    public void setProposalQueryNoExpireDate(Date proposalQueryNoExpireDate) 
    {
        this.proposalQueryNoExpireDate = proposalQueryNoExpireDate;
    }

    public Date getProposalQueryNoExpireDate() 
    {
        return proposalQueryNoExpireDate;
    }
    public void setProposalPreConfirmNoExpireDate(Date proposalPreConfirmNoExpireDate) 
    {
        this.proposalPreConfirmNoExpireDate = proposalPreConfirmNoExpireDate;
    }

    public Date getProposalPreConfirmNoExpireDate() 
    {
        return proposalPreConfirmNoExpireDate;
    }
    public void setNcdRate(BigDecimal ncdRate) 
    {
        this.ncdRate = ncdRate;
    }

    public BigDecimal getNcdRate() 
    {
        return ncdRate;
    }
    public void setTrafficViolationRate(BigDecimal trafficViolationRate) 
    {
        this.trafficViolationRate = trafficViolationRate;
    }

    public BigDecimal getTrafficViolationRate() 
    {
        return trafficViolationRate;
    }
    public void setUwRate(BigDecimal uwRate) 
    {
        this.uwRate = uwRate;
    }

    public BigDecimal getUwRate() 
    {
        return uwRate;
    }
    public void setPlatformBenchRiskPrem(BigDecimal platformBenchRiskPrem) 
    {
        this.platformBenchRiskPrem = platformBenchRiskPrem;
    }

    public BigDecimal getPlatformBenchRiskPrem() 
    {
        return platformBenchRiskPrem;
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
            .append("proposalQueryNo", getProposalQueryNo())
            .append("proposalPreConfirmNo", getProposalPreConfirmNo())
            .append("proposalConfirmNo", getProposalConfirmNo())
            .append("proposalQueryNoExpireDate", getProposalQueryNoExpireDate())
            .append("proposalPreConfirmNoExpireDate", getProposalPreConfirmNoExpireDate())
            .append("ncdRate", getNcdRate())
            .append("trafficViolationRate", getTrafficViolationRate())
            .append("uwRate", getUwRate())
            .append("platformBenchRiskPrem", getPlatformBenchRiskPrem())
            .append("version", getVersion())
            .append("insertTime", getInsertTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}

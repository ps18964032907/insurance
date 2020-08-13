package com.insurance.policy.admin.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 车船税信息对象 t_vehicle_tax
 * 
 * @author ruoyi
 * @date 2020-08-12
 */
public class VehicleTax extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 自增主键 */
    private Long id;

    /** 保单/投保单表主键 */
    @Excel(name = "保单/投保单表主键")
    private Long policyId;

    /** 缴税人识别号 */
    @Excel(name = "缴税人识别号")
    private String taxPayerNo;

    /** 缴税人名称 */
    @Excel(name = "缴税人名称")
    private String taxPayerName;

    /** 车船税标准税款 */
    @Excel(name = "车船税标准税款")
    private BigDecimal stdTax;

    /** 当年应缴纳税款 */
    @Excel(name = "当年应缴纳税款")
    private BigDecimal currentTax;

    /** 往年欠缴税款 */
    @Excel(name = "往年欠缴税款")
    private BigDecimal previousTax;

    /** 滞纳金 */
    @Excel(name = "滞纳金")
    private BigDecimal overdueFee;

    /** 联系地址 */
    @Excel(name = "联系地址")
    private String address;

    /** 电话 */
    @Excel(name = "电话")
    private String tel;

    /** 应缴纳总金额 */
    @Excel(name = "应缴纳总金额")
    private BigDecimal totalTaxAmount;

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
    public void setTaxPayerNo(String taxPayerNo) 
    {
        this.taxPayerNo = taxPayerNo;
    }

    public String getTaxPayerNo() 
    {
        return taxPayerNo;
    }
    public void setTaxPayerName(String taxPayerName) 
    {
        this.taxPayerName = taxPayerName;
    }

    public String getTaxPayerName() 
    {
        return taxPayerName;
    }
    public void setStdTax(BigDecimal stdTax) 
    {
        this.stdTax = stdTax;
    }

    public BigDecimal getStdTax() 
    {
        return stdTax;
    }
    public void setCurrentTax(BigDecimal currentTax) 
    {
        this.currentTax = currentTax;
    }

    public BigDecimal getCurrentTax() 
    {
        return currentTax;
    }
    public void setPreviousTax(BigDecimal previousTax) 
    {
        this.previousTax = previousTax;
    }

    public BigDecimal getPreviousTax() 
    {
        return previousTax;
    }
    public void setOverdueFee(BigDecimal overdueFee) 
    {
        this.overdueFee = overdueFee;
    }

    public BigDecimal getOverdueFee() 
    {
        return overdueFee;
    }
    public void setAddress(String address) 
    {
        this.address = address;
    }

    public String getAddress() 
    {
        return address;
    }
    public void setTel(String tel) 
    {
        this.tel = tel;
    }

    public String getTel() 
    {
        return tel;
    }
    public void setTotalTaxAmount(BigDecimal totalTaxAmount) 
    {
        this.totalTaxAmount = totalTaxAmount;
    }

    public BigDecimal getTotalTaxAmount() 
    {
        return totalTaxAmount;
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
            .append("taxPayerNo", getTaxPayerNo())
            .append("taxPayerName", getTaxPayerName())
            .append("stdTax", getStdTax())
            .append("currentTax", getCurrentTax())
            .append("previousTax", getPreviousTax())
            .append("overdueFee", getOverdueFee())
            .append("address", getAddress())
            .append("tel", getTel())
            .append("totalTaxAmount", getTotalTaxAmount())
            .append("version", getVersion())
            .append("insertTime", getInsertTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}

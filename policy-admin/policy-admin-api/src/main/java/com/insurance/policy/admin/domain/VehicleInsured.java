package com.insurance.policy.admin.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 被保险车辆信息对象 t_vehicle_insured
 * 
 * @author ruoyi
 * @date 2020-08-12
 */
public class VehicleInsured extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 自增主键 */
    private Long id;

    /** 保单/投保单主键 */
    @Excel(name = "保单/投保单主键")
    private Long policyId;

    /** 号牌号码 */
    @Excel(name = "号牌号码")
    private String licenseNo;

    /** 号牌种类 */
    @Excel(name = "号牌种类")
    private String licenseType;

    /** 车辆种类代码 */
    @Excel(name = "车辆种类代码")
    private String vehicleKind;

    /** 车辆使用性质代码 */
    @Excel(name = "车辆使用性质代码")
    private String vehicleUseNature;

    /** 车辆初次登记日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "车辆初次登记日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date vehicleFirstRegDate;

    /** 排量（单位：L） */
    @Excel(name = "排量", readConverterExp = "单=位：L")
    private BigDecimal displacement;

    /** 核定载客数量 */
    @Excel(name = "核定载客数量")
    private Integer approvedSeatCount;

    /** 车架号 */
    @Excel(name = "车架号")
    private String vin;

    /** 发动机号 */
    @Excel(name = "发动机号")
    private String engineNo;

    /** 燃料种类 */
    @Excel(name = "燃料种类")
    private String gasType;

    /** 新车购置价 */
    @Excel(name = "新车购置价")
    private BigDecimal newVehiclePrice;

    /** 厂牌型号 */
    @Excel(name = "厂牌型号")
    private String vehicleFactoryName;

    /** 行业车型代码 */
    @Excel(name = "行业车型代码")
    private String industryVehicleCode;

    /** 功率 */
    @Excel(name = "功率")
    private BigDecimal power;

    /** 整备质量 */
    @Excel(name = "整备质量")
    private Integer vehicleQuality;

    /** 版本号 */
    @Excel(name = "版本号")
    private Long version;

    /** 验车照片地址，用逗号分隔 */
    @Excel(name = "验车照片地址，用逗号分隔")
    private String imgUrls;

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
    public void setLicenseNo(String licenseNo) 
    {
        this.licenseNo = licenseNo;
    }

    public String getLicenseNo() 
    {
        return licenseNo;
    }
    public void setLicenseType(String licenseType) 
    {
        this.licenseType = licenseType;
    }

    public String getLicenseType() 
    {
        return licenseType;
    }
    public void setVehicleKind(String vehicleKind) 
    {
        this.vehicleKind = vehicleKind;
    }

    public String getVehicleKind() 
    {
        return vehicleKind;
    }
    public void setVehicleUseNature(String vehicleUseNature) 
    {
        this.vehicleUseNature = vehicleUseNature;
    }

    public String getVehicleUseNature() 
    {
        return vehicleUseNature;
    }
    public void setVehicleFirstRegDate(Date vehicleFirstRegDate) 
    {
        this.vehicleFirstRegDate = vehicleFirstRegDate;
    }

    public Date getVehicleFirstRegDate() 
    {
        return vehicleFirstRegDate;
    }
    public void setDisplacement(BigDecimal displacement) 
    {
        this.displacement = displacement;
    }

    public BigDecimal getDisplacement() 
    {
        return displacement;
    }
    public void setApprovedSeatCount(Integer approvedSeatCount) 
    {
        this.approvedSeatCount = approvedSeatCount;
    }

    public Integer getApprovedSeatCount() 
    {
        return approvedSeatCount;
    }
    public void setVin(String vin) 
    {
        this.vin = vin;
    }

    public String getVin() 
    {
        return vin;
    }
    public void setEngineNo(String engineNo) 
    {
        this.engineNo = engineNo;
    }

    public String getEngineNo() 
    {
        return engineNo;
    }
    public void setGasType(String gasType) 
    {
        this.gasType = gasType;
    }

    public String getGasType() 
    {
        return gasType;
    }
    public void setNewVehiclePrice(BigDecimal newVehiclePrice) 
    {
        this.newVehiclePrice = newVehiclePrice;
    }

    public BigDecimal getNewVehiclePrice() 
    {
        return newVehiclePrice;
    }
    public void setVehicleFactoryName(String vehicleFactoryName) 
    {
        this.vehicleFactoryName = vehicleFactoryName;
    }

    public String getVehicleFactoryName() 
    {
        return vehicleFactoryName;
    }
    public void setIndustryVehicleCode(String industryVehicleCode) 
    {
        this.industryVehicleCode = industryVehicleCode;
    }

    public String getIndustryVehicleCode() 
    {
        return industryVehicleCode;
    }
    public void setPower(BigDecimal power) 
    {
        this.power = power;
    }

    public BigDecimal getPower() 
    {
        return power;
    }
    public void setVehicleQuality(Integer vehicleQuality) 
    {
        this.vehicleQuality = vehicleQuality;
    }

    public Integer getVehicleQuality() 
    {
        return vehicleQuality;
    }
    public void setVersion(Long version) 
    {
        this.version = version;
    }

    public Long getVersion() 
    {
        return version;
    }
    public void setImgUrls(String imgUrls) 
    {
        this.imgUrls = imgUrls;
    }

    public String getImgUrls() 
    {
        return imgUrls;
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
            .append("licenseNo", getLicenseNo())
            .append("licenseType", getLicenseType())
            .append("vehicleKind", getVehicleKind())
            .append("vehicleUseNature", getVehicleUseNature())
            .append("vehicleFirstRegDate", getVehicleFirstRegDate())
            .append("displacement", getDisplacement())
            .append("approvedSeatCount", getApprovedSeatCount())
            .append("vin", getVin())
            .append("engineNo", getEngineNo())
            .append("gasType", getGasType())
            .append("newVehiclePrice", getNewVehiclePrice())
            .append("vehicleFactoryName", getVehicleFactoryName())
            .append("industryVehicleCode", getIndustryVehicleCode())
            .append("power", getPower())
            .append("vehicleQuality", getVehicleQuality())
            .append("version", getVersion())
            .append("imgUrls", getImgUrls())
            .append("insertTime", getInsertTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}

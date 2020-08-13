package com.insurance.policy.admin.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 投保单客户信息（投保人，被保人，车主）对象 t_vehicle_customer
 * 
 * @author ruoyi
 * @date 2020-08-12
 */
public class VehicleCustomer extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 自增主键 */
    private Long id;

    /** 保单/投保单表的主键 */
    @Excel(name = "保单/投保单表的主键")
    private Long policyId;

    /** 证件号 */
    @Excel(name = "证件号")
    private String idNo;

    /** 证件类型代码 */
    @Excel(name = "证件类型代码")
    private String idType;

    /** 客户名称 */
    @Excel(name = "客户名称")
    private String userName;

    /** 客户角色（1-车主，2-投保人，3-被保人） */
    @Excel(name = "客户角色", readConverterExp = "1=-车主，2-投保人，3-被保人")
    private String userRoleType;

    /** 电话 */
    @Excel(name = "电话")
    private String cellphone;

    /** 联系地址 */
    @Excel(name = "联系地址")
    private String address;

    /** 客户类型：1-个人 2-企业 */
    @Excel(name = "客户类型：1-个人 2-企业")
    private String customerType;

    /** 邮箱地址 */
    @Excel(name = "邮箱地址")
    private String email;

    /** 版本号 */
    @Excel(name = "版本号")
    private Long version;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date insertTime;

    /** 性别1-男，2-女 */
    @Excel(name = "性别1-男，2-女")
    private String gender;

    /** 1-投保人同车主 , 2-被保人投保人 */
    @Excel(name = "1-投保人同车主 , 2-被保人投保人")
    private String relation;

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
    public void setIdNo(String idNo) 
    {
        this.idNo = idNo;
    }

    public String getIdNo() 
    {
        return idNo;
    }
    public void setIdType(String idType) 
    {
        this.idType = idType;
    }

    public String getIdType() 
    {
        return idType;
    }
    public void setUserName(String userName) 
    {
        this.userName = userName;
    }

    public String getUserName() 
    {
        return userName;
    }
    public void setUserRoleType(String userRoleType) 
    {
        this.userRoleType = userRoleType;
    }

    public String getUserRoleType() 
    {
        return userRoleType;
    }
    public void setCellphone(String cellphone) 
    {
        this.cellphone = cellphone;
    }

    public String getCellphone() 
    {
        return cellphone;
    }
    public void setAddress(String address) 
    {
        this.address = address;
    }

    public String getAddress() 
    {
        return address;
    }
    public void setCustomerType(String customerType) 
    {
        this.customerType = customerType;
    }

    public String getCustomerType() 
    {
        return customerType;
    }
    public void setEmail(String email) 
    {
        this.email = email;
    }

    public String getEmail() 
    {
        return email;
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
    public void setGender(String gender) 
    {
        this.gender = gender;
    }

    public String getGender() 
    {
        return gender;
    }
    public void setRelation(String relation) 
    {
        this.relation = relation;
    }

    public String getRelation() 
    {
        return relation;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("policyId", getPolicyId())
            .append("idNo", getIdNo())
            .append("idType", getIdType())
            .append("userName", getUserName())
            .append("userRoleType", getUserRoleType())
            .append("cellphone", getCellphone())
            .append("address", getAddress())
            .append("customerType", getCustomerType())
            .append("email", getEmail())
            .append("version", getVersion())
            .append("insertTime", getInsertTime())
            .append("updateTime", getUpdateTime())
            .append("gender", getGender())
            .append("relation", getRelation())
            .toString();
    }
}

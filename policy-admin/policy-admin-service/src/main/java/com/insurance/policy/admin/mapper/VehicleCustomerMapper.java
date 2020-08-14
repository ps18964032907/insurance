package com.insurance.policy.admin.mapper;

import java.util.List;
import com.insurance.policy.admin.domain.VehicleCustomer;
import org.springframework.stereotype.Repository;

/**
 * 投保单客户信息（投保人，被保人，车主）Mapper接口
 * 
 * @author ruoyi
 * @date 2020-08-12
 */
@Repository
public interface VehicleCustomerMapper 
{
    /**
     * 查询投保单客户信息（投保人，被保人，车主）
     * 
     * @param id 投保单客户信息（投保人，被保人，车主）ID
     * @return 投保单客户信息（投保人，被保人，车主）
     */
    public VehicleCustomer selectVehicleCustomerById(Long id);

    /**
     * 查询投保单客户信息（投保人，被保人，车主）列表
     * 
     * @param vehicleCustomer 投保单客户信息（投保人，被保人，车主）
     * @return 投保单客户信息（投保人，被保人，车主）集合
     */
    public List<VehicleCustomer> selectVehicleCustomerList(VehicleCustomer vehicleCustomer);

    /**
     * 新增投保单客户信息（投保人，被保人，车主）
     * 
     * @param vehicleCustomer 投保单客户信息（投保人，被保人，车主）
     * @return 结果
     */
    public int insertVehicleCustomer(VehicleCustomer vehicleCustomer);

    /**
     * 修改投保单客户信息（投保人，被保人，车主）
     * 
     * @param vehicleCustomer 投保单客户信息（投保人，被保人，车主）
     * @return 结果
     */
    public int updateVehicleCustomer(VehicleCustomer vehicleCustomer);

    /**
     * 删除投保单客户信息（投保人，被保人，车主）
     * 
     * @param id 投保单客户信息（投保人，被保人，车主）ID
     * @return 结果
     */
    public int deleteVehicleCustomerById(Long id);

    /**
     * 批量删除投保单客户信息（投保人，被保人，车主）
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteVehicleCustomerByIds(Long[] ids);

    int insertVehicleCustomers(List<VehicleCustomer> compulsoryVehicleCustomerInfos);
}

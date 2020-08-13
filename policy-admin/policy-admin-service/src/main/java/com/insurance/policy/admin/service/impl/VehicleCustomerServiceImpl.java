package com.insurance.policy.admin.service.impl;

import java.util.List;
import com.ruoyi.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.insurance.policy.admin.mapper.VehicleCustomerMapper;
import com.insurance.policy.admin.domain.VehicleCustomer;
import com.insurance.policy.admin.service.IVehicleCustomerService;

/**
 * 投保单客户信息（投保人，被保人，车主）Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-08-12
 */
@Service
public class VehicleCustomerServiceImpl implements IVehicleCustomerService 
{
    @Autowired
    private VehicleCustomerMapper vehicleCustomerMapper;

    /**
     * 查询投保单客户信息（投保人，被保人，车主）
     * 
     * @param id 投保单客户信息（投保人，被保人，车主）ID
     * @return 投保单客户信息（投保人，被保人，车主）
     */
    @Override
    public VehicleCustomer selectVehicleCustomerById(Long id)
    {
        return vehicleCustomerMapper.selectVehicleCustomerById(id);
    }

    /**
     * 查询投保单客户信息（投保人，被保人，车主）列表
     * 
     * @param vehicleCustomer 投保单客户信息（投保人，被保人，车主）
     * @return 投保单客户信息（投保人，被保人，车主）
     */
    @Override
    public List<VehicleCustomer> selectVehicleCustomerList(VehicleCustomer vehicleCustomer)
    {
        return vehicleCustomerMapper.selectVehicleCustomerList(vehicleCustomer);
    }

    /**
     * 新增投保单客户信息（投保人，被保人，车主）
     * 
     * @param vehicleCustomer 投保单客户信息（投保人，被保人，车主）
     * @return 结果
     */
    @Override
    public int insertVehicleCustomer(VehicleCustomer vehicleCustomer)
    {
        return vehicleCustomerMapper.insertVehicleCustomer(vehicleCustomer);
    }

    /**
     * 修改投保单客户信息（投保人，被保人，车主）
     * 
     * @param vehicleCustomer 投保单客户信息（投保人，被保人，车主）
     * @return 结果
     */
    @Override
    public int updateVehicleCustomer(VehicleCustomer vehicleCustomer)
    {
        vehicleCustomer.setUpdateTime(DateUtils.getNowDate());
        return vehicleCustomerMapper.updateVehicleCustomer(vehicleCustomer);
    }

    /**
     * 批量删除投保单客户信息（投保人，被保人，车主）
     * 
     * @param ids 需要删除的投保单客户信息（投保人，被保人，车主）ID
     * @return 结果
     */
    @Override
    public int deleteVehicleCustomerByIds(Long[] ids)
    {
        return vehicleCustomerMapper.deleteVehicleCustomerByIds(ids);
    }

    /**
     * 删除投保单客户信息（投保人，被保人，车主）信息
     * 
     * @param id 投保单客户信息（投保人，被保人，车主）ID
     * @return 结果
     */
    @Override
    public int deleteVehicleCustomerById(Long id)
    {
        return vehicleCustomerMapper.deleteVehicleCustomerById(id);
    }
}

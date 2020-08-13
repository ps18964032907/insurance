package com.insurance.policy.admin.service.impl;

import java.util.List;
import com.ruoyi.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.insurance.policy.admin.mapper.VehicleTaxMapper;
import com.insurance.policy.admin.domain.VehicleTax;
import com.insurance.policy.admin.service.IVehicleTaxService;

/**
 * 车船税信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-08-12
 */
@Service
public class VehicleTaxServiceImpl implements IVehicleTaxService 
{
    @Autowired
    private VehicleTaxMapper vehicleTaxMapper;

    /**
     * 查询车船税信息
     * 
     * @param id 车船税信息ID
     * @return 车船税信息
     */
    @Override
    public VehicleTax selectVehicleTaxById(Long id)
    {
        return vehicleTaxMapper.selectVehicleTaxById(id);
    }

    /**
     * 查询车船税信息列表
     * 
     * @param vehicleTax 车船税信息
     * @return 车船税信息
     */
    @Override
    public List<VehicleTax> selectVehicleTaxList(VehicleTax vehicleTax)
    {
        return vehicleTaxMapper.selectVehicleTaxList(vehicleTax);
    }

    /**
     * 新增车船税信息
     * 
     * @param vehicleTax 车船税信息
     * @return 结果
     */
    @Override
    public int insertVehicleTax(VehicleTax vehicleTax)
    {
        return vehicleTaxMapper.insertVehicleTax(vehicleTax);
    }

    /**
     * 修改车船税信息
     * 
     * @param vehicleTax 车船税信息
     * @return 结果
     */
    @Override
    public int updateVehicleTax(VehicleTax vehicleTax)
    {
        vehicleTax.setUpdateTime(DateUtils.getNowDate());
        return vehicleTaxMapper.updateVehicleTax(vehicleTax);
    }

    /**
     * 批量删除车船税信息
     * 
     * @param ids 需要删除的车船税信息ID
     * @return 结果
     */
    @Override
    public int deleteVehicleTaxByIds(Long[] ids)
    {
        return vehicleTaxMapper.deleteVehicleTaxByIds(ids);
    }

    /**
     * 删除车船税信息信息
     * 
     * @param id 车船税信息ID
     * @return 结果
     */
    @Override
    public int deleteVehicleTaxById(Long id)
    {
        return vehicleTaxMapper.deleteVehicleTaxById(id);
    }
}

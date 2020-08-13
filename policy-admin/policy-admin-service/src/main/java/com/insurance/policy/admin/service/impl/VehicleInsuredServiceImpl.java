package com.insurance.policy.admin.service.impl;

import java.util.List;
import com.ruoyi.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.insurance.policy.admin.mapper.VehicleInsuredMapper;
import com.insurance.policy.admin.domain.VehicleInsured;
import com.insurance.policy.admin.service.IVehicleInsuredService;

/**
 * 被保险车辆信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-08-12
 */
@Service
public class VehicleInsuredServiceImpl implements IVehicleInsuredService 
{
    @Autowired
    private VehicleInsuredMapper vehicleInsuredMapper;

    /**
     * 查询被保险车辆信息
     * 
     * @param id 被保险车辆信息ID
     * @return 被保险车辆信息
     */
    @Override
    public VehicleInsured selectVehicleInsuredById(Long id)
    {
        return vehicleInsuredMapper.selectVehicleInsuredById(id);
    }

    /**
     * 查询被保险车辆信息列表
     * 
     * @param vehicleInsured 被保险车辆信息
     * @return 被保险车辆信息
     */
    @Override
    public List<VehicleInsured> selectVehicleInsuredList(VehicleInsured vehicleInsured)
    {
        return vehicleInsuredMapper.selectVehicleInsuredList(vehicleInsured);
    }

    /**
     * 新增被保险车辆信息
     * 
     * @param vehicleInsured 被保险车辆信息
     * @return 结果
     */
    @Override
    public int insertVehicleInsured(VehicleInsured vehicleInsured)
    {
        return vehicleInsuredMapper.insertVehicleInsured(vehicleInsured);
    }

    /**
     * 修改被保险车辆信息
     * 
     * @param vehicleInsured 被保险车辆信息
     * @return 结果
     */
    @Override
    public int updateVehicleInsured(VehicleInsured vehicleInsured)
    {
        vehicleInsured.setUpdateTime(DateUtils.getNowDate());
        return vehicleInsuredMapper.updateVehicleInsured(vehicleInsured);
    }

    /**
     * 批量删除被保险车辆信息
     * 
     * @param ids 需要删除的被保险车辆信息ID
     * @return 结果
     */
    @Override
    public int deleteVehicleInsuredByIds(Long[] ids)
    {
        return vehicleInsuredMapper.deleteVehicleInsuredByIds(ids);
    }

    /**
     * 删除被保险车辆信息信息
     * 
     * @param id 被保险车辆信息ID
     * @return 结果
     */
    @Override
    public int deleteVehicleInsuredById(Long id)
    {
        return vehicleInsuredMapper.deleteVehicleInsuredById(id);
    }
}

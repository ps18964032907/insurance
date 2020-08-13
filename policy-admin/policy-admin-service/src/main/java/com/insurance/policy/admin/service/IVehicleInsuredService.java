package com.insurance.policy.admin.service;

import java.util.List;
import com.insurance.policy.admin.domain.VehicleInsured;

/**
 * 被保险车辆信息Service接口
 * 
 * @author ruoyi
 * @date 2020-08-12
 */
public interface IVehicleInsuredService 
{
    /**
     * 查询被保险车辆信息
     * 
     * @param id 被保险车辆信息ID
     * @return 被保险车辆信息
     */
    public VehicleInsured selectVehicleInsuredById(Long id);

    /**
     * 查询被保险车辆信息列表
     * 
     * @param vehicleInsured 被保险车辆信息
     * @return 被保险车辆信息集合
     */
    public List<VehicleInsured> selectVehicleInsuredList(VehicleInsured vehicleInsured);

    /**
     * 新增被保险车辆信息
     * 
     * @param vehicleInsured 被保险车辆信息
     * @return 结果
     */
    public int insertVehicleInsured(VehicleInsured vehicleInsured);

    /**
     * 修改被保险车辆信息
     * 
     * @param vehicleInsured 被保险车辆信息
     * @return 结果
     */
    public int updateVehicleInsured(VehicleInsured vehicleInsured);

    /**
     * 批量删除被保险车辆信息
     * 
     * @param ids 需要删除的被保险车辆信息ID
     * @return 结果
     */
    public int deleteVehicleInsuredByIds(Long[] ids);

    /**
     * 删除被保险车辆信息信息
     * 
     * @param id 被保险车辆信息ID
     * @return 结果
     */
    public int deleteVehicleInsuredById(Long id);
}

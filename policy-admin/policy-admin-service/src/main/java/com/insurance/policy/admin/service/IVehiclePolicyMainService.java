package com.insurance.policy.admin.service;

import java.util.List;
import com.insurance.policy.admin.domain.VehiclePolicyMain;

/**
 * 保单主要信息Service接口
 * 
 * @author ruoyi
 * @date 2020-08-12
 */
public interface IVehiclePolicyMainService 
{
    /**
     * 查询保单主要信息
     * 
     * @param id 保单主要信息ID
     * @return 保单主要信息
     */
    public VehiclePolicyMain selectVehiclePolicyMainById(Long id);

    /**
     * 查询保单主要信息列表
     * 
     * @param vehiclePolicyMain 保单主要信息
     * @return 保单主要信息集合
     */
    public List<VehiclePolicyMain> selectVehiclePolicyMainList(VehiclePolicyMain vehiclePolicyMain);

    /**
     * 新增保单主要信息
     * 
     * @param vehiclePolicyMain 保单主要信息
     * @return 结果
     */
    public int insertVehiclePolicyMain(VehiclePolicyMain vehiclePolicyMain);

    /**
     * 修改保单主要信息
     * 
     * @param vehiclePolicyMain 保单主要信息
     * @return 结果
     */
    public int updateVehiclePolicyMain(VehiclePolicyMain vehiclePolicyMain);

    /**
     * 批量删除保单主要信息
     * 
     * @param ids 需要删除的保单主要信息ID
     * @return 结果
     */
    public int deleteVehiclePolicyMainByIds(Long[] ids);

    /**
     * 删除保单主要信息信息
     * 
     * @param id 保单主要信息ID
     * @return 结果
     */
    public int deleteVehiclePolicyMainById(Long id);
}

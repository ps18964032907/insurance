package com.insurance.policy.admin.service.impl;

import java.util.List;
import com.ruoyi.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.insurance.policy.admin.mapper.VehiclePolicyMainMapper;
import com.insurance.policy.admin.domain.VehiclePolicyMain;
import com.insurance.policy.admin.service.IVehiclePolicyMainService;

/**
 * 保单主要信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-08-12
 */
@Service
public class VehiclePolicyMainServiceImpl implements IVehiclePolicyMainService 
{
    @Autowired
    private VehiclePolicyMainMapper vehiclePolicyMainMapper;

    /**
     * 查询保单主要信息
     * 
     * @param id 保单主要信息ID
     * @return 保单主要信息
     */
    @Override
    public VehiclePolicyMain selectVehiclePolicyMainById(Long id)
    {
        return vehiclePolicyMainMapper.selectVehiclePolicyMainById(id);
    }

    /**
     * 查询保单主要信息列表
     * 
     * @param vehiclePolicyMain 保单主要信息
     * @return 保单主要信息
     */
    @Override
    public List<VehiclePolicyMain> selectVehiclePolicyMainList(VehiclePolicyMain vehiclePolicyMain)
    {
        return vehiclePolicyMainMapper.selectVehiclePolicyMainList(vehiclePolicyMain);
    }

    /**
     * 新增保单主要信息
     * 
     * @param vehiclePolicyMain 保单主要信息
     * @return 结果
     */
    @Override
    public int insertVehiclePolicyMain(VehiclePolicyMain vehiclePolicyMain)
    {
        return vehiclePolicyMainMapper.insertVehiclePolicyMain(vehiclePolicyMain);
    }

    /**
     * 修改保单主要信息
     * 
     * @param vehiclePolicyMain 保单主要信息
     * @return 结果
     */
    @Override
    public int updateVehiclePolicyMain(VehiclePolicyMain vehiclePolicyMain)
    {
        vehiclePolicyMain.setUpdateTime(DateUtils.getNowDate());
        return vehiclePolicyMainMapper.updateVehiclePolicyMain(vehiclePolicyMain);
    }

    /**
     * 批量删除保单主要信息
     * 
     * @param ids 需要删除的保单主要信息ID
     * @return 结果
     */
    @Override
    public int deleteVehiclePolicyMainByIds(Long[] ids)
    {
        return vehiclePolicyMainMapper.deleteVehiclePolicyMainByIds(ids);
    }

    /**
     * 删除保单主要信息信息
     * 
     * @param id 保单主要信息ID
     * @return 结果
     */
    @Override
    public int deleteVehiclePolicyMainById(Long id)
    {
        return vehiclePolicyMainMapper.deleteVehiclePolicyMainById(id);
    }
}

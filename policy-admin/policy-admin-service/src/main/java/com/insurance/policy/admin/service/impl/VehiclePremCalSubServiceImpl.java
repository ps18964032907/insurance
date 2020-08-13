package com.insurance.policy.admin.service.impl;

import java.util.List;
import com.ruoyi.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.insurance.policy.admin.mapper.VehiclePremCalSubMapper;
import com.insurance.policy.admin.domain.VehiclePremCalSub;
import com.insurance.policy.admin.service.IVehiclePremCalSubService;

/**
 * 保费计算附加信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-08-12
 */
@Service
public class VehiclePremCalSubServiceImpl implements IVehiclePremCalSubService 
{
    @Autowired
    private VehiclePremCalSubMapper vehiclePremCalSubMapper;

    /**
     * 查询保费计算附加信息
     * 
     * @param id 保费计算附加信息ID
     * @return 保费计算附加信息
     */
    @Override
    public VehiclePremCalSub selectVehiclePremCalSubById(Long id)
    {
        return vehiclePremCalSubMapper.selectVehiclePremCalSubById(id);
    }

    /**
     * 查询保费计算附加信息列表
     * 
     * @param vehiclePremCalSub 保费计算附加信息
     * @return 保费计算附加信息
     */
    @Override
    public List<VehiclePremCalSub> selectVehiclePremCalSubList(VehiclePremCalSub vehiclePremCalSub)
    {
        return vehiclePremCalSubMapper.selectVehiclePremCalSubList(vehiclePremCalSub);
    }

    /**
     * 新增保费计算附加信息
     * 
     * @param vehiclePremCalSub 保费计算附加信息
     * @return 结果
     */
    @Override
    public int insertVehiclePremCalSub(VehiclePremCalSub vehiclePremCalSub)
    {
        return vehiclePremCalSubMapper.insertVehiclePremCalSub(vehiclePremCalSub);
    }

    /**
     * 修改保费计算附加信息
     * 
     * @param vehiclePremCalSub 保费计算附加信息
     * @return 结果
     */
    @Override
    public int updateVehiclePremCalSub(VehiclePremCalSub vehiclePremCalSub)
    {
        vehiclePremCalSub.setUpdateTime(DateUtils.getNowDate());
        return vehiclePremCalSubMapper.updateVehiclePremCalSub(vehiclePremCalSub);
    }

    /**
     * 批量删除保费计算附加信息
     * 
     * @param ids 需要删除的保费计算附加信息ID
     * @return 结果
     */
    @Override
    public int deleteVehiclePremCalSubByIds(Long[] ids)
    {
        return vehiclePremCalSubMapper.deleteVehiclePremCalSubByIds(ids);
    }

    /**
     * 删除保费计算附加信息信息
     * 
     * @param id 保费计算附加信息ID
     * @return 结果
     */
    @Override
    public int deleteVehiclePremCalSubById(Long id)
    {
        return vehiclePremCalSubMapper.deleteVehiclePremCalSubById(id);
    }
}

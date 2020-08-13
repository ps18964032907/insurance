package com.insurance.policy.admin.service;

import java.util.List;
import com.insurance.policy.admin.domain.VehiclePremCalSub;

/**
 * 保费计算附加信息Service接口
 * 
 * @author ruoyi
 * @date 2020-08-12
 */
public interface IVehiclePremCalSubService 
{
    /**
     * 查询保费计算附加信息
     * 
     * @param id 保费计算附加信息ID
     * @return 保费计算附加信息
     */
    public VehiclePremCalSub selectVehiclePremCalSubById(Long id);

    /**
     * 查询保费计算附加信息列表
     * 
     * @param vehiclePremCalSub 保费计算附加信息
     * @return 保费计算附加信息集合
     */
    public List<VehiclePremCalSub> selectVehiclePremCalSubList(VehiclePremCalSub vehiclePremCalSub);

    /**
     * 新增保费计算附加信息
     * 
     * @param vehiclePremCalSub 保费计算附加信息
     * @return 结果
     */
    public int insertVehiclePremCalSub(VehiclePremCalSub vehiclePremCalSub);

    /**
     * 修改保费计算附加信息
     * 
     * @param vehiclePremCalSub 保费计算附加信息
     * @return 结果
     */
    public int updateVehiclePremCalSub(VehiclePremCalSub vehiclePremCalSub);

    /**
     * 批量删除保费计算附加信息
     * 
     * @param ids 需要删除的保费计算附加信息ID
     * @return 结果
     */
    public int deleteVehiclePremCalSubByIds(Long[] ids);

    /**
     * 删除保费计算附加信息信息
     * 
     * @param id 保费计算附加信息ID
     * @return 结果
     */
    public int deleteVehiclePremCalSubById(Long id);
}

package com.insurance.policy.admin.mapper;

import java.util.List;
import com.insurance.policy.admin.domain.VehicleTax;

/**
 * 车船税信息Mapper接口
 * 
 * @author ruoyi
 * @date 2020-08-12
 */
public interface VehicleTaxMapper 
{
    /**
     * 查询车船税信息
     * 
     * @param id 车船税信息ID
     * @return 车船税信息
     */
    public VehicleTax selectVehicleTaxById(Long id);

    /**
     * 查询车船税信息列表
     * 
     * @param vehicleTax 车船税信息
     * @return 车船税信息集合
     */
    public List<VehicleTax> selectVehicleTaxList(VehicleTax vehicleTax);

    /**
     * 新增车船税信息
     * 
     * @param vehicleTax 车船税信息
     * @return 结果
     */
    public int insertVehicleTax(VehicleTax vehicleTax);

    /**
     * 修改车船税信息
     * 
     * @param vehicleTax 车船税信息
     * @return 结果
     */
    public int updateVehicleTax(VehicleTax vehicleTax);

    /**
     * 删除车船税信息
     * 
     * @param id 车船税信息ID
     * @return 结果
     */
    public int deleteVehicleTaxById(Long id);

    /**
     * 批量删除车船税信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteVehicleTaxByIds(Long[] ids);
}

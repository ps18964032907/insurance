package com.insurance.policy.admin.mapper;

import java.util.List;
import com.insurance.policy.admin.domain.VehicleCoverage;

/**
 * 险种责任信息Mapper接口
 * 
 * @author ruoyi
 * @date 2020-08-12
 */
public interface VehicleCoverageMapper 
{
    /**
     * 查询险种责任信息
     * 
     * @param id 险种责任信息ID
     * @return 险种责任信息
     */
    public VehicleCoverage selectVehicleCoverageById(Long id);

    /**
     * 查询险种责任信息列表
     * 
     * @param vehicleCoverage 险种责任信息
     * @return 险种责任信息集合
     */
    public List<VehicleCoverage> selectVehicleCoverageList(VehicleCoverage vehicleCoverage);

    /**
     * 新增险种责任信息
     * 
     * @param vehicleCoverage 险种责任信息
     * @return 结果
     */
    public int insertVehicleCoverage(VehicleCoverage vehicleCoverage);

    /**
     * 修改险种责任信息
     * 
     * @param vehicleCoverage 险种责任信息
     * @return 结果
     */
    public int updateVehicleCoverage(VehicleCoverage vehicleCoverage);

    /**
     * 删除险种责任信息
     * 
     * @param id 险种责任信息ID
     * @return 结果
     */
    public int deleteVehicleCoverageById(Long id);

    /**
     * 批量删除险种责任信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteVehicleCoverageByIds(Long[] ids);
}

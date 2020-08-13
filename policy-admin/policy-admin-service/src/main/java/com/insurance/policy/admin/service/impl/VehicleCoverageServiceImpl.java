package com.insurance.policy.admin.service.impl;

import java.util.List;
import com.ruoyi.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.insurance.policy.admin.mapper.VehicleCoverageMapper;
import com.insurance.policy.admin.domain.VehicleCoverage;
import com.insurance.policy.admin.service.IVehicleCoverageService;

/**
 * 险种责任信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-08-12
 */
@Service
public class VehicleCoverageServiceImpl implements IVehicleCoverageService 
{
    @Autowired
    private VehicleCoverageMapper vehicleCoverageMapper;

    /**
     * 查询险种责任信息
     * 
     * @param id 险种责任信息ID
     * @return 险种责任信息
     */
    @Override
    public VehicleCoverage selectVehicleCoverageById(Long id)
    {
        return vehicleCoverageMapper.selectVehicleCoverageById(id);
    }

    /**
     * 查询险种责任信息列表
     * 
     * @param vehicleCoverage 险种责任信息
     * @return 险种责任信息
     */
    @Override
    public List<VehicleCoverage> selectVehicleCoverageList(VehicleCoverage vehicleCoverage)
    {
        return vehicleCoverageMapper.selectVehicleCoverageList(vehicleCoverage);
    }

    /**
     * 新增险种责任信息
     * 
     * @param vehicleCoverage 险种责任信息
     * @return 结果
     */
    @Override
    public int insertVehicleCoverage(VehicleCoverage vehicleCoverage)
    {
        return vehicleCoverageMapper.insertVehicleCoverage(vehicleCoverage);
    }

    /**
     * 修改险种责任信息
     * 
     * @param vehicleCoverage 险种责任信息
     * @return 结果
     */
    @Override
    public int updateVehicleCoverage(VehicleCoverage vehicleCoverage)
    {
        vehicleCoverage.setUpdateTime(DateUtils.getNowDate());
        return vehicleCoverageMapper.updateVehicleCoverage(vehicleCoverage);
    }

    /**
     * 批量删除险种责任信息
     * 
     * @param ids 需要删除的险种责任信息ID
     * @return 结果
     */
    @Override
    public int deleteVehicleCoverageByIds(Long[] ids)
    {
        return vehicleCoverageMapper.deleteVehicleCoverageByIds(ids);
    }

    /**
     * 删除险种责任信息信息
     * 
     * @param id 险种责任信息ID
     * @return 结果
     */
    @Override
    public int deleteVehicleCoverageById(Long id)
    {
        return vehicleCoverageMapper.deleteVehicleCoverageById(id);
    }
}

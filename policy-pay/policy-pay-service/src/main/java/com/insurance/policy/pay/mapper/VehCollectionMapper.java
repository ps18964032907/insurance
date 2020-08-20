package com.insurance.policy.pay.mapper;



import com.insurance.policy.pay.domain.VehicleCollection;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 * 保单收费Mapper接口
 * 
 * @author ruoyi
 * @date 2020-08-16
 */

@Mapper
@Component
public interface VehCollectionMapper
{
    /**
     * 查询保单收费
     * 
     * @param id 保单收费ID
     * @return 保单收费
     */
    public VehicleCollection selectCollectionByPolicyId(Long id);

    /**
     * 查询保单收费列表
     * 
     * @param vehicleCollection 保单收费
     * @return 保单收费集合
     */
    public List<VehicleCollection> selectCollectionList(VehicleCollection vehicleCollection);

    /**
     * 新增保单收费
     * 
     * @param vehicleCollection 保单收费
     * @return 结果
     */
    public int insertCollection(VehicleCollection vehicleCollection);

    /**
     * 修改保单收费
     * 
     * @param vehicleCollection 保单收费
     * @return 结果
     */
    public int updateCollection(VehicleCollection vehicleCollection);

    /**
     * 删除保单收费
     * 
     * @param id 保单收费ID
     * @return 结果
     */
    public int deleteCollectionById(Long id);

    /**
     * 批量删除保单收费
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteCollectionByIds(Long[] ids);
}

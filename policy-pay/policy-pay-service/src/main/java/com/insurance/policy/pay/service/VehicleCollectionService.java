package com.insurance.policy.pay.service;

import com.insurance.policy.pay.domain.VehicleCollection;
import com.insurance.policy.pay.mapper.VehCollectionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author 潘升
 * @Description //TODO $
 * @Date 2020/8/19 10:34
 */
@Service
public class VehicleCollectionService {

    @Autowired
    VehCollectionMapper vehCollectionMapper;

    public int underwriting(VehicleCollection vehicleCollection) {
        try {
            vehCollectionMapper.insertCollection(vehicleCollection);
        }catch (Exception e){
            return 0;
        }
        return 1;
    }


}

package com.insurance.platform.api;

import com.insurance.policy.admin.domain.CommercialPolicy;
import com.insurance.policy.admin.domain.CompulsoryPolicy;
import com.insurance.policy.admin.domain.VehiclePremCalSub;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 监管平台的接口
 */

@FeignClient("insurance-platform-service")
@RequestMapping("/insurance/platform")
public interface InsurancePlatformApi {


    /**
     * 模拟商业险投保查询
     * @param commercialPolicy
     * @return
     */
    @RequestMapping(value = "/commercial/query")
    public VehiclePremCalSub commercialQuery(@RequestBody CommercialPolicy commercialPolicy);


    /**
     * 模拟交强险投保查询
     * @param compulsoryPolicy
     * @return
     */
    @RequestMapping(value = "/compulsory/query")
    public VehiclePremCalSub compulsoryQuery(@RequestBody CompulsoryPolicy compulsoryPolicy);

    /**
     * 模拟商业险投保预确认
     * @param commercialPolicy
     * @return
     */
    @RequestMapping(value = "/commercial/preConfirm")
    public VehiclePremCalSub commercialPreConfirm(@RequestBody CommercialPolicy commercialPolicy);
    /**
     * 模拟交强险投保预确认
     * @param compulsoryPolicy
     * @return
     */
    @RequestMapping(value = "/compulsory/preConfirm")
    public VehiclePremCalSub compulsoryPreConfirm(@RequestBody CompulsoryPolicy compulsoryPolicy);



    /**
     * 模拟商业险保确认
     * @param commercialPolicy
     * @return
     */
    @RequestMapping(value = "/commercial/confirm")
    public VehiclePremCalSub commercialConfirm(@RequestBody CommercialPolicy commercialPolicy);


    /**
     * 模拟交强险投保确认
     * @param compulsoryPolicy
     * @return
     */
    @RequestMapping(value = "/compulsory/confirm")
    public VehiclePremCalSub compulsoryConfirm(@RequestBody CompulsoryPolicy compulsoryPolicy);
}

package com.insurance.platform.controller;

import com.insurance.policy.admin.domain.CommercialPolicy;
import com.insurance.policy.admin.domain.CompulsoryPolicy;
import com.insurance.policy.admin.domain.VehiclePremCalSub;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Date;

@RestController
@RequestMapping("/insurance/platform")
public class InsurancePlatformController {


    /**
     * 模拟商业险投保查询
     * @param commercialPolicy
     * @return
     */
    @RequestMapping(value = "/commercial/query")
    public VehiclePremCalSub commercialQuery(@RequestBody CommercialPolicy commercialPolicy){

        long curr = System.currentTimeMillis();
        long rand = curr%5+1;

        long curr2 = System.currentTimeMillis();
        long rand2 = curr2%3+1;

        VehiclePremCalSub vehiclePremCalSub = new VehiclePremCalSub();
//        vehiclePremCalSub.setPolicyId(commercialPolicy.getVehiclePolicyMain().getId());
        vehiclePremCalSub.setPlatformBenchRiskPrem(new BigDecimal(Math.max(3000,1000*rand)));
        vehiclePremCalSub.setProposalQueryNo("CCLQ"+System.currentTimeMillis());
        vehiclePremCalSub.setProposalQueryNoExpireDate(new Date(2020,9,10));
        vehiclePremCalSub.setTrafficViolationRate(new BigDecimal(Math.max(0.4*rand,1.3)));
        vehiclePremCalSub.setNcdRate(new BigDecimal(Math.max(0.4*rand2,1.3)));
        vehiclePremCalSub.setUwRate(new BigDecimal(1.0));

        return vehiclePremCalSub;
    }


    /**
     * 模拟交强险投保查询
     * @param compulsoryPolicy
     * @return
     */
    @RequestMapping(value = "/compulsory/query")
    public VehiclePremCalSub compulsoryQuery(@RequestBody CompulsoryPolicy compulsoryPolicy){

        long curr = System.currentTimeMillis();
        long rand = curr%5+1;

        long curr2 = System.currentTimeMillis();
        long rand2 = curr2%3+1;

        VehiclePremCalSub vehiclePremCalSub = new VehiclePremCalSub();
//        vehiclePremCalSub.setPolicyId(compulsoryPolicy.getVehiclePolicyMain().getId());
        vehiclePremCalSub.setPlatformBenchRiskPrem(new BigDecimal(Math.max(3000,1000*rand)));
        vehiclePremCalSub.setProposalQueryNo("CPLQ"+System.currentTimeMillis());
        vehiclePremCalSub.setProposalQueryNoExpireDate(new Date(2020,9,10));
        vehiclePremCalSub.setTrafficViolationRate(new BigDecimal(Math.max(0.4*rand,1.3)));
        vehiclePremCalSub.setNcdRate(new BigDecimal(Math.max(0.4*rand2,1.3)));
        vehiclePremCalSub.setUwRate(new BigDecimal(1.0));

        return vehiclePremCalSub;
    }


    /**
     * 模拟商业险投保预确认
     * @param commercialPolicy
     * @return
     */
    @RequestMapping(value = "/commercial/preConfirm")
    public VehiclePremCalSub commercialPreConfirm(@RequestBody CommercialPolicy commercialPolicy){



        VehiclePremCalSub vehiclePremCalSub = new VehiclePremCalSub();

        String proposalQueryNo = commercialPolicy.getVehiclePremCalSub().getProposalQueryNo();

        if(proposalQueryNo==null){
            throw new RuntimeException("投保查询码无效");
        }
        vehiclePremCalSub.setProposalPreConfirmNo("CCLP"+System.currentTimeMillis());
        vehiclePremCalSub.setProposalPreConfirmNoExpireDate(new Date(2020,10,10));


        return vehiclePremCalSub;
    }


    /**
     * 模拟交强险投保预确认
     * @param compulsoryPolicy
     * @return
     */
    @RequestMapping(value = "/compulsory/preConfirm")
    public VehiclePremCalSub compulsoryPreConfirm(@RequestBody CompulsoryPolicy compulsoryPolicy){

        VehiclePremCalSub vehiclePremCalSub = new VehiclePremCalSub();

        String proposalQueryNo = compulsoryPolicy.getVehiclePremCalSub().getProposalQueryNo();

        if(proposalQueryNo==null){
            throw new RuntimeException("投保查询码无效");
        }
        vehiclePremCalSub.setProposalPreConfirmNo("CPLP"+System.currentTimeMillis());
        vehiclePremCalSub.setProposalPreConfirmNoExpireDate(new Date(2020,10,10));


        return vehiclePremCalSub;


    }



    /**
     * 模拟商业险保确认
     * @param commercialPolicy
     * @return
     */
    @RequestMapping(value = "/commercial/confirm")
    public VehiclePremCalSub commercialConfirm(@RequestBody CommercialPolicy commercialPolicy){



        VehiclePremCalSub vehiclePremCalSub = new VehiclePremCalSub();

        String proposalQueryNo = commercialPolicy.getVehiclePremCalSub().getProposalPreConfirmNo();

        if(proposalQueryNo==null){
            throw new RuntimeException("投保预确认码无效");
        }
        vehiclePremCalSub.setProposalConfirmNo("CCLC"+System.currentTimeMillis());


        return vehiclePremCalSub;
    }


    /**
     * 模拟交强险投保确认
     * @param compulsoryPolicy
     * @return
     */
    @RequestMapping(value = "/compulsory/confirm")
    public VehiclePremCalSub compulsoryConfirm(@RequestBody CompulsoryPolicy compulsoryPolicy){

        VehiclePremCalSub vehiclePremCalSub = new VehiclePremCalSub();

        String proposalQueryNo = compulsoryPolicy.getVehiclePremCalSub().getProposalPreConfirmNo();

        if(proposalQueryNo==null){
            throw new RuntimeException("投保预确认码无效");
        }
        vehiclePremCalSub.setProposalConfirmNo("CPLC"+System.currentTimeMillis());


        return vehiclePremCalSub;


    }
}

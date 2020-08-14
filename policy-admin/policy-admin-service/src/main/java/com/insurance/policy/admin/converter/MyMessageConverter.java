package com.insurance.policy.admin.converter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.insurance.policy.admin.controller.VehiclePolicyMainController;
import com.insurance.policy.admin.domain.VehiclePolicyMain;
import com.sun.xml.internal.fastinfoset.util.ValueArray;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import java.io.IOException;

public class MyMessageConverter extends AbstractHttpMessageConverter<VehiclePolicyMain> {


    private ObjectMapper mapper = new ObjectMapper();

    @Override
    protected boolean supports(Class<?> clazz) {
        return clazz.isAssignableFrom(VehiclePolicyMain.class);
    }

    @Override
    protected VehiclePolicyMain readInternal(Class<? extends VehiclePolicyMain> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        VehiclePolicyMain vehiclePolicyMain = mapper.readValue(inputMessage.getBody(), VehiclePolicyMain.class);
        return vehiclePolicyMain;
    }

    @Override
    protected void writeInternal(VehiclePolicyMain vehiclePolicyMain, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
        mapper.writeValue(outputMessage.getBody(),vehiclePolicyMain);
    }
}

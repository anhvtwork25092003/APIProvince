package com.example.demo.mapper.entitytoresponse;

import com.example.demo.dto.response.DistrictResponse;
import com.example.demo.dto.response.WardResponse;
import com.example.demo.entities.District;
import com.example.demo.entities.Ward;
import org.springframework.stereotype.Service;

@Service
public class ConvertWard {
    public WardResponse convertToWardResponse(Ward ward) {
        WardResponse response = new WardResponse();
        response.setIdWard(ward.getIdWard());
        response.setWardName(ward.getWardName());
        response.setIdDistrict(ward.getDistrict().getIdDistrict());
        response.setDistrictName(ward.getDistrict().getDistrictName());
        return response;
    }
}

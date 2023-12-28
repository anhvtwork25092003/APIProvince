package com.example.demo.mapper;

import com.example.demo.dto.response.DistrictResponse;
import com.example.demo.entities.District;
import org.springframework.stereotype.Service;

@Service
public class ConvertDistrict {
    public DistrictResponse convertToDistrictResponse(District district) {
        DistrictResponse response = new DistrictResponse();
        response.setIdDistrict(district.getIdDistrict());
        response.setDistrictName(district.getDistrictName());
        response.setProvinceId(district.getProvince().getIdProvince());
        response.setProvinceName(district.getProvince().getProvinceName());
        return response;
    }
}

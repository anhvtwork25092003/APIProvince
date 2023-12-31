package com.example.demo.mapper.requesttoentity;

import com.example.demo.dto.request.WardRequest;
import com.example.demo.entities.District;
import com.example.demo.entities.Ward;
import com.example.demo.repository.DistrictRepository;
import com.example.demo.repository.WardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WardRequestToWard {

    @Autowired
    WardRepository wardRepository;
    @Autowired
    DistrictRepository districtRepository;


    public Ward convertToWard(WardRequest wardRequest) {
        try {
            District district = this.districtRepository.findById(wardRequest.getIdDistrict()).get();
            Ward ward = new Ward();
            ward.setWardName(wardRequest.getWardName());
            ward.setDistrict(district);
            return ward;
        } catch (Exception e) {
            throw e;
        }
    }
}

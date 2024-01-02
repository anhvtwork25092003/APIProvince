package com.example.demo.service;

import com.example.demo.dto.response.WardResponse;
import com.example.demo.entities.Ward;

import java.util.List;

public interface WardService {
    List<WardResponse> getListWardsByDistrictID(long districtID);
}

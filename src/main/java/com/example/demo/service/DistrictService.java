package com.example.demo.service;

import com.example.demo.dto.response.DistrictResponse;

import java.util.List;

public interface DistrictService {

    List<DistrictResponse> getListDistrictByProvinceName(long provinceId);
}

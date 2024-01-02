package com.example.demo.service;

import com.example.demo.dto.response.ProvinceResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProvinceService {

    Page<ProvinceResponse> listProvinceResponses(Pageable pageable);

    List<ProvinceResponse> allOfProvinceResponses();
}

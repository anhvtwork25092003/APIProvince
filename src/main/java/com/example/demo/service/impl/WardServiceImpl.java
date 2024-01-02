package com.example.demo.service.impl;

import com.example.demo.dto.request.WardRequest;
import com.example.demo.dto.response.WardResponse;
import com.example.demo.entities.Ward;
import com.example.demo.exception.CustomNotFoundException;
import com.example.demo.mapper.entitytoresponse.ConvertWard;
import com.example.demo.mapper.requesttoentity.WardRequestToWard;
import com.example.demo.repository.DistrictRepository;
import com.example.demo.repository.WardRepository;
import com.example.demo.service.CommonService;
import com.example.demo.service.WardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WardServiceImpl implements CommonService<WardResponse, WardRequest>, WardService {

    @Autowired
    ConvertWard convertWard;
    @Autowired
    WardRepository wardRepository;
    @Autowired
    DistrictRepository districtRepository;

    @Autowired
    WardRequestToWard wardRequestToWard;

    @Override
    public Page<WardResponse> getAll(Pageable pageable) {
        try {
            Page<Ward> wardPage = this.wardRepository.findAll(pageable);
            return wardPage.map((convertWard::convertToWardResponse));
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public WardResponse getById(Long id) {
        try {
            Ward ward = this.wardRepository.findById(id).get();
            WardResponse wardResponse = this.convertWard.convertToWardResponse(ward);
            return wardResponse;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public WardResponse create(WardRequest entity) {
        try {
            Ward newWard = this.wardRequestToWard.convertToWard(entity);
            Ward createdWard = this.wardRepository.save(newWard);
            WardResponse wardResponse = this.convertWard.convertToWardResponse(createdWard);
            return wardResponse;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public WardResponse update(Long id, WardRequest entity) {

        try {
            Ward oldWard = this.wardRepository.findById(id).get();
            if (oldWard == null) {
                throw new CustomNotFoundException("Khong ton tai war co id la: " + id);
            }
            Ward newWard = this.wardRequestToWard.convertToWard(entity);
            newWard.setIdWard(id);
            Ward createdWard = this.wardRepository.save(newWard);
            WardResponse wardResponse = this.convertWard.convertToWardResponse(createdWard);
            return wardResponse;
        } catch (CustomNotFoundException customNotFoundException) {
            throw customNotFoundException;
        } catch (Exception exception) {
            throw exception;
        }

    }

    @Override
    public void delete(Long id) {

        try {
            Ward oldWard = this.wardRepository.findById(id).get();
            if (oldWard == null) {
                throw new CustomNotFoundException("Khong ton tai war co id la: " + id);
            }
            this.wardRepository.deleteById(id);
        } catch (CustomNotFoundException customNotFoundException) {
            throw customNotFoundException;
        } catch (Exception e) {
            throw e;
        }

    }

    @Override
    public List<WardResponse> getListWardsByDistrictID(long districtID) {
        try {
            List<Ward> listWards = this.wardRepository.findAllByDistrictIdDistrict(districtID);
            List<WardResponse> wardResponseList = listWards.stream()
                    .map(convertWard::convertToWardResponse)
                    .collect(Collectors.toList());
            return wardResponseList;
        } catch (Exception e) {
            throw e;
        }
    }
}

package com.example.demo.service.impl;

import com.example.demo.dto.request.DistrictRequest;
import com.example.demo.dto.response.DistrictResponse;
import com.example.demo.entities.District;
import com.example.demo.exception.CustomNotFoundException;
import com.example.demo.mapper.ConvertDistrict;
import com.example.demo.repository.DistrictRepository;
import com.example.demo.repository.ProvinceRepository;
import com.example.demo.service.CommonService;
import com.example.demo.service.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public class DistrictServiceImpl implements DistrictService, CommonService<DistrictResponse, DistrictRequest> {

    @Autowired
    DistrictRepository districtRepository;

    @Autowired
    ConvertDistrict districtConverter;


    @Autowired
    ProvinceRepository provinceRepository;

    @Override
    public Page<DistrictResponse> getAll(Pageable pageable) {
        Page<District> districts = this.districtRepository.findAll(pageable);
        return districts.map(districtConverter::convertToDistrictResponse);
    }

    @Override
    public DistrictResponse getById(Long id) {
        District district = districtRepository.findById(id).orElse(null);
        if (district == null) {
            throw new CustomNotFoundException("Không tìm thấy tỉnh với ID: " + id); // hoặc có thể throw một Exception phù hợp với logic của bạn
        }
        return districtConverter.convertToDistrictResponse(district);
    }

    @Override
    public DistrictResponse create(DistrictRequest entity) {
        try {
            District district = new District();
            district.setDistrictName(entity.getDistrictName());
            district.setProvince(this.provinceRepository.findById(entity.getProvinceId()).get());
            District districtCreated = this.districtRepository.save(district);
            return districtConverter.convertToDistrictResponse(districtCreated);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    @Override
    public DistrictResponse update(Long id, DistrictRequest entity) {
        try {
            // lây ra bằng id
            District district = this.districtRepository.findById(id).get();
            if (district == null) {
                throw new CustomNotFoundException("Không tim được quận/huyện có id là: " + id);
            }
            district.setDistrictName(entity.getDistrictName());
            district.setProvince(this.provinceRepository.findById(entity.getProvinceId()).get());
            District districtUpdated = this.districtRepository.save(district);
            return districtConverter.convertToDistrictResponse(districtUpdated);
        } catch (Exception e) {
            throw new RuntimeException();
        }

    }

    @Override
    public void delete(Long id) {
        try {
            // Kiểm tra xem ID có hợp lệ không
            Optional<District> existingProvinceOptional = districtRepository.findById(id);
            if (existingProvinceOptional.isEmpty()) {
                throw new CustomNotFoundException("Không tìm thấy tỉnh với ID: " + id);
            }
            districtRepository.deleteById(id);
        } catch (CustomNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
}

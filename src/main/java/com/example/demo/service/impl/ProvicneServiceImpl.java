package com.example.demo.service.impl;

import com.example.demo.dto.request.ProvinceRequest;
import com.example.demo.dto.response.ProvinceResponse;
import com.example.demo.entities.Province;
import com.example.demo.exception.CustomNotFoundException;
import com.example.demo.repository.ProvinceRepository;
import com.example.demo.service.CommonService;
import com.example.demo.service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProvicneServiceImpl implements CommonService<ProvinceResponse, ProvinceRequest>, ProvinceService {
    @Autowired
    private ProvinceRepository provinceRepository;


    @Override
    public Page<ProvinceResponse> getAll(Pageable pageable) {
        Page<Province> provinces = provinceRepository.findAll(pageable);
        return provinces.map(this::convertToDTO);
    }


    @Override
    public ProvinceResponse getById(Long id) {
        Province province = provinceRepository.findById(id).orElse(null);
        if (province == null) {
            return null; // hoặc có thể throw một Exception phù hợp với logic của bạn
        }
        return convertToDTO(province);
    }

    @Override
    public ProvinceResponse create(ProvinceRequest entity) {
        Province province = new Province();
        province.setProvinceName(entity.getProvinceName());
        Province provinceCheckWithName = this.provinceRepository.findByProvinceName(entity.getProvinceName());
        if (provinceCheckWithName != null) {
            return null;
        } else {
            Province provinceSaved = this.provinceRepository.save(province);
            return convertToDTO(provinceSaved);
        }
    }

    @Override
    public ProvinceResponse update(Long id, ProvinceRequest entity) {
        try {
            // Kiểm tra xem ID có hợp lệ không
            Optional<Province> existingProvinceOptional = provinceRepository.findById(id);
            if (existingProvinceOptional.isEmpty()) {
                throw new CustomNotFoundException("Không tìm thấy tỉnh với ID: " + id);
            }
            Province province = existingProvinceOptional.get();
            province.setProvinceName(entity.getProvinceName());
            return convertToDTO(provinceRepository.save(province));
        } catch (CustomNotFoundException e) {
            throw e; // Re-throw ngoại lệ not found để giữ nguyên loại ngoại lệ
        } catch (Exception e) {
            // Xử lý các ngoại lệ khác và chuyển đổi chúng thành định dạng mong muốn
            throw new RuntimeException("Lỗi khi cập nhật tỉnh thành", e);
        }
    }


    @Override
    public void delete(Long id) {
        try {
            // Kiểm tra xem ID có hợp lệ không
            Optional<Province> existingProvinceOptional = provinceRepository.findById(id);
            if (existingProvinceOptional.isEmpty()) {
                throw new CustomNotFoundException("Không tìm thấy tỉnh với ID: " + id);
            }
            provinceRepository.deleteById(id);
        } catch (CustomNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Lỗi khi xóatỉnh thành", e);
        }
    }

    @Override
    public Page<ProvinceResponse> listProvinceResponses(Pageable pageable) {
        Page<Province> provinces = provinceRepository.findAll(pageable);
        return provinces.map(this::convertToDTO);
    }


    private ProvinceResponse convertToDTO(Province province) {
        // Thực hiện chuyển đổi từ Province sang ProvinceDTO
        ProvinceResponse provinceDTO = new ProvinceResponse();
        provinceDTO.setIdProvince(province.getIdProvince());
        provinceDTO.setProvinceName(province.getProvinceName());
        // Các trường khác...
        return provinceDTO;
    }
}

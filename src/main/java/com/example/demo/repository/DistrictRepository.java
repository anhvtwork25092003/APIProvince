package com.example.demo.repository;

import com.example.demo.entities.District;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DistrictRepository extends JpaRepository<District, Long> {
    Page<District> findAll(Pageable pageable);

    List<District> findAllByProvinceIdProvince(long provinceId);
}

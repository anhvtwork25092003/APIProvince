package com.example.demo.repository;

import com.example.demo.entities.District;
import com.example.demo.entities.Province;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DistrictRepository extends JpaRepository<District, Long> {
}

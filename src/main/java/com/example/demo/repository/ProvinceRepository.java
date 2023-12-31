package com.example.demo.repository;

import com.example.demo.entities.Province;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProvinceRepository extends JpaRepository<Province, Long> {
    Page<Province> findAll(Pageable pageable);

    Province findByProvinceName(String name);

    List<Province> findAll();
}

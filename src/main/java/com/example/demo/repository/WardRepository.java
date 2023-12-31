package com.example.demo.repository;

import com.example.demo.entities.Ward;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface WardRepository extends JpaRepository<Ward, Long> {

    Page<Ward> findAll(Pageable pageable);
    List<Ward> findAllByDistrictIdDistrict(long id);
}

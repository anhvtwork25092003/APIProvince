package com.example.demo.repository;

import com.example.demo.entities.District;
import com.example.demo.entities.Ward;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WardRepository extends JpaRepository<Ward, Long> {
}

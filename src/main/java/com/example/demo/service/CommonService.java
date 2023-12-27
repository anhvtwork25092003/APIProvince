package com.example.demo.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CommonService<T, X> {
    Page<T> getAll(Pageable pageable);
    T getById(Long id);
    T create(X entity);
    T update(Long id, X entity);
    void delete(Long id);
}
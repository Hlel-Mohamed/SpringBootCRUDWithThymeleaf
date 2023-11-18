package com.springjava.poc.springbootcrudwiththymeleaf.service;

import com.springjava.poc.springbootcrudwiththymeleaf.entity.Staff;

import java.util.List;

public interface StaffService {
    void save(Staff staff);
    List<Staff> getAll();
    Staff getById(Integer id);
    void delete(Staff staff);
}

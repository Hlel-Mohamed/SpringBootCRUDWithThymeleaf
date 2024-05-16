package com.springjava.poc.springbootcrudwiththymeleaf.service;

import com.springjava.poc.springbootcrudwiththymeleaf.entity.Staff;

import java.util.List;

public interface StaffService {
    Staff save(Staff staff);
    List<Staff> getAll();
    Staff getById(Integer id);
    Staff getByEmail(String email);
    void delete(Staff staff);
}

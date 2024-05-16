package com.springjava.poc.springbootcrudwiththymeleaf.repository;

import com.springjava.poc.springbootcrudwiththymeleaf.entity.Staff;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StaffRepository extends JpaRepository<Staff, Integer> {
    Staff findByEmail(String email);
}

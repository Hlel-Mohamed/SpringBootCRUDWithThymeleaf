package com.springjava.poc.springbootcrudwiththymeleaf.repository;

import com.springjava.poc.springbootcrudwiththymeleaf.entity.Staff;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StaffRepo extends JpaRepository<Staff, Integer> {
}

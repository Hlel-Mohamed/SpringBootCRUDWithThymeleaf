package com.springjava.poc.springbootcrudwiththymeleaf.service.impl;

import com.springjava.poc.springbootcrudwiththymeleaf.entity.Staff;
import com.springjava.poc.springbootcrudwiththymeleaf.repository.StaffRepository;
import com.springjava.poc.springbootcrudwiththymeleaf.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StaffServiceImpl implements StaffService {
    @Autowired
    StaffRepository staffRepository;

    @Override
    public Staff save(Staff staff) {
        if (staff.getId() == null) {
            staffRepository.save(staff);
        } else {
            Staff staffUpdate = staffRepository.findById(staff.getId()).get();
            staffUpdate.setName(staff.getName());
            staffUpdate.setDesignation(staff.getDesignation());
            staffUpdate.setEmail(staff.getEmail());
            staffRepository.save(staffUpdate);
        }
        return staff;
    }

    @Override
    public List<Staff> getAll() {
        return staffRepository.findAll();
    }

    @Override
    public Staff getById(Integer id) {
        return staffRepository.findById(id).get();
    }

    @Override
    public Staff getByEmail(String email) {
        return staffRepository.findByEmail(email);
    }

    @Override
    public void delete(Staff staff) {
        staffRepository.delete(staff);
    }
}

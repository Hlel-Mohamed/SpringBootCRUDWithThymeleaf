package com.springjava.poc.springbootcrudwiththymeleaf.controller;

import com.springjava.poc.springbootcrudwiththymeleaf.entity.Staff;
import com.springjava.poc.springbootcrudwiththymeleaf.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * This is a RestController class for handling operations related to Staff.
 */
@RestController
public class StaffController {
    final StaffService staffService;

    @Autowired
    public StaffController(StaffService staffService) {
        this.staffService = staffService;
    }

    /**
     * This method is used to get all staff members.
     *
     * @return ResponseEntity containing a list of all staff members and HTTP status.
     */
    @GetMapping("/staff-list")
    public ResponseEntity<List<Staff>> getAllStaffs() {
        List<Staff> staffList = staffService.getAll();
        if (staffList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(staffList, HttpStatus.OK);
        }
    }

    /**
     * This method is used to get a staff member by id.
     *
     * @param id This is the id of the staff member to be fetched.
     * @return ResponseEntity containing the staff member and HTTP status.
     */
    @GetMapping("/staff-list/{id}")
    public ResponseEntity<Staff> getStaff(@PathVariable("id") Integer id) {
        if (id == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            Staff staff = staffService.getById(id);
            if (staff == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(staff, HttpStatus.OK);
            }
        }
    }

    /**
     * This method is used to get a staff member by email.
     *
     * @param email This is the email of the staff member to be fetched.
     * @return ResponseEntity containing the staff member and HTTP status.
     */
    @GetMapping("/staff-list/email/{email}")
    public ResponseEntity<Staff> getStaffByEmail(@PathVariable("email") String email) {
        if (email == null || email.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            Staff staff = staffService.getByEmail(email);
            if (staff == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(staff, HttpStatus.OK);
            }
        }
    }

    /**
     * This method is used to add a new staff member.
     *
     * @param staff This is the staff member to be added.
     * @return ResponseEntity containing the added staff member and HTTP status.
     */
    @PostMapping("/staff-add")
    public ResponseEntity<Staff> addStaff(@RequestBody Staff staff) {
        if (staff == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else if (staffService.getByEmail(staff.getEmail()) != null) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        } else {
            Staff newStaff = staffService.save(staff);
            return new ResponseEntity<>(newStaff, HttpStatus.CREATED);
        }
    }

    /**
     * This method is used to update a staff member.
     *
     * @param id    This is id of the staff member to be updated.
     * @param staff This is the updated staff member.
     * @return ResponseEntity containing the updated staff member and HTTP status.
     */
    @PutMapping("/staff-update/{id}")
    public ResponseEntity<Staff> updateStaff(@PathVariable("id") Integer id, @RequestBody Staff staff) {
        if (staff == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            Staff updateStaff = staffService.getById(id);
            if (updateStaff == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                updateStaff.setName(staff.getName());
                updateStaff.setDesignation(staff.getDesignation());
                updateStaff.setEmail(staff.getEmail());
                staffService.save(updateStaff);
                return new ResponseEntity<>(updateStaff, HttpStatus.OK);
            }
        }
    }

    /**
     * This method is used to delete a staff member.
     *
     * @param id This is the id of the staff member to be deleted.
     * @return ResponseEntity containing HTTP status.
     */
    @DeleteMapping("/staff-delete/{id}")
    public ResponseEntity<Void> deleteStaff(@PathVariable Integer id) {
        Staff staff = staffService.getById(id);
        if (staff == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        staffService.delete(staff);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
package com.springjava.poc.springbootcrudwiththymeleaf;

import com.springjava.poc.springbootcrudwiththymeleaf.controller.StaffController;
import com.springjava.poc.springbootcrudwiththymeleaf.entity.Staff;
import com.springjava.poc.springbootcrudwiththymeleaf.service.StaffService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class StaffControllerTest {

    @InjectMocks
    StaffController staffController;

    @Mock
    StaffService staffService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getAllStaffs_returnsStaffList() {
        Staff staff = new Staff();
        when(staffService.getAll()).thenReturn(List.of(staff));

        ResponseEntity<List<Staff>> response = staffController.getAllStaffs();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, Objects.requireNonNull(response.getBody()).size());
        verify(staffService, times(1)).getAll();
    }

    @Test
    public void getAllStaffs_returnsNoContent_whenNoStaff() {
        when(staffService.getAll()).thenReturn(List.of());

        ResponseEntity<List<Staff>> response = staffController.getAllStaffs();

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(staffService, times(1)).getAll();
    }

    @Test
    public void getStaff_returnsStaff() {
        Staff staff = new Staff();
        when(staffService.getById(1)).thenReturn(staff);

        ResponseEntity<Staff> response = staffController.getStaff(1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(staffService, times(1)).getById(1);
    }

    @Test
    public void getStaff_returnsNotFound_whenStaffNotFound() {
        when(staffService.getById(1)).thenReturn(null);

        ResponseEntity<Staff> response = staffController.getStaff(1);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(staffService, times(1)).getById(1);
    }

    @Test
    public void getStaffByEmail_returnsStaff() {
        Staff staff = new Staff();
        when(staffService.getByEmail("test@test.com")).thenReturn(staff);

        ResponseEntity<Staff> response = staffController.getStaffByEmail("test@test.com");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(staffService, times(1)).getByEmail("test@test.com");
    }

    @Test
    public void getStaffByEmail_returnsNotFound_whenEmailNotFound() {
        when(staffService.getByEmail("test@test.com")).thenReturn(null);

        ResponseEntity<Staff> response = staffController.getStaffByEmail("test@test.com");

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(staffService, times(1)).getByEmail("test@test.com");
    }

    @Test
    public void addStaff_returnsCreated() {
        Staff staff = new Staff();
        when(staffService.save(any(Staff.class))).thenReturn(staff);

        ResponseEntity<Staff> response = staffController.addStaff(staff);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        verify(staffService, times(1)).save(any(Staff.class));
    }

    @Test
    public void addStaff_returnsConflict_whenEmailExists() {
        Staff staff = new Staff();
        staff.setEmail("test@test.com");
        when(staffService.getByEmail("test@test.com")).thenReturn(staff);

        ResponseEntity<Staff> response = staffController.addStaff(staff);

        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        verify(staffService, times(1)).getByEmail("test@test.com");
    }

    @Test
    public void updateStaff_returnsOk() {
        Staff staff = new Staff();
        when(staffService.getById(1)).thenReturn(staff);
        when(staffService.save(any(Staff.class))).thenReturn(staff);

        ResponseEntity<Staff> response = staffController.updateStaff(1, staff);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(staffService, times(1)).getById(1);
        verify(staffService, times(1)).save(any(Staff.class));
    }

    @Test
    public void updateStaff_returnsNotFound_whenStaffNotFound() {
        Staff staff = new Staff();
        when(staffService.getById(1)).thenReturn(null);

        ResponseEntity<Staff> response = staffController.updateStaff(1, staff);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(staffService, times(1)).getById(1);
    }

    @Test
    public void deleteStaff_returnsNoContent() {
        Staff staff = new Staff();
        when(staffService.getById(1)).thenReturn(staff);
        doNothing().when(staffService).delete(staff);

        ResponseEntity<Void> response = staffController.deleteStaff(1);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(staffService, times(1)).getById(1);
        verify(staffService, times(1)).delete(staff);
    }

    @Test
    public void deleteStaff_returnsNotFound_whenStaffNotFound() {
        when(staffService.getById(1)).thenReturn(null);

        ResponseEntity<Void> response = staffController.deleteStaff(1);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(staffService, times(1)).getById(1);
    }
}
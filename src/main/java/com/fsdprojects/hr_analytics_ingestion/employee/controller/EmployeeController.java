package com.fsdprojects.hr_analytics_ingestion.employee.controller;

import com.fsdprojects.hr_analytics_ingestion.employee.dto.EmployeeDto;
import com.fsdprojects.hr_analytics_ingestion.employee.entity.Employee;
import com.fsdprojects.hr_analytics_ingestion.employee.service.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService service;

    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    /**
     * Creates or updates an Employee record.
     *
     * @param request Employee data in JSON format.
     * @return Saved employee with all nested attributes and salary values.
     */
    @PostMapping
    public ResponseEntity<?> create(@RequestBody EmployeeDto request) {
        if (request.getId() == null || request.getId().isBlank())
            return ResponseEntity.badRequest().body(Map.of("error", "id is required"));
        Employee saved = service.save(request.toEmployee());
        return ResponseEntity.ok(EmployeeDto.fromEmployee(saved));
    }
}

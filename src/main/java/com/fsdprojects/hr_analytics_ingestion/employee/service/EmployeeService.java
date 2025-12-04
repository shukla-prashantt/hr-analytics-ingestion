package com.fsdprojects.hr_analytics_ingestion.employee.service;

import com.fsdprojects.hr_analytics_ingestion.employee.entity.Employee;
import com.fsdprojects.hr_analytics_ingestion.employee.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepo;

    public EmployeeService(EmployeeRepository repository) {
        this.employeeRepo = repository;
    }

    private Logger log = LoggerFactory.getLogger(EmployeeService.class);

    public Employee save (Employee request) {
        Optional<Employee> existing = employeeRepo.findById(request.getId());
        if (existing.isPresent()) {
            log.info("Updating existing employee {}", request.getId());
        } else {
            log.info("Received request to create employee: {}", request.getId());
        }
        return employeeRepo.save(request);
    }
}

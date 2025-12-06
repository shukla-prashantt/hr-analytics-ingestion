package com.fsdprojects.hr_analytics_ingestion.employee.repository;

import com.fsdprojects.hr_analytics_ingestion.employee.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, String> {
}

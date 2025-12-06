package com.fsdprojects.hr_analytics_ingestion;

import com.fsdprojects.hr_analytics_ingestion.employee.dto.EmployeeDto;
import com.fsdprojects.hr_analytics_ingestion.employee.dto.SalaryValueDto;
import com.fsdprojects.hr_analytics_ingestion.employee.entity.Employee;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class EmpoloyeeDToTest {

    @Test
    void testToDomainMapping() {
        EmployeeDto dto = new EmployeeDto();
        dto.setId("abc1");
        dto.setName("Bob");
        dto.setDeleted(false);
        dto.setAttributes(Map.of("position", "Manager"));
        dto.setSalaryValues(List.of(new SalaryValueDto("Base", "USD", 5000.0)));

        Employee emp = dto.toEmployee();

        // Validate basic fields
        assertThat(emp.getId()).isEqualTo("abc1");
        assertThat(emp.getName()).isEqualTo("Bob");
        assertThat(emp.isDeleted()).isFalse();

        // Validate attributes mapped
        assertThat(emp.getAttributes()).hasSize(1);
        assertThat(emp.getAttributes().get(0).getAttributeKey()).isEqualTo("position");
        assertThat(emp.getAttributes().get(0).getAttributeValue()).isEqualTo("Manager");

        // Validate salary mapped
        assertThat(emp.getSalaryValues()).hasSize(1);
        assertThat(emp.getSalaryValues().get(0).getType()).isEqualTo("Base");
        assertThat(emp.getSalaryValues().get(0).getCurrency()).isEqualTo("USD");
        assertThat(emp.getSalaryValues().get(0).getAmount()).isEqualTo(5000.0);
    }
}

package com.fsdprojects.hr_analytics_ingestion.employee.dto;

import com.fsdprojects.hr_analytics_ingestion.employee.entity.Employee;
import com.fsdprojects.hr_analytics_ingestion.employee.entity.EmployeeAttribute;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class EmployeeDto {
    private String id;
    private String name;
    private Map<String, Object> attributes;
    private List<SalaryValueDto> salaryValues;
    private boolean isDeleted;

    public Employee toEmployee() {
        Employee emp = new Employee();
        emp.setId(id);
        emp.setName(name);
        emp.setDeleted(isDeleted);

        if (attributes != null && !attributes.isEmpty()) {
            emp.setAttributes(
                    attributes.entrySet().stream()
                            .map(e -> toEmployeeAttribute(e.getKey(), String.valueOf(e.getValue()), emp))
                            .toList()
            );
            Object joined = attributes.get("joinedOn");
            if (joined != null)
                emp.setJoinedOn(Instant.parse(String.valueOf(joined)));
        }

        // convert salary
        if (salaryValues != null && !salaryValues.isEmpty()) {
            emp.setSalaryValues(
                    salaryValues.stream()
                            .map(s -> s.toSalaryValue(s, emp))
                            .toList()
            );
        }
        return emp;
    }

    public EmployeeAttribute toEmployeeAttribute(String key, String value, Employee employee) {
        EmployeeAttribute attr = new EmployeeAttribute();
        attr.setAttributeKey(key);
        attr.setAttributeValue(value);
        attr.setEmployee(employee);
        return attr;
    }

    public static EmployeeDto fromEmployee(Employee emp) {
        EmployeeDto dto = new EmployeeDto();
        dto.setId(emp.getId());
        dto.setName(emp.getName());
        dto.setDeleted(emp.isDeleted());

        if (emp.getAttributes() != null) {
            dto.setAttributes(
                    emp.getAttributes().stream()
                            .collect(Collectors.toMap(
                                    EmployeeAttribute::getAttributeKey,
                                    EmployeeAttribute::getAttributeValue
                            ))
            );
        }

        if (emp.getSalaryValues() != null) {
            dto.setSalaryValues(
                    emp.getSalaryValues()
                            .stream()
                            .map(SalaryValueDto::fromSalaryValue)
                            .toList()
            );
        }

        return dto;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, Object> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    public List<SalaryValueDto> getSalaryValues() {
        return salaryValues;
    }

    public void setSalaryValues(List<SalaryValueDto> salaryValues) {
        this.salaryValues = salaryValues;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }
}

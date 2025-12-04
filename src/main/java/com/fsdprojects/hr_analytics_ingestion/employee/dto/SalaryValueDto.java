package com.fsdprojects.hr_analytics_ingestion.employee.dto;

import com.fsdprojects.hr_analytics_ingestion.employee.entity.Employee;
import com.fsdprojects.hr_analytics_ingestion.employee.entity.SalaryValue;

public class SalaryValueDto {

    private String type;

    private String currency;

    private Double value;

    public SalaryValueDto(String type, String currency, Double value) {
        this.value = value;
        this.currency = currency;
        this.type = type;
    }

    SalaryValue toSalaryValue(SalaryValueDto request, Employee employee) {
        SalaryValue salaryValue = new SalaryValue();
        salaryValue.setEmployee(employee);
        salaryValue.setCurrency(request.getCurrency());
        salaryValue.setType(request.getType());
        salaryValue.setAmount(request.getValue());
        return salaryValue;
    }

    public static SalaryValueDto fromSalaryValue(SalaryValue s) {
        return new SalaryValueDto(s.getType(), s.getCurrency(), s.getAmount());
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double amount) {
        this.value = amount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

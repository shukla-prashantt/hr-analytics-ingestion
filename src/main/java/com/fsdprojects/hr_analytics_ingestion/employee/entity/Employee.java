package com.fsdprojects.hr_analytics_ingestion.employee.entity;

import jakarta.persistence.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Employee {

    @Id
    private String id;

    private String name;

    private boolean isDeleted;

    //Ticket 2 -Changes to show DB Versioning
    @Column(name = "joined_on")
    private Instant joinedOn;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SalaryValue> salaryValues = new ArrayList<>();

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EmployeeAttribute> attributes = new ArrayList<>();

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

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public Instant getJoinedOn() {
        return joinedOn;
    }

    public void setJoinedOn(Instant joinedOn) {
        this.joinedOn = joinedOn;
    }

    public List<SalaryValue> getSalaryValues() {
        return salaryValues;
    }

    public void setSalaryValues(List<SalaryValue> salaryValues) {
        this.salaryValues = salaryValues;
    }

    public List<EmployeeAttribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<EmployeeAttribute> attributes) {
        this.attributes = attributes;
    }
}

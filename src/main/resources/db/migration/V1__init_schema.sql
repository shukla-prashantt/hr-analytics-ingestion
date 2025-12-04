-- V1__init_schema.sql
CREATE TABLE employee (
    id VARCHAR(255) PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    is_deleted BOOLEAN DEFAULT FALSE NOT NULL
);

CREATE TABLE employee_attribute (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    attribute_key VARCHAR(255),
    attribute_value VARCHAR(1000),
    employee_id VARCHAR(255),
    CONSTRAINT fk_attr_employee FOREIGN KEY (employee_id)
        REFERENCES employee(id) ON DELETE CASCADE
);

CREATE TABLE salary_value (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    employee_id VARCHAR(255) NOT NULL,
    type VARCHAR(100),
    currency VARCHAR(10),
    amount DECIMAL(15,2), -- renamed from "value" in the json provided to "amount" as value is reserved keyword in h2 db
    CONSTRAINT fk_salary_employee FOREIGN KEY (employee_id)
        REFERENCES employee(id) ON DELETE CASCADE
);

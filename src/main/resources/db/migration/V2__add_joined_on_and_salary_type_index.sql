-- V2__add_joined_on_and_salary_type_index.sql
-- Add a joined_on column to employee (nullable)
ALTER TABLE employee ADD COLUMN joined_on TIMESTAMP;

-- Add an index on salary_value.type to speed queries by salary part
CREATE INDEX idx_salary_value_type ON salary_value(type);

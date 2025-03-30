-- liquibase formatted sql

-- changeset Kirill:1
CREATE INDEX student_idx_name ON student USING GIST (name);

-- changeset Kirill:2
CREATE INDEX faculty_idx_name_color ON faculty USING GIST (name, color);
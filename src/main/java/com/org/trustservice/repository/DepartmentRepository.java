package com.org.trustservice.repository;

import com.org.trustservice.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department , Long> {
}

package com.aapon.springbootrestapilearn.repository;

import com.aapon.springbootrestapilearn.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
}

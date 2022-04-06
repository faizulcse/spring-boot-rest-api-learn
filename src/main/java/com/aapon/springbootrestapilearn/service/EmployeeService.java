package com.aapon.springbootrestapilearn.service;

import com.aapon.springbootrestapilearn.model.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> getEmployees(int pageNumber, int pageSize);

    Employee saveEmployee(Employee employee);

    Employee getSingleEmployee(Long id);

    void deleteEmployee(Long id);

    Employee updateEmployee(Employee employee);
}

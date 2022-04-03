package com.aapon.springbootrestapilearn.service;

import com.aapon.springbootrestapilearn.model.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> getEmployees();

    Employee saveEmployee(Employee employee);
}

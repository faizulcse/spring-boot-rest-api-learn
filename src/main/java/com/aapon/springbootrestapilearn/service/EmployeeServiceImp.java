package com.aapon.springbootrestapilearn.service;

import com.aapon.springbootrestapilearn.model.Employee;
import com.aapon.springbootrestapilearn.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImp implements EmployeeService {
    @Autowired
    private EmployeeRepository eRepository;

    @Override
    public List<Employee> getEmployees() {
        return eRepository.findAll();
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        return eRepository.save(employee);
    }
}

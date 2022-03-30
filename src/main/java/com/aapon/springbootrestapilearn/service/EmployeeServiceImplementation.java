package com.aapon.springbootrestapilearn.service;

import com.aapon.springbootrestapilearn.model.Employee;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeServiceImplementation implements EmployeeService {
    private static List<Employee> list = new ArrayList<>();

    static {
        Employee e = new Employee();
        e.setName("Faizul");
        e.setAge(28L);
        e.setLocation("Dhaka");
        e.setEmail("faizulcse@gmail.com");
        e.setDepartment("CSE");
        list.add(e);

        e = new Employee();
        e.setName("Appon");
        e.setAge(28L);
        e.setLocation("Dhaka");
        e.setEmail("faizulcse@gmail.com");
        e.setDepartment("CSE");
        list.add(e);
    }

    @Override
    public List<Employee> getEmployees() {
        return list;
    }
}

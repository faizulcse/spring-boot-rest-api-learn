package com.aapon.springbootrestapilearn.controller;

import com.aapon.springbootrestapilearn.model.Employee;
import com.aapon.springbootrestapilearn.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {
    @Autowired
    private EmployeeService eService;

    @GetMapping("/employees")
    public List<Employee> getEmployees() {
        return eService.getEmployees();
    }

    @GetMapping("/employees/{id}")
    public String getEmployee(@PathVariable Long id) {
        return "Employee Details of id: " + id;
    }

    @PostMapping("/employees")
    public Employee saveEmployee(@RequestBody Employee employee) {
        return eService.saveEmployee(employee);
    }

    @PutMapping("/employees/{id}")
    public Employee updateEmployee(@PathVariable Long id, @RequestBody Employee employee) {
        System.out.println("Updating the employee data for the id: " + id);
        return employee;
    }

    @DeleteMapping("/employees")
    public String deleteEmployee(@RequestParam Long id) {
        return "Deleted Employee Details of id: " + id;
    }
}

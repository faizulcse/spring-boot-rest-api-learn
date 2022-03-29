package com.aapon.springbootrestapilearn.restaipparameterpassing;

import com.aapon.springbootrestapilearn.model.Employee;
import org.springframework.web.bind.annotation.*;

@RestController
public class EmployeeController {
    // localhost:8080/employees
    @GetMapping("/employees")
    public String getEmployees() {
        return "Display list of Employee Details";
    }

    //localhost:8080/employees/123
    @GetMapping("/employees/{id}")
    public String getEmployee(@PathVariable Long id) {
        return "Employee Details of id: " + id;
    }

    //localhost:8080/employees
    @PostMapping("/employees")
    public String saveEmployee(@RequestBody Employee employee) {
        return "Save the employee details to the database: " + employee;
    }

    //localhost:8080/employees
    @PutMapping("/employees/{id}")
    public Employee updateEmployee(@PathVariable Long id, @RequestBody Employee employee) {
        System.out.println("Updating the employee data for the id: " + id);
        return employee;
    }

    //localhost:8080/employees? id=10
    @DeleteMapping("/employees")
    public String deleteEmployee(@RequestParam Long id) {
        return "Deleted Employee Details of id: " + id;
    }
}

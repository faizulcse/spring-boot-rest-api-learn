package com.aapon.springbootrestapilearn.controller;

import com.aapon.springbootrestapilearn.model.Department;
import com.aapon.springbootrestapilearn.model.Employee;
import com.aapon.springbootrestapilearn.repository.DepartmentRepository;
import com.aapon.springbootrestapilearn.repository.EmployeeRepository;
import com.aapon.springbootrestapilearn.request.EmployeeRequest;
import com.aapon.springbootrestapilearn.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class EmployeeController {
    @Autowired
    private EmployeeService eService;

    @Autowired
    private DepartmentRepository depRepo;

    @Autowired
    private EmployeeRepository eRepo;

    @GetMapping("/employees")
    public ResponseEntity<List<Employee>> getEmployees(@RequestParam Integer pageNumber, @RequestParam Integer pageSize) {
        return new ResponseEntity<List<Employee>>(eService.getEmployees(pageNumber, pageSize), HttpStatus.OK);
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity<Employee> getEmployee(@PathVariable Long id) {
        return new ResponseEntity<Employee>(eService.getSingleEmployee(id), HttpStatus.OK);
    }

    @PostMapping("/employees")
    public ResponseEntity<Employee> saveEmployeeOneTwoOne(@Valid @RequestBody EmployeeRequest eRequest) {
        Department dept = new Department();
        dept.setName(eRequest.getDepartment());
        dept = depRepo.save(dept);
        Employee employee = new Employee(eRequest);
        employee.setDepartment(dept);
        employee = eRepo.save(employee);

        return new ResponseEntity<Employee>(employee, HttpStatus.CREATED);
    }

    @PutMapping("/employees/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employee) {
        employee.setId(id);
        return new ResponseEntity<Employee>(eService.updateEmployee(employee), HttpStatus.OK);
    }

    @DeleteMapping("/employees")
    public ResponseEntity<HttpStatus> deleteEmployee(@RequestParam Long id) {
        eService.deleteEmployee(id);
        return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/employees/filter/{name}")
    public ResponseEntity<List<Employee>> getEmployeesByDepartment(@PathVariable String name) {
        return new ResponseEntity<List<Employee>>(eRepo.findByDepartmentName(name), HttpStatus.OK);
    }
}

package com.aapon.springbootrestapilearn.controller;

import com.aapon.springbootrestapilearn.model.Employee;
import com.aapon.springbootrestapilearn.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.aapon.springbootrestapilearn.CustomApiLogger.loggerGenerator;

@RestController
public class EmployeeController {
    @Autowired
    private EmployeeService eService;

    @GetMapping("/employees")
    public ResponseEntity<List<Employee>> getEmployees(@RequestParam Integer pageNumber, @RequestParam Integer pageSize) {
        loggerGenerator("/employees");
        return new ResponseEntity<List<Employee>>(eService.getEmployees(pageNumber, pageSize), HttpStatus.OK);
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity<Employee> getEmployee(@PathVariable Long id) {
        loggerGenerator("/employees/{id}");
        return new ResponseEntity<Employee>(eService.getSingleEmployee(id), HttpStatus.OK);
    }

    @PostMapping("/employees")
    public ResponseEntity<Employee> saveEmployee(@Valid @RequestBody Employee employee) {
        loggerGenerator("/employees");
        return new ResponseEntity<Employee>(eService.saveEmployee(employee), HttpStatus.CREATED);
    }

    @PutMapping("/employees/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employee) {
        loggerGenerator("/employees/{id}");
        employee.setId(id);
        return new ResponseEntity<Employee>(eService.updateEmployee(employee), HttpStatus.OK);
    }

    @DeleteMapping("/employees")
    public ResponseEntity<HttpStatus> deleteEmployee(@RequestParam Long id) {
        loggerGenerator("/employees/{id}");
        eService.deleteEmployee(id);
        return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/employees/filterByName")
    public ResponseEntity<List<Employee>> getEmployeesByName(@RequestParam String name) {
        loggerGenerator("/employees/filterByName");
        return new ResponseEntity<List<Employee>>(eService.getEmployeesByName(name), HttpStatus.OK);
    }

    @GetMapping("/employees/filterByNameAndLocation")
    public ResponseEntity<List<Employee>> getEmployeesByNameAndLocation(@RequestParam String name, @RequestParam String location) {
        loggerGenerator("/employees/filterByNameAndLocation");
        return new ResponseEntity<List<Employee>>(eService.getEmployeesByNameAndLocation(name, location), HttpStatus.OK);
    }

    @GetMapping("/employees/filterByKeyword")
    public ResponseEntity<List<Employee>> getEmployeesByKeyword(@RequestParam String name) {
        loggerGenerator("/employees/filterByKeyword");
        return new ResponseEntity<List<Employee>>(eService.getEmployeesByKeyword(name), HttpStatus.OK);
    }

    @GetMapping("/employees/{name}/{location}")
    public ResponseEntity<List<Employee>> getEmployeesByNameOrLocation(@PathVariable String name, @PathVariable String location) {
        loggerGenerator("/employees/" + name + "/" + location);
        return new ResponseEntity<List<Employee>>(eService.getEmployeesByNameAndLocation(name, location), HttpStatus.OK);
    }

    @DeleteMapping("/employees/delete/{name}")
    public ResponseEntity<String> deleteEmployeeByName(@PathVariable String name) {
        loggerGenerator("/employees/" + name);
        return new ResponseEntity<String>("No. of records deleted: " + eService.deleteEmployeeByName(name), HttpStatus.OK);
    }
}

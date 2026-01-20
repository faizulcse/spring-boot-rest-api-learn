package com.aapon.springbootrestapilearn.controller;

import com.aapon.springbootrestapilearn.model.Employee;
import com.aapon.springbootrestapilearn.service.EmployeeService;
import com.aapon.springbootrestapilearn.utils.ApiEndpoints;
import com.aapon.springbootrestapilearn.utils.ApiLogger;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class EmployeeController {
    @Autowired
    private EmployeeService eService;

    @GetMapping(ApiEndpoints.EMPLOYEE_BASE)
    public ResponseEntity<List<Employee>> getEmployees(@RequestParam Integer page_number, @RequestParam Integer page_size) {
        ApiLogger.loggerGenerator(ApiEndpoints.EMPLOYEE_BASE + "?page_number=" + page_number + "&page_size=" + page_size);
        return new ResponseEntity<List<Employee>>(eService.getEmployees(page_number, page_size), HttpStatus.OK);
    }

    @GetMapping(ApiEndpoints.EMPLOYEE_BY_ID)
    public ResponseEntity<Employee> getEmployee(@PathVariable Long id) {
        ApiLogger.loggerGenerator(ApiEndpoints.EMPLOYEE_BASE + "/" + id);
        return new ResponseEntity<Employee>(eService.getSingleEmployee(id), HttpStatus.OK);
    }

    @PostMapping(ApiEndpoints.EMPLOYEE_BASE)
    public ResponseEntity<Employee> saveEmployee(@Valid @RequestBody Employee employee) {
        ApiLogger.loggerGenerator(ApiEndpoints.EMPLOYEE_BASE);
        return new ResponseEntity<Employee>(eService.saveEmployee(employee), HttpStatus.CREATED);
    }

    @PutMapping(ApiEndpoints.EMPLOYEE_BY_ID)
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employee) {
        ApiLogger.loggerGenerator(ApiEndpoints.EMPLOYEE_BASE + "/" + id);
        employee.setId(id);
        return new ResponseEntity<Employee>(eService.updateEmployee(employee), HttpStatus.OK);
    }

    @DeleteMapping(ApiEndpoints.EMPLOYEE_BASE)
    public ResponseEntity<HttpStatus> deleteEmployee(@RequestParam Long id) {
        ApiLogger.loggerGenerator(ApiEndpoints.EMPLOYEE_BASE + "/" + id);
        eService.deleteEmployee(id);
        return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(ApiEndpoints.EMPLOYEE_FILTER_BY_NAME)
    public ResponseEntity<List<Employee>> getEmployeesByName(@RequestParam String name) {
        ApiLogger.loggerGenerator(ApiEndpoints.EMPLOYEE_FILTER_BY_NAME + "?name=" + name);
        return new ResponseEntity<List<Employee>>(eService.getEmployeesByName(name), HttpStatus.OK);
    }

    @GetMapping(ApiEndpoints.EMPLOYEE_FILTER_BY_NAME_AND_LOCATION)
    public ResponseEntity<List<Employee>> getEmployeesByNameAndLocation(@RequestParam String name, @RequestParam String location) {
        ApiLogger.loggerGenerator(ApiEndpoints.EMPLOYEE_FILTER_BY_NAME_AND_LOCATION + "?name=" + name + "&location=" + location);
        return new ResponseEntity<List<Employee>>(eService.getEmployeesByNameAndLocation(name, location), HttpStatus.OK);
    }

    @GetMapping(ApiEndpoints.EMPLOYEE_FILTER_BY_KEYWORD)
    public ResponseEntity<List<Employee>> getEmployeesByKeyword(@RequestParam String name) {
        ApiLogger.loggerGenerator(ApiEndpoints.EMPLOYEE_FILTER_BY_KEYWORD + "?name=" + name);
        return new ResponseEntity<List<Employee>>(eService.getEmployeesByKeyword(name), HttpStatus.OK);
    }

    @GetMapping(ApiEndpoints.EMPLOYEE_BY_NAME_AND_LOCATION)
    public ResponseEntity<List<Employee>> getEmployeesByNameOrLocation(@PathVariable String name, @PathVariable String location) {
        ApiLogger.loggerGenerator(ApiEndpoints.EMPLOYEE_BASE + "/" + name + "/" + location);
        return new ResponseEntity<List<Employee>>(eService.getEmployeesByNameAndLocation(name, location), HttpStatus.OK);
    }

    @DeleteMapping(ApiEndpoints.EMPLOYEE_DELETE_BY_NAME)
    public ResponseEntity<String> deleteEmployeeByName(@PathVariable String name) {
        ApiLogger.loggerGenerator(ApiEndpoints.EMPLOYEE_DELETE_BY_NAME.replace("{name}", name));
        return new ResponseEntity<String>("No. of records deleted: " + eService.deleteEmployeeByName(name), HttpStatus.OK);
    }
}

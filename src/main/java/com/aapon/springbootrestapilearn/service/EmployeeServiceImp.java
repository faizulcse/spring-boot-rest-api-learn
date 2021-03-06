package com.aapon.springbootrestapilearn.service;

import com.aapon.springbootrestapilearn.model.Employee;
import com.aapon.springbootrestapilearn.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImp implements EmployeeService {
    @Autowired
    private EmployeeRepository eRepository;

    @Override
    public List<Employee> getEmployees(int pageNumber, int pageSize) {
        PageRequest pages = PageRequest.of(pageNumber, pageSize, Sort.Direction.DESC, "id");
        return eRepository.findAll(pages).getContent();
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        return eRepository.save(employee);
    }

    @Override
    public Employee getSingleEmployee(Long id) {
        Optional<Employee> employee = eRepository.findById(id);
        if (employee.isPresent())
            return employee.get();
        throw new RuntimeException("Employee not found for the id: " + id);
    }

    @Override
    public void deleteEmployee(Long id) {
        if (eRepository.existsById(id))
            eRepository.deleteById(id);
        else
            throw new RuntimeException("Employee not found for the id: " + id);
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        if (eRepository.existsById(employee.getId()))
            return eRepository.save(employee);
        throw new RuntimeException("Employee not found for the id: " + employee.getId());
    }

    @Override
    public List<Employee> getEmployeesByName(String name) {
        return eRepository.findByName(name);
    }

    @Override
    public List<Employee> getEmployeesByNameAndLocation(String name, String location) {
        return eRepository.findByNameAndLocation(name, location);
    }

    @Override
    public List<Employee> getEmployeesByKeyword(String name) {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        return eRepository.findByNameContaining(name, sort);
    }

    @Override
    public List<Employee> getEmployeesByNameOrLocation(String name, String location) {
        return eRepository.getEmployeesByNameOrLocation(name, location);
    }

    @Override
    public Integer deleteEmployeeByName(String name) {
        return eRepository.deleteEmployeeByName(name);
    }
}

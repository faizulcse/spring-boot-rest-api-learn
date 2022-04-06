package com.aapon.springbootrestapilearn.model;

import com.aapon.springbootrestapilearn.request.EmployeeRequest;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Setter
@Getter
@ToString
@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name should not be null")
    private String name;

    @OneToOne
    @JoinColumn(name = "department_id")
    private Department department;

    public Employee(EmployeeRequest req) {
        this.name = req.getName();
    }
}

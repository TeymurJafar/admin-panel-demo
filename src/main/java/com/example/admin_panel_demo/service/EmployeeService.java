package com.example.admin_panel_demo.service;

import com.example.admin_panel_demo.entity.Employee;

import java.util.List;

public interface EmployeeService {

    List<Employee> findAll();

    List<Employee> findByKeyword(String keyword);

    Employee findById(Long theId);

    Employee save(Employee theEmployee);

    void deleteById(Long theId);
}

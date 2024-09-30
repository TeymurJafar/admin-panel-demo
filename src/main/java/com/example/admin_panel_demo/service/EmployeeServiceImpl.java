package com.example.admin_panel_demo.service;

import com.example.admin_panel_demo.entity.Employee;
import com.example.admin_panel_demo.redis.RedisService;
import com.example.admin_panel_demo.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final RedisService redisService;

    @Override
    public List<Employee> findAll() {
        List<Employee> employees = redisService.findAll();
        if (employees.isEmpty()) {
            employees = employeeRepository.findAll();
        }
        for (Employee employee : employees) {
            redisService.saveEmployee(employee);
        }
        return employees;
    }

    @Override
    public List<Employee> findByKeyword(String keyword) {
        List<Employee> employees = redisService.findByKeyword(keyword);
        if (employees.isEmpty()) {
            employees = employeeRepository.findAllByKeyword(keyword);
        }
        for (Employee employee : employees) {
            redisService.saveEmployee(employee);
        }
        return employees;
    }

    @Override
    public Employee findById(Long theId) {
       Employee employee =redisService.findById(theId);
       if (employee != null) {
           return employee;
       }else {
           return  employeeRepository.findById(theId).orElse(null);
       }

    }

    @Override
    public Employee save(Employee theEmployee) {

        Employee employee = employeeRepository.save(theEmployee);
        redisService.saveEmployee(employee);
        return employee;
    }

    @Override
    public void deleteById(Long theId) {
        Employee employee = findById(theId);
        redisService.deleteEmployee(employee.getId(),employee.getName());
        employeeRepository.deleteById(theId);
    }

}

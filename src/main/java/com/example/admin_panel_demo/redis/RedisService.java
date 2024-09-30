package com.example.admin_panel_demo.redis;

import com.example.admin_panel_demo.entity.Employee;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class RedisService {
    private final RedisTemplate<String, Employee> employeeRedisTemplate;
    private final RedisTemplate<String, String> stringRedisTemplate;

    public void saveEmployee(Employee employee) {
        employeeRedisTemplate.opsForValue().set("employee:" + employee.getId(), employee);
        stringRedisTemplate.opsForSet().add("employee:byName", employee.getName().toLowerCase() + ":" + employee.getId());
    }

    public Employee findById(Long employeeId) {
        return employeeRedisTemplate.opsForValue().get("employee:" + employeeId);
    }

    public List<Employee> findByKeyword(String keyword) {
        List<Employee> employees = new ArrayList<>();
        Set<Employee> keys = employeeRedisTemplate.opsForSet().members("employee");

        if (keys != null) {
            for (Employee employee : keys) {
                    if (employee != null) {
                        employees.add(employee);
                    }
                }
            }
        return employees;
    }

    public List<Employee> findAll() {
        List<Employee> employees = new ArrayList<>();
        Set<String> keys = stringRedisTemplate.opsForSet().members("employee:byName");

        if (keys != null) {
            for (String nameId : keys) {
                String[] parts = nameId.split(":");
                Long id = Long.valueOf(parts[1]);
                Employee employee = findById(id);
                if (employee != null) {
                    employees.add(employee);
                }
            }
        }
        return employees;
    }
    public void deleteEmployee(Long employeeId, String employeeName) {
        stringRedisTemplate.opsForSet().remove("employee:byName", employeeName.toLowerCase() + ":" + employeeId);
        employeeRedisTemplate.delete("employee:" + employeeId);
    }
}

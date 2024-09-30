package com.example.admin_panel_demo.controller;

import com.example.admin_panel_demo.entity.Employee;
import com.example.admin_panel_demo.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employees")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;


    @GetMapping("/getEmployee/{id}")
    public Employee getEmployeeById(@PathVariable("id") Long id) {
        return employeeService.findById(id);
    }

    @GetMapping("/search")
    @ResponseBody
    public List<Employee> search(@RequestParam(value = "keyword", required = false) String keyword) {
        if (keyword == null || keyword.isEmpty()) {
            keyword = "%";
        }
        return employeeService.findByKeyword(keyword);
    }
    @GetMapping("/showAll")
    @ResponseBody
    public List<Employee> showAll() {
        return employeeService.findAll();
    }

    @GetMapping("/edit")
    @ResponseBody
    public Employee edit(@RequestParam("employeeId") Long employeeId){
        return employeeService.findById(employeeId);
    }

    @PostMapping("/addEmployee")
    @ResponseBody
    public Employee addEmployee(@RequestBody Employee employee) {
        return employeeService.save(employee);
    }
    @PostMapping("/delete")
    @ResponseBody
    public void deleteEmployee(@RequestBody Long id) {
        employeeService.deleteById(id);
    }


}

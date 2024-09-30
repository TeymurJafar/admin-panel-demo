package com.example.admin_panel_demo.repository;

import com.example.admin_panel_demo.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {

    @Query("SELECT e FROM Employee e WHERE e.name LIKE CONCAT('%', ?1, '%')")
     List<Employee> findAllByKeyword(String keyword);
}

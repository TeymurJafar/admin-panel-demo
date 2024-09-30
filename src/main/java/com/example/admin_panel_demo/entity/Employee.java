package com.example.admin_panel_demo.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Check;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Table(name = "employees")
@Data
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String surname;
    @Column(nullable = false,unique = true)
    private String email;
    @Column(nullable = false)
    @ColumnDefault(value = "1")
    @Check(constraints = "active IN (1,0)")
    private Integer active;

    public Employee() {
        if (active== null){
            active = 1;
        }
    }
}

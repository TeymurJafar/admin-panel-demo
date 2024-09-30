package com.example.admin_panel_demo;

import com.example.admin_panel_demo.repository.EmployeeRepository;
import com.example.admin_panel_demo.service.EmployeeService;
import com.example.admin_panel_demo.service.EmployeeServiceImpl;
import org.apache.catalina.core.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class AdminPanelDemoApplication {

	public static void main(String[] args) {

		SpringApplication.run(AdminPanelDemoApplication.class, args);

	}

}

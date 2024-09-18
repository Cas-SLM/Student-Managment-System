package org.cas.student_management_system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@SpringBootApplication
@RestController // Makes the class serve rest endpoints
public class SpringbootCourseApplication {
    // Port is set by Tomcat by default
    public static void main(String[] args) {
        SpringApplication.run(SpringbootCourseApplication.class, args);
    }
}

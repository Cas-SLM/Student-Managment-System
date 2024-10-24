package org.cas.student_management_system.model;

import java.time.LocalDate;
import java.time.Period;

public class Student {
    private Long id;
    private String name;
    private String email;
    private LocalDate dateOfBirth;
    private Integer age;

    public Student() {
    }
    public Student(int id, String name, String email, LocalDate dateOfBirth, Integer age) {
        this((long) id, name, email, dateOfBirth, age);
    }

    public Student(Long id, String name, String email, LocalDate dateOfBirth, Integer age) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.age = age;
        this.dateOfBirth = dateOfBirth;
    }

    public Student(Long id, String name, String email, LocalDate dateOfBirth) {
        this(id, name, email, dateOfBirth, currentAge(dateOfBirth));
    };

    public Student(String name, String email, LocalDate dateOfBirth, Integer age) {
        this.name = name;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.age = age;
    }

    public Student(String name, String email, LocalDate dateOfBirth) {
        this(name, email, dateOfBirth, currentAge(dateOfBirth));
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    private static int currentAge(LocalDate dateOfBirth) {
        return Period.between(dateOfBirth, LocalDate.now()).getYears();
    }
}

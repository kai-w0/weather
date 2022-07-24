package com.example.search.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Employee {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("employee_name")
    private String employeeName;

    @JsonProperty("employee_salary")
    private Long employeeSalary;

    @JsonProperty("employee_age")
    private int age;

    @JsonProperty("profile_image")
    private String profileImage;

    public Employee() {
    }

    public Employee(Long id, String employeeName, Long employeeSalary, int age, String profileImage) {
        this.id = id;
        this.employeeName = employeeName;
        this.employeeSalary = employeeSalary;
        this.age = age;
        this.profileImage = profileImage;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public Long getEmployeeSalary() {
        return employeeSalary;
    }

    public void setEmployeeSalary(Long employeeSalary) {
        this.employeeSalary = employeeSalary;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }
}

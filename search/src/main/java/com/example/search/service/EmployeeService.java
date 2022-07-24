package com.example.search.service;

import com.example.search.domain.CommonResponse;
import com.example.search.domain.EmployeePojo;

public interface EmployeeService {

    EmployeePojo getRestService();
    CommonResponse getAllEmployee();
    CommonResponse getEmployeeByAge(int age);
}

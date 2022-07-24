package com.example.search.service;

import com.example.search.domain.CommonResponse;
import com.example.search.domain.Employee;
import com.example.search.domain.EmployeePojo;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final RestTemplate restTemplate;

    private final String url = "https://dummy.restapiexample.com/api/v1/employees";

    @Autowired
    public EmployeeServiceImpl(@Qualifier("restTemplate") RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public EmployeePojo getRestService() {
        return restTemplate.getForObject(url, EmployeePojo.class);
    }

    @Override
    public CommonResponse getAllEmployee() {
        CommonResponse response = new CommonResponse();
        EmployeePojo pojo = restTemplate.getForObject(url, EmployeePojo.class);
        response.setData(pojo.data);
        return response;
    }

    @Override
    public CommonResponse getEmployeeByAge(int age) {
        CommonResponse response = new CommonResponse();
        EmployeePojo pojo = restTemplate.getForObject(url, EmployeePojo.class);
        List<Employee> list = pojo.data.stream().filter(e -> e.getAge() == age).collect(Collectors.toList());
        EmployeePojo pojo1 = new EmployeePojo(pojo.status, list, pojo.message);
        response.setData(pojo1);
        return response;
    }
}

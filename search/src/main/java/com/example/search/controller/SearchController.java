package com.example.search.controller;

import com.example.search.domain.CommonResponse;
import com.example.search.service.EmployeeService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
public class SearchController {

    private final EmployeeService employeeService;
    private static ExecutorService pool = Executors.newCachedThreadPool();

    @Autowired
    public SearchController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/weather/search")
    public ResponseEntity<?> getDetails() {
        //TODO
        return new ResponseEntity<>("this is search service", HttpStatus.OK);
    }

    @RequestMapping("/employee")
    @HystrixCommand(fallbackMethod = "fallback")
    public ResponseEntity<CommonResponse> getAllEmployee() {
        return new ResponseEntity<>(employeeService.getAllEmployee(), HttpStatus.OK);
    }

    @RequestMapping(value = "/employee", params = "ages")
    @HystrixCommand(fallbackMethod = "fallback1")
    public ResponseEntity<CommonResponse> getEmployeeByAge(@RequestParam int[] ages) {
        CommonResponse response = new CommonResponse();
        List<CompletableFuture> futureList = new ArrayList<>();
        for (int age : ages) {
            CompletableFuture<Object> cf = CompletableFuture.supplyAsync(() -> employeeService.getEmployeeByAge(age).getData(), pool);
            futureList.add(cf);
        }
        List<Object> res = new ArrayList<>();
        CompletableFuture.allOf(futureList.toArray(new CompletableFuture[futureList.size()])).thenApply(Void -> {
            for (CompletableFuture future : futureList) {
                res.add(future.join());
            }
            return res;
        }).join();
        response.setData(res);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private ResponseEntity<CommonResponse> fallback() {
        CommonResponse response = new CommonResponse();
        response.setData("Request fails. It takes long time to response");
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    private ResponseEntity<CommonResponse> fallback1(@RequestParam int[] ages) {
        CommonResponse response = new CommonResponse();
        response.setData("Request fails. It takes long time to response" + ages);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}

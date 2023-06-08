package com.ec2.deployec2.controller;

import com.ec2.deployec2.entity.Employee;
import com.ec2.deployec2.repository.EmployeeRepository;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(value = "/api/v1/employee")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @PostMapping(value = "/save")
    public ResponseEntity<Employee> save(@RequestBody Employee employee){
        log.info("request employee = {}", new Gson().toJson(employee));
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(employeeRepository.save(employee));
    }

    @GetMapping(value = "/list")
    public ResponseEntity<Page<Employee>> list(Pageable pageable){
        log.info("request list = {}", new Gson().toJson(pageable));
        return ResponseEntity.status(HttpStatus.OK)
                .body(employeeRepository.findAll(pageable));
    }

    @GetMapping(value = "/find/{id}")
    public ResponseEntity<Employee> find(@PathVariable("id")Long id){
        log.info("request find by id = {}",id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(employeeRepository.findById(id).get());
    }
}

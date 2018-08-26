package com.spring.oracle.demospringoracle.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.oracle.demospringoracle.entity.Employee;
import com.spring.oracle.demospringoracle.exception.NotFoundException;
import com.spring.oracle.demospringoracle.repository.EmployeeRepository;

@RestController
@RequestMapping(value = "/api/employee")
public class EmployeeController {
	
	@Autowired private EmployeeRepository employeeRepository;
	
	@GetMapping
	public List<Employee> listEmployee(){
		List<Employee> emps = employeeRepository.findAll();
		if (emps.isEmpty()) {
			throw new NotFoundException("sorry employee still null");
		}
		return emps;
	}

	@PostMapping
	public Employee create(@RequestBody Employee employee) {
		Employee emp = Employee.builder()
				.code(employee.getCode())
				.name(employee.getName())
				.email(employee.getEmail())
				.birthday(employee.getBirthday())
				.build();
		
		return employeeRepository.save(emp);
	}
	
	@PutMapping(value = "/{employeeId}")
	public Employee updated(@PathVariable("employeeId") Long employeeId,
						   @RequestBody Employee employee) {
		return employeeRepository.findById(employeeId)
				.map(currentEmployee -> {
					currentEmployee.setName(employee.getName());
					currentEmployee.setCode(employee.getCode());
					currentEmployee.setBirthday(employee.getBirthday());
					return employeeRepository.save(currentEmployee);
				}).orElseThrow(() -> new NotFoundException("sorry employeeId not found"));
	}
	
}

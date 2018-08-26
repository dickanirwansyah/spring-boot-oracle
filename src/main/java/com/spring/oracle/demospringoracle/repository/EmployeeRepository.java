package com.spring.oracle.demospringoracle.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.oracle.demospringoracle.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long>{

}

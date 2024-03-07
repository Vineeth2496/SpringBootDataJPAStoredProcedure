package com.SpringBootJDataJPA.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.SpringBootJDataJPA.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}

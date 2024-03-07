package com.SpringBootJDataJPA.runner;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.SpringBootJDataJPA.model.Employee;

import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.StoredProcedureQuery;
@Component
public class EmployeeStoredProcRunner implements CommandLineRunner {
	@Autowired
	private EntityManager em;
	@Override
	public void run(String... args) throws Exception {

		/*
		// Example 1
		//1. create Query using proc-name and type for output
		StoredProcedureQuery query=em.createStoredProcedureQuery("GETALLEMPS", Employee.class);
		//2. execute / call query
		List<Employee> emps=query.getResultList();
		//3. Print data
		emps.forEach(System.out::println);
		*/
		
		/*
		// Example 2
		// {call GETEMPBYDEPT(?)}
		StoredProcedureQuery query=em.createStoredProcedureQuery("GETEMPBYDEPT", Employee.class);
		// register parameter with name, data type, model(IN/OUT)
		query.registerStoredProcedureParameter("emp_dept", String.class, ParameterMode.IN);
		// pass input data
		query.setParameter("emp_dept", "DEV");
		List<Employee> emps=query.getResultList();
		emps.forEach(System.out::println);
		*/
		
		// Example 3
		//{call GETEMPBYDEPTCOUNT(?,?)}
		StoredProcedureQuery query=em.createStoredProcedureQuery("GETEMPBYDEPTCOUNT", Employee.class);
		// register parameter with name, data type, model(IN/OUT)
		query.registerStoredProcedureParameter("emp_dept", String.class, ParameterMode.IN);
		query.registerStoredProcedureParameter("dept_count", Integer.class, ParameterMode.OUT);
		// pass input data
		query.setParameter("emp_dept", "QA");
		
		// execute (not list this time)
		query.execute();
		
		//read output
		Integer count=(Integer)query.getOutputParameterValue("dept_count");
		System.out.println(count);
	}

}

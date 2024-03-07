package com.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.model.Employee;

public interface EmployeeDao extends JpaRepository<Employee, String> {
	
	@Query("from Employee where age=:age")
	public List<Employee> findByAgeWhereAgeIsGreaterThan(int age);
	
}

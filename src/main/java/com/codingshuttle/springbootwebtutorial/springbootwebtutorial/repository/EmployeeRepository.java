package com.codingshuttle.springbootwebtutorial.springbootwebtutorial.repository;

import com.codingshuttle.springbootwebtutorial.springbootwebtutorial.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity,Long> {

}

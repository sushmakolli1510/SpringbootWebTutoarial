package com.codingshuttle.springbootwebtutorial.springbootwebtutorial.repository;

import com.codingshuttle.springbootwebtutorial.springbootwebtutorial.entity.DepartmentEntity;
import com.codingshuttle.springbootwebtutorial.springbootwebtutorial.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<DepartmentEntity,Long> {
}

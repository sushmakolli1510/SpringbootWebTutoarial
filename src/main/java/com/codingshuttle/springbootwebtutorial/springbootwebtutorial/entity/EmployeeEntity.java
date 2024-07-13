package com.codingshuttle.springbootwebtutorial.springbootwebtutorial.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="employees")
public class EmployeeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO )
    private Long id;
    private String name;
    private String email;
    private Integer age;
    private boolean isActive;
    private LocalDate dateOfJoining;
    private String role;
    private Double salary;
    private String creditCardNumber;
    private Integer experience;
    private String password;

}

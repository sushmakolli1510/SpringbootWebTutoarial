package com.codingshuttle.springbootwebtutorial.springbootwebtutorial.DTO;

import com.codingshuttle.springbootwebtutorial.springbootwebtutorial.annotations.EmployeeRoleValidation;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.CreditCardNumber;
import org.hibernate.validator.constraints.Range;

import java.time.LocalDate;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO {
    private Long id;
    @NotBlank(message = "name should not be blank")
    @Size(min = 3, max = 20, message = "number of characters in the name should be in range 3 and 20")
    private String name;
    @Email(message = "mail should be valid one")
    @NotBlank(message = "mail should not be null")
    private String email;
    @Min(value = 18, message = "age should be greater than 5")
    @Max(value = 80, message = "age should be less than 80")
    private Integer age;
    private boolean isActive;
    @PastOrPresent(message = "date of joining should be past or present")
    private LocalDate dateOfJoining;
    @NotBlank(message = "role of employee should not be null")
    @EmployeeRoleValidation
    private String role;
    @Positive(message = "salary of employee should be positive")
    @Digits(integer = 6, fraction = 2, message = "salary should be 6 digit integer and 2 digit fraction")
    @DecimalMax(value = "100000.99")
    @DecimalMin(value = "100.99")
    private Double salary;
    @CreditCardNumber(message = "credit card number should be valid")
    private String creditCardNumber;
    @Range(min = 1, max = 10, message = "experience should be in range 1 and 10")
    private Integer experience;


}

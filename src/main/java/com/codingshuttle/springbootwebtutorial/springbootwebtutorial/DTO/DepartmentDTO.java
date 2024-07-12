package com.codingshuttle.springbootwebtutorial.springbootwebtutorial.DTO;

import com.codingshuttle.springbootwebtutorial.springbootwebtutorial.annotations.PrimeNumberValidation;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.URL;

import java.time.LocalDate;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentDTO {
    private Long id;
    @NotBlank(message = "name should not be blank")
    @Size(min = 3, max = 20, message = "number of characters in the name should be in range 3 and 20")
    private String title;
    private boolean isActive;
    @PastOrPresent(message = "date of joining should be past or present")
    private LocalDate createdAt;
    @PrimeNumberValidation(message = "Department capacity should be a prime number")
    private Integer capacity;
    @URL(message = "department website should be a valid URL")
    private String website;
    @NotEmpty(message = "department description should not be empty")
    private String description;
    @Range(min = 1, max = 5, message = "department rating should be in range 1 and 5")
    private Integer rating;
}

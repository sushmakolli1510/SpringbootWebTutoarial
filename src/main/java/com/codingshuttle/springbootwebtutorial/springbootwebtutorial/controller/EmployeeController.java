package com.codingshuttle.springbootwebtutorial.springbootwebtutorial.controller;

import com.codingshuttle.springbootwebtutorial.springbootwebtutorial.DTO.EmployeeDTO;
import com.codingshuttle.springbootwebtutorial.springbootwebtutorial.exception.ResourceNotFoundException;
import com.codingshuttle.springbootwebtutorial.springbootwebtutorial.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping(path="/employees")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping(path="/{employeeid}")
    public ResponseEntity<EmployeeDTO> getEmployeesById(@PathVariable(name="employeeid") Long id){
        Optional<EmployeeDTO> employeeDTO = employeeService.findByEmployeeId(id);
        return employeeDTO.map(employeeDTO1->ResponseEntity.ok(employeeDTO1)).orElseThrow(()->new ResourceNotFoundException("Employee not found with id:"+id));
    }



    @GetMapping
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees(){

        return ResponseEntity.ok(employeeService.getAllEmployees());
    }

    @PostMapping
    public ResponseEntity<EmployeeDTO> createNewEmployee(@RequestBody @Valid EmployeeDTO inputEmployee){

        EmployeeDTO employeeDTO= employeeService.saveEmployee(inputEmployee);
        return new ResponseEntity<>(employeeDTO, HttpStatus.CREATED);
    }

    @PutMapping(path="/{employeeId}")
    public ResponseEntity<EmployeeDTO> updateEmployeeById(@RequestBody @Valid EmployeeDTO inputEmployee,@PathVariable Long employeeId){
        return ResponseEntity.ok(employeeService.updateEmployeeById(inputEmployee,employeeId));
    }

    @DeleteMapping(path="/{employeeId}")
    public ResponseEntity<Boolean> deleteEmployeeById(@PathVariable Long employeeId){

       boolean deletedEmployee= employeeService.deleteEmployeeById(employeeId);
       if(deletedEmployee){
           return ResponseEntity.ok(true);
       }
       return ResponseEntity.notFound().build();
    }

    @PatchMapping(path="/{employeeId}")
    public ResponseEntity<EmployeeDTO> updatePartialEmployeeById(@RequestBody Map<String,Object> value, @PathVariable Long employeeId){
        EmployeeDTO employeeDTO= employeeService.updatePartialEmployeeById(value,employeeId);
        if(employeeDTO==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(employeeDTO);
    }





}

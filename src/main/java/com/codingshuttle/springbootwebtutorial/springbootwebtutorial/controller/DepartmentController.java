package com.codingshuttle.springbootwebtutorial.springbootwebtutorial.controller;

import com.codingshuttle.springbootwebtutorial.springbootwebtutorial.DTO.DepartmentDTO;
import com.codingshuttle.springbootwebtutorial.springbootwebtutorial.DTO.EmployeeDTO;
import com.codingshuttle.springbootwebtutorial.springbootwebtutorial.exception.ResourceNotFoundException;
import com.codingshuttle.springbootwebtutorial.springbootwebtutorial.service.DepartmentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(path="/departments")
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    @GetMapping(path="/{departmentid}")
    public ResponseEntity<DepartmentDTO> getDepartmentById(@PathVariable(name="departmentid") Long id){
        Optional<DepartmentDTO> departmentDTO = departmentService.findByDepartmentId(id);
        return departmentDTO.map(departmentDTO1->ResponseEntity.ok(departmentDTO1)).orElseThrow(()->new ResourceNotFoundException("Department not found with id:"+id));
    }

    @GetMapping
    public ResponseEntity<List<DepartmentDTO>> getAllEmployees(){

        return ResponseEntity.ok(departmentService.getAllDepartments());
    }

    @PostMapping( produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DepartmentDTO> createNewEmployee(@RequestBody @Valid DepartmentDTO department){

        DepartmentDTO departmentDTO= departmentService.saveDepartment(department);
        return new ResponseEntity<>(departmentDTO, HttpStatus.CREATED);
    }

    @PutMapping(path="/{departmentId}")
    public ResponseEntity<DepartmentDTO> updateDepartmentById(@RequestBody @Valid DepartmentDTO inputDepartment,@PathVariable Long departmentId){
        return ResponseEntity.ok(departmentService.updateDepartmentById(inputDepartment,departmentId));
    }
    @DeleteMapping(path="/{departmentId}")
    public ResponseEntity<Boolean> deleteDepartmentById(@PathVariable Long departmentId){

        boolean deletedDepartment= departmentService.deleteDepartmentById(departmentId);
        if(deletedDepartment){
            return ResponseEntity.ok(true);
        }
        return ResponseEntity.notFound().build();
    }
    @PatchMapping(path="/{departmentId}")
    public ResponseEntity<DepartmentDTO> updatePartialEmployeeById(@RequestBody Map<String,Object> value, @PathVariable Long departmentId){
        DepartmentDTO departmentDTO= departmentService.updatePartialDepartmentById(value,departmentId);
        if(departmentDTO==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(departmentDTO);
    }

}

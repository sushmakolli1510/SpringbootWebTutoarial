package com.codingshuttle.springbootwebtutorial.springbootwebtutorial.service;

import com.codingshuttle.springbootwebtutorial.springbootwebtutorial.DTO.EmployeeDTO;
import com.codingshuttle.springbootwebtutorial.springbootwebtutorial.entity.EmployeeEntity;
import com.codingshuttle.springbootwebtutorial.springbootwebtutorial.exception.ResourceNotFoundException;
import com.codingshuttle.springbootwebtutorial.springbootwebtutorial.repository.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private ModelMapper modelMapper;

    public Optional<EmployeeDTO> findByEmployeeId(Long id) {
//        Optional<EmployeeEntity> employeeEntity= employeeRepository.findById(id);
//        return employeeEntity.map(employeeEntity1->(modelMapper.map(employeeEntity1,EmployeeDTO.class)));
        return employeeRepository.findById(id).map(employeeEntity1->modelMapper.map(employeeEntity1,EmployeeDTO.class));
    }

    public List<EmployeeDTO> getAllEmployees() {
        List<EmployeeEntity> employees=employeeRepository.findAll();

        return employees.stream().map(employeeEntity->modelMapper.map(employeeEntity,EmployeeDTO.class)).collect(Collectors.toList());
    }


    public EmployeeDTO saveEmployee(EmployeeDTO inputEmployee) {
        EmployeeEntity tosaveEntity=modelMapper.map(inputEmployee,EmployeeEntity.class);
        EmployeeEntity employee=employeeRepository.save(tosaveEntity);
        return modelMapper.map(employee,EmployeeDTO.class);
    }

    public EmployeeDTO updateEmployeeById( EmployeeDTO inputEmployee,Long employeeId) {
        isExistsEmployeeById(employeeId);
        EmployeeEntity employeeEntity=modelMapper.map(inputEmployee,EmployeeEntity.class);
        employeeEntity.setId(employeeId);
        EmployeeEntity savedEmployeeEntity =employeeRepository.save(employeeEntity);
        return modelMapper.map(savedEmployeeEntity,EmployeeDTO.class);
    }

    public boolean isExistsEmployeeById(Long employeeId){
        boolean exists =employeeRepository.existsById(employeeId);
        if(!exists){
            throw new ResourceNotFoundException("Employee not found with id:"+employeeId);
        }
        return true;
    }

    public boolean deleteEmployeeById(Long employeeId) {
        isExistsEmployeeById(employeeId);
        employeeRepository.deleteById(employeeId);
        return true;
    }

    public EmployeeDTO updatePartialEmployeeById(Map<String, Object> updates, Long employeeId) {
        boolean exists=isExistsEmployeeById(employeeId);
        if(!exists){
            return null;
        }
        EmployeeEntity employeeEntity=employeeRepository.findById(employeeId).get();
        updates.forEach((field,value)-> {
            Field fieldToBeUpdated = ReflectionUtils.findRequiredField(EmployeeEntity.class, field);
            fieldToBeUpdated.setAccessible(true);
            ReflectionUtils.setField(fieldToBeUpdated, employeeEntity, value);
        }
        );
        return modelMapper.map(employeeRepository.save(employeeEntity),EmployeeDTO.class);
    }
}

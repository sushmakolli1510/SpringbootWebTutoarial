package com.codingshuttle.springbootwebtutorial.springbootwebtutorial.service;

import com.codingshuttle.springbootwebtutorial.springbootwebtutorial.DTO.DepartmentDTO;
import com.codingshuttle.springbootwebtutorial.springbootwebtutorial.DTO.EmployeeDTO;
import com.codingshuttle.springbootwebtutorial.springbootwebtutorial.entity.DepartmentEntity;
import com.codingshuttle.springbootwebtutorial.springbootwebtutorial.entity.EmployeeEntity;
import com.codingshuttle.springbootwebtutorial.springbootwebtutorial.exception.ResourceNotFoundException;
import com.codingshuttle.springbootwebtutorial.springbootwebtutorial.repository.DepartmentRepository;
import com.codingshuttle.springbootwebtutorial.springbootwebtutorial.repository.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private ModelMapper modelMapper;



    public Optional<DepartmentDTO> findByDepartmentId(Long id) {

        return departmentRepository.findById(id).map(departmentEntity1->modelMapper.map(departmentEntity1,DepartmentDTO.class));
    }

    public List<DepartmentDTO> getAllDepartments() {
        List<DepartmentEntity> department=departmentRepository.findAll();

        return department.stream().map(departmentEntity->modelMapper.map(departmentEntity,DepartmentDTO.class)).collect(Collectors.toList());
    }

    public DepartmentDTO saveDepartment(DepartmentDTO inputDepartment) {
        DepartmentEntity tosaveEntity=modelMapper.map(inputDepartment,DepartmentEntity.class);
        DepartmentEntity department=departmentRepository.save(tosaveEntity);
        return modelMapper.map(department,DepartmentDTO.class);
    }

    public DepartmentDTO updateDepartmentById( DepartmentDTO inputDepartment,Long departmentId) {
        isExistsDepartmentById(departmentId);
        DepartmentEntity departmentEntity=modelMapper.map(inputDepartment,DepartmentEntity.class);
        departmentEntity.setId(departmentId);
        DepartmentEntity savedDepartmentEntity =departmentRepository.save(departmentEntity);
        return modelMapper.map(savedDepartmentEntity,DepartmentDTO.class);
    }
    public boolean isExistsDepartmentById(Long departmentId){
        boolean exists =departmentRepository.existsById(departmentId);
        if(!exists){
            throw new ResourceNotFoundException("Department not found with id:"+departmentId);
        }
        return true;
    }
    public boolean deleteDepartmentById(Long departmentId) {
        isExistsDepartmentById(departmentId);
        departmentRepository.deleteById(departmentId);
        return true;
    }
    public DepartmentDTO updatePartialDepartmentById(Map<String, Object> updates, Long departmentId) {
        boolean exists=isExistsDepartmentById(departmentId);
        if(!exists){
            return null;
        }
        DepartmentEntity departmentEntity=departmentRepository.findById(departmentId).get();
        updates.forEach((field,value)-> {
                    Field fieldToBeUpdated = ReflectionUtils.findRequiredField(DepartmentEntity.class, field);
                    fieldToBeUpdated.setAccessible(true);
                    ReflectionUtils.setField(fieldToBeUpdated, departmentEntity, value);
                }
        );
        return modelMapper.map(departmentRepository.save(departmentEntity),DepartmentDTO.class);
    }

}

package bansira.java.library.library.controller;

import bansira.java.library.library.dtos.DepartmentDto;
import bansira.java.library.library.exception.DepartmentNotFound;
import bansira.java.library.library.model.Department;
import bansira.java.library.library.respository.DepartmentRepository;
import bansira.java.library.library.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/departments")
@RequiredArgsConstructor
@Slf4j
public class DepartmentController {
    @Autowired private final DepartmentService departmentService;
    @Autowired private final DepartmentRepository departmentRepository;

    @GetMapping("/{id}")
    public ResponseEntity<DepartmentDto> getDepartmentById(@PathVariable String id) throws DepartmentNotFound {
        DepartmentDto departmentDto = departmentService.findDepartmentById(id);
        if (departmentDto == null) {
            throw new DepartmentNotFound("Department not found with ID: " + id);
        }
        return ResponseEntity.status(200).body(departmentDto);
    }

    @PostMapping
    public ResponseEntity<DepartmentDto> createDepartment(@RequestBody DepartmentDto departmentDto) {
         return ResponseEntity.status(200).body(departmentService.saveDepartment(departmentDto));
    }
    @GetMapping
    public ResponseEntity<List<DepartmentDto>> getAllDepartments() {
        return ResponseEntity.ok(departmentService.getAllDepartments());
    }
    @DeleteMapping
    public void deleteAll() {
        departmentRepository.deleteAll();
    }
}

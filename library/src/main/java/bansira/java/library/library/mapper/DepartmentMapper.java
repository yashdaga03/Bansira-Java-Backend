package bansira.java.library.library.mapper;

import bansira.java.library.library.dtos.DepartmentDto;
import bansira.java.library.library.model.Department;

public class DepartmentMapper {
    public static DepartmentDto mapToDepartmentDto(Department department) {
        return DepartmentDto.builder()
                .id(department.getId())
                .name(department.getName())
                .departmentHits(department.getDepartmentHits())
                .books(department.getBooks())
                .build();
    }
    public static Department mapToDepartment(DepartmentDto departmentDto) {
        return Department.builder()
                .id(departmentDto.getId())
                .name(departmentDto.getName())
                .departmentHits(departmentDto.getDepartmentHits())
                .books(departmentDto.getBooks())
                .build();
    }
}

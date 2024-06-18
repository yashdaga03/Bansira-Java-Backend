package bansira.java.library.library.service;

import bansira.java.library.library.dtos.DepartmentDto;
import bansira.java.library.library.mapper.DepartmentMapper;
import bansira.java.library.library.model.Book;
import bansira.java.library.library.model.Department;
import bansira.java.library.library.respository.BookRepository;
import bansira.java.library.library.respository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class DepartmentService {
    @Autowired private final DepartmentRepository departmentRepository;
    @Autowired private final BookRepository bookRepository;

    public DepartmentDto findDepartmentById(String id) {
        Optional<Department> department = departmentRepository.findById(id);
        if (department.isEmpty()) {
            return null;
        }
        List<String> bookIds = getBookIds(department.get().getBooks());
        int hits = getDailyDepartmentHits(bookIds);
        Department updatedDepartment = Department.builder()
                .departmentHits(hits)
                .id(department.get().getId())
                .books(department.get().getBooks())
                .name(department.get().getName())
                .build();
        return DepartmentMapper.mapToDepartmentDto(departmentRepository.save(updatedDepartment));
    }

    private List<String> getBookIds(List<Book> books) {
        List<String> bookIds = new ArrayList<>();
        for (Book book: books) {
            bookIds.add(book.getId());
        }
        return bookIds;
    }
    private int getDailyDepartmentHits(List<String> bookIds) {
        int hits = 0;
        for (String id: bookIds) {
            Optional<Book> book = bookRepository.findById(id);
            if (book.isPresent()) {
                hits += book.get().getTodayHit();
            }
        }
        return hits;
    }
    private List<Book> getBooks(List<Book> books) {
        List<Book> completeBooks = new ArrayList<>();
        for (Book book: books) {
            Optional<Book> resBook = bookRepository.findById(book.getId());
            resBook.ifPresent(completeBooks::add);
        }
        return completeBooks;
    }
    public DepartmentDto saveDepartment(DepartmentDto departmentDto) {
        List<Book> books = getBooks(departmentDto.getBooks());
        Department department = Department.builder()
                .name(departmentDto.getName())
                .departmentHits(0)
                .books(books)
                .build();
        return DepartmentMapper.mapToDepartmentDto(departmentRepository.save(department));
    }
    public List<DepartmentDto> getAllDepartments() {
        log.info("get all departments service");
        List<Department> departments = departmentRepository.findAll();
        log.info("{}" ,departments);
        List<DepartmentDto> response = new ArrayList<>();
        for (Department department: departments) {
            response.add(DepartmentMapper.mapToDepartmentDto(department));
        }
        return response;
    }
}

package bansira.java.library.library.dtos;

import bansira.java.library.library.model.Book;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class DepartmentDto {
    private String id;
    private String name;
    private int departmentHits;
    private List<Book> books;
}

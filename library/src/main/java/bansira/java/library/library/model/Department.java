package bansira.java.library.library.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(collection = "departments")
@Builder
public class Department {
    @Id
    private String id;
    private String name;
    private int departmentHits;
    @DBRef
    private List<Book> books;
}

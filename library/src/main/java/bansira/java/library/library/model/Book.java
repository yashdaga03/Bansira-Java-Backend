package bansira.java.library.library.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Data
@Builder
@Document(collection = "books")
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    @Id
    private String id;
    private String title;
    private String author;
    private boolean available;
    private int weeklyHit;
    private int monthlyHit;
    private int todayHit;
    private boolean isIssued;
    private Instant createdAt;
    private Instant updatedAt;
}

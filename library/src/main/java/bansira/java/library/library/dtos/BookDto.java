package bansira.java.library.library.dtos;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@Builder
public class BookDto {
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

package bansira.java.library.library.mapper;

import bansira.java.library.library.dtos.BookDto;
import bansira.java.library.library.model.Book;

public class BookMapper {
    public static BookDto mapToBookDto(Book book) {
        return BookDto.builder()
                .id(book.getId())
                .title(book.getTitle())
                .author(book.getAuthor())
                .available(book.isAvailable())
                .createdAt(book.getCreatedAt())
                .updatedAt(book.getUpdatedAt())
                .monthlyHit(book.getMonthlyHit())
                .weeklyHit(book.getWeeklyHit())
                .isIssued(book.isIssued())
                .todayHit(book.getTodayHit())
                .build();
    }
    public static Book mapToBook(BookDto bookDto) {
        return Book.builder()
                .id(bookDto.getId())
                .title(bookDto.getTitle())
                .author(bookDto.getAuthor())
                .available(bookDto.isAvailable())
                .createdAt(bookDto.getCreatedAt())
                .updatedAt(bookDto.getUpdatedAt())
                .monthlyHit(bookDto.getMonthlyHit())
                .weeklyHit(bookDto.getWeeklyHit())
                .isIssued(bookDto.isIssued())
                .todayHit(bookDto.getTodayHit())
                .build();
    }
}

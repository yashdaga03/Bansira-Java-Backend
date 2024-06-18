package bansira.java.library.library.service;

import bansira.java.library.library.dtos.BookDto;
import bansira.java.library.library.model.Book;
import bansira.java.library.library.respository.BookRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.Collections;
import java.util.Optional;


@ExtendWith(MockitoExtension.class)
public class BookServiceTest {
    @Mock private BookRepository bookRepository;

    @InjectMocks private BookService bookService;
    @Mock
    Pageable pageable;

    Book book;
    @BeforeEach
    void setup() {
        book = new Book();
        book.setTitle("Title");
        book.setAuthor("Author");
    }
    @Test
    void testWhenFindAllIsCalled() {
        Page<Book> page = new PageImpl<>(Collections.singletonList(book));
        Mockito.when(bookRepository.findAll(pageable)).thenReturn(page);
        Page<Book> expected = bookService.findAllBooks(pageable);
        Assertions.assertEquals(expected.getSize(), 1);
    }

    @Test
    void testWhenFindBookById_Success() {
        Mockito.when(bookRepository.findById("id")).thenReturn(Optional.ofNullable(book));
        BookDto expected = bookService.findBookById("id");
        Assertions.assertEquals(expected.getAuthor(), "Author");
    }

    @Test
    void testWhenFindBookById_Failure() {
        Mockito.when(bookRepository.findById("id")).thenReturn(Optional.empty());
        BookDto expected = bookService.findBookById("id");
        Assertions.assertNull(expected);
    }

}

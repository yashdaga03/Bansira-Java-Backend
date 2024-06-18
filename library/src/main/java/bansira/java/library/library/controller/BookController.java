package bansira.java.library.library.controller;

import bansira.java.library.library.dtos.BookDto;
import bansira.java.library.library.exception.BookAlreadyIssued;
import bansira.java.library.library.exception.BookNotFoundException;
import bansira.java.library.library.model.Book;
import bansira.java.library.library.respository.BookRepository;
import bansira.java.library.library.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
@Slf4j
public class BookController {
    @Autowired private final BookService bookService;
    @Autowired private BookRepository bookRepository;

    @GetMapping
    public ResponseEntity<Page<Book>> getAllBooks(Pageable pageable) {
        return ResponseEntity.status(200).body(bookService.findAllBooks(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDto> getBookById(@PathVariable String id) throws BookNotFoundException {
        BookDto bookDto = bookService.findBookById(id);
        if (bookDto == null) {
            log.error("Book not found with ID: {}", id);
            throw new BookNotFoundException("Book not found with given ID: " + id);
        }
        return ResponseEntity.status(200).body(bookDto);
    }

    @GetMapping("/search")
    public ResponseEntity<Page<BookDto>> getBooksByTitle(@RequestParam String title, Pageable pageable) throws BookNotFoundException {
        Page<BookDto> bookDto = bookService.findBooksByTitle(title, pageable);
        if (bookDto == null) {
            log.error("Book not found with Title: {}", title);
            throw new BookNotFoundException("Book not found with given Title: " + title);
        }
        return ResponseEntity.status(200).body(bookDto);
    }

    @GetMapping("/author")
    public ResponseEntity<Page<BookDto>> getBooksByAuthor(@RequestParam String author, Pageable pageable) throws BookNotFoundException {
        Page<BookDto> bookDto = bookService.findBooksByAuthor(author, pageable);
        if (bookDto == null) {
            log.error("Book not found with Author: {}", author);
            throw new BookNotFoundException("Book not found with given Author: " + author);
        }
        return ResponseEntity.status(200).body(bookDto);
    }

    @GetMapping("/available")
    public ResponseEntity<Page<BookDto>> getAvailableBooks(Pageable pageable) throws BookNotFoundException {
        Page<BookDto> bookDto = bookService.findAvailableBooks(pageable);
        if (bookDto == null) {
            log.error("No Available Books Found");
            throw new BookNotFoundException("No Available Books Found");
        }
        return ResponseEntity.status(200).body(bookDto);
    }


    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public BookDto createBook(@RequestBody BookDto bookDto) {
        return bookService.saveBook(bookDto);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/{id}")
    public ResponseEntity<BookDto> updateBook(@PathVariable String id, @RequestBody BookDto bookDto) {
        BookDto resBookDto = bookService.updateBook(id, bookDto);
        if (resBookDto == null) {
            log.error("Book not Found with ID: " + id);
            throw new BookNotFoundException("Book not Found with ID: " + id);
        }
        return ResponseEntity.status(200).body(resBookDto);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable String id) {
        bookService.deleteBookById(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/issue/{id}")
    public ResponseEntity<BookDto> issueBook(@PathVariable String id) throws BookNotFoundException {
        BookDto bookDto = bookService.issueBook(id);
        if (bookDto == null) {
            log.error("Book not Found with ID: " + id);
            throw new BookNotFoundException("Book not Found with ID: " + id);
        }
        return ResponseEntity.status(200).body(bookDto);
    }
    @DeleteMapping("/deleteAll")
    public void delete() {
        bookRepository.deleteAll();
    }
}

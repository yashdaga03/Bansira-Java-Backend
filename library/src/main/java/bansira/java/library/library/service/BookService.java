package bansira.java.library.library.service;

import bansira.java.library.library.dtos.BookDto;
import bansira.java.library.library.exception.BookAlreadyIssued;
import bansira.java.library.library.exception.BookNotFoundException;
import bansira.java.library.library.mapper.BookMapper;
import bansira.java.library.library.model.Book;
import bansira.java.library.library.respository.BookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookService {

    @Autowired
    private final BookRepository bookRepository;

    public Page<Book> findAllBooks(Pageable pageable) {
        return bookRepository.findAll(pageable);
    }

    public BookDto findBookById(String id) throws BookNotFoundException {
        Optional<Book> book = bookRepository.findById(id);
        return book.map(BookMapper::mapToBookDto).orElse(null);
    }

    public Page<BookDto> findBooksByTitle(String title, Pageable pageable) {
        Page<Book> books = bookRepository.findByTitle(title, pageable);
        if (books.isEmpty()) {
            return null;
        }
        return convertBooktoBookDto(books);
    }

    private Page<BookDto> convertBooktoBookDto(Page<Book> bookPage) {
        return bookPage.map(BookMapper::mapToBookDto);
    }

    public Page<BookDto> findBooksByAuthor(String author, Pageable pageable) {
        Page<Book> books = bookRepository.findByAuthor(author, pageable);
        if (books.isEmpty()) {
            return null;
        }
        return convertBooktoBookDto(books);
    }

    public Page<BookDto> findAvailableBooks(Pageable pageable) {
        Page<Book> books = bookRepository.findByAvailable(true, pageable);
        return convertBooktoBookDto(books);
    }

    public BookDto saveBook(BookDto bookDto) {
        Book book = BookMapper.mapToBook(bookDto);
        book.setCreatedAt(Instant.now());
        book.setUpdatedAt(Instant.now());
        book.setMonthlyHit(0);
        book.setTodayHit(0);
        book.setTodayHit(0);
        book.setIssued(false);
        return BookMapper.mapToBookDto(bookRepository.save(book));
    }

    public BookDto updateBook(String id, BookDto bookDto) {
        BookDto resBookDto = findBookById(id);
        if (resBookDto == null) {return null;}
        resBookDto.setUpdatedAt(Instant.now());
        resBookDto.setTitle(bookDto.getTitle());
        resBookDto.setAuthor(bookDto.getAuthor());
        resBookDto.setAvailable(bookDto.isAvailable());
        Book book = bookRepository.save(BookMapper.mapToBook(resBookDto));
        return BookMapper.mapToBookDto(book);
    }
    private void updateLeaderBoardDetails(Book book) {
        book.setTodayHit(book.getTodayHit() + 1);
        book.setMonthlyHit(book.getMonthlyHit() + 1);
        book.setWeeklyHit(book.getWeeklyHit() + 1);
        bookRepository.save(book);
    }

    public void deleteBookById(String id) {
        bookRepository.deleteById(id);
    }
    public BookDto issueBook(String id) throws BookAlreadyIssued {
        Optional<Book> book = bookRepository.findById(id);
        if (book.isEmpty()) {
            return null;
        }
        updateLeaderBoardDetails(book.get());
        if (book.get().isIssued()) {
            log.error("Book already Issued with given ID: {}", id);
            throw new BookAlreadyIssued("Book already Issued with given ID: " + id);
        }
        book.get().setIssued(true);
        return BookMapper.mapToBookDto(bookRepository.save(book.get()));
    }
    public void resetDaily() {
        List<Book> books = bookRepository.findAll();
        for (Book book: books) {
            book.setTodayHit(0);
            bookRepository.save(book);
        }
    }
    public void resetWeekly() {
        List<Book> books = bookRepository.findAll();
        for (Book book: books) {
            book.setWeeklyHit(0);
            bookRepository.save(book);
        }
    }
    public void resetMonthly() {
        List<Book> books = bookRepository.findAll();
        for (Book book: books) {
            book.setMonthlyHit(0);
            bookRepository.save(book);
        }
    }
}

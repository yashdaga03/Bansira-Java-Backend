package bansira.java.library.library.respository;

import bansira.java.library.library.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends MongoRepository<Book, String> {
    Page<Book> findByTitle(String title, Pageable pageable);
    Page<Book> findByAuthor(String author, Pageable pageable);
    Page<Book> findByAvailable(boolean available, Pageable pageable);
}

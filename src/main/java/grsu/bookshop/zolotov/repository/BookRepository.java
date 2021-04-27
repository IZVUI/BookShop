package grsu.bookshop.zolotov.repository;

import grsu.bookshop.zolotov.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    Book findByBookname(String bookName);
    List<Book> findBookByBookname(String bookName);
}

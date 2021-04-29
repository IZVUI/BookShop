package grsu.bookshop.zolotov.repository;

import grsu.bookshop.zolotov.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    Book findByBookname(String bookName);
    List<Book> findBookByBookname(String bookName);
}

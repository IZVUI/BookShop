package grsu.bookshop.zolotov.repository;

import grsu.bookshop.zolotov.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String name);
    Optional<User> findById(Long id);


    List<User> findUsersByUsername(String name);
}

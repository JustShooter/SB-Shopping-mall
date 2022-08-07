package by.itacademy.justshooter.shoppingmall.repository;

import by.itacademy.justshooter.shoppingmall.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    @Query("select u from User u where u.userName = ?1")
    Optional<User> findByUserName(@NonNull String userName);

    

}

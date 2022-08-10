package by.itacademy.justshooter.shoppingmall.repository;

import by.itacademy.justshooter.shoppingmall.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
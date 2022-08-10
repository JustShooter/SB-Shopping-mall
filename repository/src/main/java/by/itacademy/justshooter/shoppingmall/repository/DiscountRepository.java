package by.itacademy.justshooter.shoppingmall.repository;

import by.itacademy.justshooter.shoppingmall.entity.Discount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiscountRepository extends JpaRepository<Discount, Integer> {
}
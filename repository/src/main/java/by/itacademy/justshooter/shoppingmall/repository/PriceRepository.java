package by.itacademy.justshooter.shoppingmall.repository;

import by.itacademy.justshooter.shoppingmall.entity.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PriceRepository extends JpaRepository<Price, Integer>, JpaSpecificationExecutor<Price> {
}
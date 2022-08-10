package by.itacademy.justshooter.shoppingmall.repository;

import by.itacademy.justshooter.shoppingmall.entity.Item;
import by.itacademy.justshooter.shoppingmall.entity.ShopItemPriceDiscount;
import by.itacademy.justshooter.shoppingmall.entity.ShopItemPriceDiscountId;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;

import java.util.List;

public interface ShopItemPriceDiscountRepository extends
        JpaRepository<ShopItemPriceDiscount, ShopItemPriceDiscountId>,
        JpaSpecificationExecutor<ShopItemPriceDiscount> {


}
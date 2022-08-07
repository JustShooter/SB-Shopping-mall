package by.itacademy.justshooter.shoppingmall.repository;

import by.itacademy.justshooter.shoppingmall.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Integer> ,
        JpaSpecificationExecutor<Item> {

    @Query("select i from item i inner join i.shopItemPriceDiscounts shopItemPriceDiscounts " +
            "where shopItemPriceDiscounts.shop.id = ?1")
    List<Item> findByShopItemPriceDiscounts_Shop_Id(@NonNull Integer id);


}
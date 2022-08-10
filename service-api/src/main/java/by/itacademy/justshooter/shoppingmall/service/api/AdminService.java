package by.itacademy.justshooter.shoppingmall.service.api;


import by.itacademy.justshooter.shoppingmall.service.dtos.AddressDto;
import by.itacademy.justshooter.shoppingmall.service.dtos.CategoryDto;
import by.itacademy.justshooter.shoppingmall.service.dtos.ItemDto;
import by.itacademy.justshooter.shoppingmall.service.dtos.LocationDto;
import by.itacademy.justshooter.shoppingmall.service.dtos.ShopOwnerDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AdminService {

    List<CategoryDto> getAllCategories();


    CategoryDto getCategoryById(Integer id);

    void saveCategory(CategoryDto category);

    void deleteCategory(Integer id);

    List<LocationDto> getAllLocations();

    void saveLocation(LocationDto location);

    LocationDto getLocationById(Integer id);

    void deleteLocation(Integer id);

    List<ShopOwnerDto> getAllShopOwners();

    List<AddressDto> getAllAddresses();

    ShopOwnerDto getShopOwnerById(Integer id);

    void saveShopOwner(ShopOwnerDto shopOwner);

    void saveAddress(AddressDto address);

    AddressDto getAddressById(Integer id);

    void deleteAddress(Integer id);

    List<ItemDto> getAllItems();

    ItemDto getItemById(Integer id);

    void deleteItem(Integer id);

    void saveItem(ItemDto item);

    Page<ItemDto> findPaginatedItems(Pageable pageable);
}

package by.itacademy.justshooter.shoppingmall.service.impl;

import by.itacademy.justshooter.shoppingmall.entity.Address;
import by.itacademy.justshooter.shoppingmall.entity.Category;
import by.itacademy.justshooter.shoppingmall.entity.Item;
import by.itacademy.justshooter.shoppingmall.entity.Location;
import by.itacademy.justshooter.shoppingmall.entity.ShopOwner;
import by.itacademy.justshooter.shoppingmall.repository.AddressRepository;
import by.itacademy.justshooter.shoppingmall.repository.CategoryRepository;
import by.itacademy.justshooter.shoppingmall.repository.ItemRepository;
import by.itacademy.justshooter.shoppingmall.repository.LocationRepository;
import by.itacademy.justshooter.shoppingmall.repository.ShopOwnerRepository;
import by.itacademy.justshooter.shoppingmall.service.api.AdminService;
import by.itacademy.justshooter.shoppingmall.service.dtos.AddressDto;
import by.itacademy.justshooter.shoppingmall.service.dtos.CategoryDto;
import by.itacademy.justshooter.shoppingmall.service.dtos.ItemDto;
import by.itacademy.justshooter.shoppingmall.service.dtos.LocationDto;
import by.itacademy.justshooter.shoppingmall.service.dtos.ShopOwnerDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final ModelMapper modelMapper;
    private final CategoryRepository categoryRepository;
    private final LocationRepository locationRepository;
    private final ShopOwnerRepository shopOwnerRepository;
    private final AddressRepository addressRepository;
    private final ItemRepository itemRepository;

    @Override
    public List<CategoryDto> getAllCategories() {
        return categoryRepository.findAll()
                .stream()
                .map(category -> modelMapper.map(category, CategoryDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public CategoryDto getCategoryById(Integer id) {
        return categoryRepository.findById(id)
                .map(category -> modelMapper.map(category, CategoryDto.class))
                .orElseThrow();
    }

    @Override
    public void saveCategory(CategoryDto category) {
        categoryRepository.save(modelMapper.map(category, Category.class));
    }

    @Override
    public void deleteCategory(Integer id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public List<LocationDto> getAllLocations() {
        return locationRepository.findAll()
                .stream()
                .map(location -> modelMapper.map(location, LocationDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public void saveLocation(LocationDto location) {
        locationRepository.save(modelMapper.map(location, Location.class));
    }

    @Override
    public LocationDto getLocationById(Integer id) {
        return locationRepository.findById(id)
                .map(location -> modelMapper.map(location, LocationDto.class))
                .orElseThrow();
    }

    @Override
    public void deleteLocation(Integer id) {
        locationRepository.deleteById(id);
    }

    @Override
    public List<ShopOwnerDto> getAllShopOwners() {
        return shopOwnerRepository.findAll()
                .stream()
                .map(shopOwner -> modelMapper.map(shopOwner, ShopOwnerDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<AddressDto> getAllAddresses() {
        return addressRepository.findAll()
                .stream()
                .map(address -> modelMapper.map(address, AddressDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public ShopOwnerDto getShopOwnerById(Integer id) {
        return shopOwnerRepository.findById(id)
                .map(shopOwner -> modelMapper.map(shopOwner, ShopOwnerDto.class))
                .orElseThrow();
    }

    @Override
    public void saveShopOwner(ShopOwnerDto shopOwner) {
        shopOwnerRepository.save(modelMapper.map(shopOwner, ShopOwner.class));
    }

    @Override
    public void saveAddress(AddressDto address) {
        addressRepository.save(modelMapper.map(address, Address.class));
    }

    @Override
    public AddressDto getAddressById(Integer id) {
        return addressRepository.findById(id)
                .map(address -> modelMapper.map(address, AddressDto.class))
                .orElseThrow();
    }

    @Override
    public void deleteAddress(Integer id) {
        addressRepository.deleteById(id);
    }

    @Override
    public List<ItemDto> getAllItems() {
        return itemRepository.findAll()
                .stream()
                .map(item -> modelMapper.map(item, ItemDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public ItemDto getItemById(Integer id) {
        return itemRepository.findById(id)
                .map(item -> modelMapper.map(item, ItemDto.class))
                .orElseThrow();
    }

    @Override
    public void deleteItem(Integer id) {
        itemRepository.deleteById(id);
    }

    @Override
    public void saveItem(ItemDto item) {
        itemRepository.save(modelMapper.map(item, Item.class));
    }

    @Override
    public Page<ItemDto> findPaginatedItems(Pageable pageable) {
        Page<Item> items = itemRepository.findAll(pageable);
        return items.map(item -> modelMapper.map(item, ItemDto.class));
    }

}

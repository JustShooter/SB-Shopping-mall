package by.itacademy.justshooter.shoppingmall.service.impl;

import by.itacademy.justshooter.shoppingmall.entity.Address;
import by.itacademy.justshooter.shoppingmall.entity.Category;
import by.itacademy.justshooter.shoppingmall.entity.Location;
import by.itacademy.justshooter.shoppingmall.entity.ShopOwner;
import by.itacademy.justshooter.shoppingmall.repository.AddressRepository;
import by.itacademy.justshooter.shoppingmall.repository.CategoryRepository;
import by.itacademy.justshooter.shoppingmall.repository.LocationRepository;
import by.itacademy.justshooter.shoppingmall.repository.ShopOwnerRepository;
import by.itacademy.justshooter.shoppingmall.service.api.AdminService;
import by.itacademy.justshooter.shoppingmall.service.dtos.AddressDto;
import by.itacademy.justshooter.shoppingmall.service.dtos.CategoryDto;
import by.itacademy.justshooter.shoppingmall.service.dtos.LocationDto;
import by.itacademy.justshooter.shoppingmall.service.dtos.ShopOwnerDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

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
        System.out.println("dto : " + shopOwner);
        System.out.println("dto address : " + shopOwner.getAddress());
        System.out.println("dto address id : " + shopOwner.getAddress().getId());
        System.out.println("     ----- ----- -----     ");
        ShopOwner shopOwnerEntity = modelMapper.map(shopOwner, ShopOwner.class);
        shopOwnerEntity.setAddress(addressRepository.findById(shopOwner.getAddress().getId()).orElseThrow());
        System.out.println(shopOwnerEntity);
        System.out.println(shopOwnerEntity.getAddress());
        System.out.println(shopOwnerEntity.getAddress().getId());

//        shopOwnerRepository.save(modelMapper.map(shopOwner, ShopOwner.class));
    }

    @Override
    public void saveAddress(AddressDto address) {
        addressRepository.save(modelMapper.map(address, Address.class));
    }

}

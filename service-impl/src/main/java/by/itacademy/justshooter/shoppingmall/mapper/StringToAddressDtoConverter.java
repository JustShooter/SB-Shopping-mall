package by.itacademy.justshooter.shoppingmall.mapper;

import by.itacademy.justshooter.shoppingmall.entity.enums.StreetType;
import by.itacademy.justshooter.shoppingmall.service.dtos.AddressDto;
import org.springframework.core.convert.converter.Converter;


public class StringToAddressDtoConverter implements Converter<String, AddressDto> {

    @Override
    public AddressDto convert(String source) {
        String[] parts = source.split(",");
        Integer id = Integer.parseInt(parts[0]);
        String city = parts[1];
        String street = parts[2];
        StreetType streetType = StreetType.valueOf(parts[3]);
        String buildingNumber = parts[4];
        String officeNumber = parts[5];
        return new AddressDto(id, city, street, streetType, buildingNumber, officeNumber);
    }
}


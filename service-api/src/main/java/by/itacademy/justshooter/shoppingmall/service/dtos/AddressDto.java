package by.itacademy.justshooter.shoppingmall.service.dtos;

import by.itacademy.justshooter.shoppingmall.entity.enums.StreetType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressDto implements Serializable {

    private Integer id;

    @Length(min = 3, max = 50, message = "{validation.address.city.length}")
    @NotBlank(message = "{validation.address.city.not_blank}")
    @Pattern(regexp = "^[А-ЯЁA-Z][0-9а-яА-ЯёЁa-zA-Z -]+?[0-9а-яА-ЯёЁa-zA-Z]+$", message = "{validation.address.city.pattern}")
    private String city;

    @Length(min = 3, max = 50, message = "{validation.address.street.length}")
    @NotBlank(message = "{validation.address.street.not_blank}")
    @Pattern(regexp = "^[А-ЯЁA-Z][0-9а-яА-ЯёЁa-zA-Z -]+?[0-9а-яА-ЯёЁa-zA-Z]+$", message = "{validation.address.street.pattern}")
    private String street;

    private StreetType streetType;

    @Length(min = 1, max = 10, message = "{validation.address.building_number.length}")
    @NotBlank(message = "{validation.address.building_number.not_blank}")
    @Pattern(regexp = "^[0-9]+?[А-Яа-яЁёA-Za-z0-9/-]*?[А-Яа-яЁёA-Za-z0-9]*?$", message = "{validation.address.building_number.pattern}")
    private String buildingNumber;

    @Length(min = 1, max = 10, message = "{validation.address.office_number.length}")
    @NotBlank(message = "{validation.address.office_number.not_blank}")
    @Pattern(regexp = "^[0-9]+?[А-Яа-яЁёA-Za-z0-9/-]*?[А-Яа-яЁёA-Za-z0-9]*?$", message = "{validation.address.office_number.pattern}")
    private String officeNumber;


    @Override
    public String toString() {
        return id + "," + city + "," + street + "," + streetType + "," + buildingNumber + "," + officeNumber;
    }

    public String toTableView() {
        return city + ", " +
                streetType.getFullName() + " " +
                street + ", " +
                buildingNumber + "-" +
                officeNumber;
    }
}

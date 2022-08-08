package by.itacademy.justshooter.shoppingmall.service.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LocationDto implements Serializable {
    private Integer id;

    @Length(min = 3, max = 10, message = "{validation.location.shop_number.length}")
    @Pattern(regexp = "^[0-9]+(?:\\s+[А-Яа-яЁёA-Za-z0-9]+)*$", message = "{validation.location.shop_number.pattern}")
    private String shopNumber;

    @Min(value = 1, message = "{validation.location.floor.min}")
    @Max(value = 10, message = "{validation.location.floor.max}")
    @Digits(integer = 2, fraction = 0, message = "{validation.location.floor.digits}")
    @Positive(message = "{validation.location.floor.positive}")
    @NotNull(message = "{validation.location.floor.not_null}")
    private Integer floor;

    @Length(max = 250, message = "{validation.location.description.length}")
    private String description;
}

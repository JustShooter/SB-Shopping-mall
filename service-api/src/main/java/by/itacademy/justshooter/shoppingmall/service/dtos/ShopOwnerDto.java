package by.itacademy.justshooter.shoppingmall.service.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShopOwnerDto implements Serializable {
    private Integer id;

    @Length(min = 3, max = 50, message = "{validation.shop_owner.owner_name.length}")
    @NotBlank(message = "{validation.shop_owner.owner_name.not_blank}")
    @Pattern(regexp = "^[А-ЯЁA-Z][0-9а-яА-ЯёЁa-zA-Z -]+?[0-9а-яА-ЯёЁa-zA-Z]+$", message = "{validation.shop_owner.owner_name.pattern}")
    private String ownerName;

    @NotNull(message = "{validation.shop_owner.address.not_null}")
    private AddressDto address;
}

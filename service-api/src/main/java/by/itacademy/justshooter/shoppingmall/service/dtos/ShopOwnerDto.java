package by.itacademy.justshooter.shoppingmall.service.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShopOwnerDto implements Serializable {
    private Integer id;
    private String ownerName;
    private AddressDto address;
}

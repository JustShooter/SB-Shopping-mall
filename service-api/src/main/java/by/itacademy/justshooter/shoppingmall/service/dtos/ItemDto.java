package by.itacademy.justshooter.shoppingmall.service.dtos;

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
public class ItemDto implements Serializable {
    private Integer id;

    @NotBlank(message = "{validation.item.name.not_blank}")
    @Length(min = 3, max = 50, message = "{validation.item.name.length}")
    // TODO Make RegEx refactoring
    @Pattern(regexp = "^[А-ЯЁA-Z][0-9а-яА-ЯёЁa-zA-Z -]+?[0-9а-яА-ЯёЁa-zA-Z]+$", message = "{validation.item.name.pattern}")
    private String name;

    @NotBlank(message = "{validation.item.article.not_blank}")
    @Length(min = 3, max = 50, message = "{validation.item.article.length}")
    // TODO Make RegEx refactoring
    @Pattern(regexp = "^[0-9а-яА-ЯёЁa-zA-Z]+?[0-9а-яА-ЯёЁa-zA-Z \\-\\/]+$", message = "{validation.item.article.pattern}")
    private String article;

    @NotBlank(message = "{validation.item.barcode.not_blank}")
    @Length(min = 3, max = 50, message = "{validation.item.barcode.length}")
    @Pattern(regexp = "^\\d{13}$", message = "{validation.item.barcode.pattern}")
    private String barcode;
}

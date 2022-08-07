package by.itacademy.justshooter.shoppingmall.service.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto implements Serializable {

    private Integer id;

    @Length(min = 3, max = 50, message = "{validation.category.name.length}")
    @NotBlank(message = "{validation.category.name.not_blank}")
    @Pattern(regexp = "^[А-ЯЁA-Z][A-Za-zА-Яа-яЁё]*(?:\\s[А-Яа-яёЁA-Za-z0-9]+)*$", message = "{validation.category.name.pattern}")
    private String name;

    @Length(min = 3, max = 250, message = "{validation.category.description.length}")
    @NotBlank(message = "{validation.category.description.not_blank}")
    @Pattern(regexp = "^[А-ЯЁA-Z][A-Za-zА-Яа-яЁё]*(?:\\s[А-Яа-яёЁA-Za-z0-9,]+)*[.]$", message = "{validation.category.description.pattern}")
    private String description;
}

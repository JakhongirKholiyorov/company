package uz.pdp.springmodule2task1.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CompanyDto {
    @NotNull(message = "corpName bo'sh bo'lmasligi kerak")
    private String corpName;
    @NotNull(message = "directorName bo'sh bo'lmasligi kerak")
    private String directorName;
    @NotNull(message = "addressId bo'sh bo'lmasligi kerak")
    private Integer addressId;
}

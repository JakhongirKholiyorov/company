package uz.pdp.springmodule2task1.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DepartmentDto {
    @NotNull(message = "department name bo'sh bo'lmasligi kerak")
    private String name;
    @NotNull(message = "companyId bo'sh bo'lmasligi kerak")
    private Integer companyId;
}

package uz.pdp.springmodule2task1.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class AddressDto {
    @NotNull(message = "street bosh bolmasligi kerak")
    private String street;
    @NotNull(message = "homeNumber bosh bolmasligi kerak")
    private Integer homeNumber;
}

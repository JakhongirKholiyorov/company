package uz.pdp.springmodule2task1.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class WorkerDto {
    @NotNull(message = "worker name bo'sh bo'lmasligi kerak")
    private String name;
    @NotNull(message = "phoneNumber bo'sh bo'lmasligi kerak")
    private String phoneNumber;
    @NotNull(message = "addressId bo'sh bo'lmasligi kerak")
    private Integer addressId;
    @NotNull(message = "departmentId bo'sh bo'lmasligi kerak")
    private Integer departmentId;
}

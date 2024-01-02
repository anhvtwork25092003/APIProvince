package com.example.demo.dto.request;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DistrictRequest {
    @NotBlank(message = "Tên Quận huyện không được để trống")
    @Size(max = 50, message = "Tên quận huyện không được vượt quá 50 kí tự")
    private String districtName;

    @NotBlank(message = "Không để trống thông tin!")
    private long provinceId;
}

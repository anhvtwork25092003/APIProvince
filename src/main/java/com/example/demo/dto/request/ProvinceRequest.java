package com.example.demo.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class ProvinceRequest {
    @NotBlank(message = "Tên tỉnh không được để trống")
    @Size(max = 50, message = "Tên tỉnh không được vượt quá 50 kí tự")
    @Pattern(regexp = "^[\\w\\s\\p{Punct}]+$", message = "Tên tỉnh chỉ chấp nhận kí tự chữ cái, số, khoảng trắng và dấu câu")
    private String provinceName;
}

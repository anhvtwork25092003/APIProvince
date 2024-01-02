package com.example.demo.dto.request;
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
public class WardRequest {
    @NotBlank(message = "Tên phường/xã không được để trống")
    @Size(max = 50, message = "Tên phường/xã không được vượt quá 50 kí tự")
    private String wardName;

    @NotBlank(message = "không được để trống")
    private long idDistrict;
}

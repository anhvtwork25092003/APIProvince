package com.example.demo.dto.response;

import com.example.demo.entities.Province;
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
public class DistrictResponse {

    private long idDistrict;

    private String districtName;

    private long provinceId;
    private String provinceName;
}

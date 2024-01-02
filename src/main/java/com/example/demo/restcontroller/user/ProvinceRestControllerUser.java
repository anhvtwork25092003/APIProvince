package com.example.demo.restcontroller.user;

import com.example.demo.dto.response.ProvinceResponse;
import com.example.demo.service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user/api/province")
public class ProvinceRestControllerUser {

    @Autowired
    ProvinceService provinceService;

    @GetMapping("/get-list-province")
    public ResponseEntity<List<ProvinceResponse>> getListProvince() {
        try {
            List<ProvinceResponse> provinces = provinceService.allOfProvinceResponses();
            if (provinces.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(provinces, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

package com.example.demo.restcontroller.user;

import com.example.demo.dto.response.DistrictResponse;
import com.example.demo.dto.response.WardResponse;
import com.example.demo.service.DistrictService;
import com.example.demo.service.WardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user/api/ward")
public class WardUserRestController {

    @Autowired
    WardService wardService;


    @GetMapping("/get-list-district/{idprovince}")
    public ResponseEntity<List<WardResponse>> getListProvince2(@PathVariable("idprovince") long id) {
        try {
            List<WardResponse> wardResponseList = wardService.getListWardsByDistrictID(id);
            return new ResponseEntity<>(wardResponseList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

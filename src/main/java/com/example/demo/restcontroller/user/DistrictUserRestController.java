package com.example.demo.restcontroller.user;

import com.example.demo.dto.response.DistrictResponse;
import com.example.demo.service.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user/api/district")
public class DistrictUserRestController {

    @Autowired
    DistrictService districtServicel;


    @GetMapping("/get-list-district/{idprovince}")
    public ResponseEntity<List<DistrictResponse>> getListProvince2(@PathVariable("idprovince") long id) {
        try {
            List<DistrictResponse> districtResponses = districtServicel.getListDistrictByProvinceName(id);
            if (districtResponses.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(districtResponses, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

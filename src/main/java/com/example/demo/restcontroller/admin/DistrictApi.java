package com.example.demo.restcontroller.admin;

import com.example.demo.dto.request.DistrictRequest;
import com.example.demo.dto.response.DistrictResponse;
import com.example.demo.service.CommonService;
import com.example.demo.service.DistrictService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/admin/api/district")
public class DistrictApi {
    @Autowired
    CommonService<DistrictResponse, DistrictRequest> districtCommonService;

    @Autowired
    DistrictService districtServicel;

    @GetMapping("/get-list-district")
    public ResponseEntity<Page<DistrictResponse>> getListProvince(@RequestParam(defaultValue = "1") int page) {
        try {
            int pageSize = 5; // Đặt kích thước trang mặc định
            Pageable pageable = PageRequest.of(page - 1, pageSize); // Số trang bắt đầu từ 0
            Page<DistrictResponse> districtResponses = districtCommonService.getAll(pageable);
            return new ResponseEntity<>(districtResponses, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get-district/{id}")
    public ResponseEntity<DistrictResponse> getDistrict(@PathVariable("id") long id) {
        try {
            DistrictResponse districtResponses = this.districtCommonService.getById(id);
            return new ResponseEntity<>(districtResponses, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/create-district")
    public ResponseEntity<DistrictResponse> createProvince(@RequestBody @Valid DistrictRequest districtRequest) {
        try {
            // Thực hiện xử lý khi dữ liệu hợp lệ và tạo tỉnh thành công
            DistrictResponse districtResponse = districtCommonService.create(districtRequest);
            return new ResponseEntity<>(districtResponse, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update-district/{id}")
    public ResponseEntity<DistrictResponse> updateProvince(@RequestBody @Valid DistrictRequest districtRequest,
                                                           @PathVariable("id") long id
    ) {
        try {
            // Thực hiện xử lý khi dữ liệu hợp lệ và tạo tỉnh thành công
            DistrictResponse districtRespons = districtCommonService.update(id, districtRequest);
            return new ResponseEntity<>(districtRespons, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete-district/{id}")
    public ResponseEntity<Map<String, Object>> delete(
            @PathVariable("id") long id
    ) {
        try {
            // Thực hiện xử lý khi dữ liệu hợp lệ và tạo tỉnh thành công
            this.districtCommonService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

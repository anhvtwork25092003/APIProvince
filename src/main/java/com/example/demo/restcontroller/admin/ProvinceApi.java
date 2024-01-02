package com.example.demo.restcontroller.admin;

import com.example.demo.dto.request.ProvinceRequest;
import com.example.demo.dto.response.ProvinceResponse;
import com.example.demo.service.CommonService;
import com.example.demo.service.ProvinceService;
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

@RestController
@RequestMapping("/admin/api/province")
public class ProvinceApi {

    @Autowired
    CommonService<ProvinceResponse, ProvinceRequest> provinceCommonService;

    @Autowired
    ProvinceService provinceService;

    @GetMapping("/get-list-province")
    public ResponseEntity<Page<ProvinceResponse>> getListProvince(@RequestParam(defaultValue = "1") int page) {
        try {
            int pageSize = 5; // Đặt kích thước trang mặc định
            Pageable pageable = PageRequest.of(page - 1, pageSize); // Số trang bắt đầu từ 0

            Page<ProvinceResponse> provinces = provinceCommonService.getAll(pageable);
            if (provinces.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(provinces, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get-province/{id}")
    public ResponseEntity<ProvinceResponse> getProvince(@PathVariable("id") long id) {
        try {
            ProvinceResponse provinceResponse = this.provinceCommonService.getById(id);
            return new ResponseEntity<>(provinceResponse, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/create-province")
    public ResponseEntity<ProvinceResponse> createProvince(@RequestBody @Valid ProvinceRequest provinceRequest) {
        try {
            ProvinceResponse createdProvince = provinceCommonService.create(provinceRequest);
            return new ResponseEntity<>(createdProvince, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update-province/{id}")
    public ResponseEntity<ProvinceResponse> updateProvince(@RequestBody @Valid ProvinceRequest provinceRequest,
                                                           @PathVariable("id") long id
    ) {
        try {
            // Thực hiện xử lý khi dữ liệu hợp lệ và tạo tỉnh thành công
            ProvinceResponse updatedProvince = provinceCommonService.update(id, provinceRequest);
            return new ResponseEntity<>(updatedProvince, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete-province/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") long id) {
        try {
            // Thực hiện xử lý khi dữ liệu hợp lệ và tạo tỉnh thành công
            this.provinceCommonService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

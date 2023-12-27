package com.example.demo.restcontroller;

import com.example.demo.dto.request.ProvinceRequest;
import com.example.demo.dto.response.ProvinceResponse;
import com.example.demo.exception.CustomNotFoundException;
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

import java.util.HashMap;
import java.util.Map;

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
            if (provinceResponse == null) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(provinceResponse, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/create-province")
    public ResponseEntity<Map<String, Object>> createProvince(@RequestBody @Valid ProvinceRequest provinceRequest) {
        Map<String, Object> result = new HashMap<>();
        try {
            // Thực hiện xử lý khi dữ liệu hợp lệ và tạo tỉnh thành công
            ProvinceResponse createdProvince = provinceCommonService.create(provinceRequest);
            if (createdProvince == null) {
                result.put("success", false);
                result.put("message", "Tên tỉnh đã tồn tại!");
                return new ResponseEntity<>(result, HttpStatus.OK);
            }
            result.put("success", true);
            result.put("message", "Tạo tỉnh thành công");
            result.put("data", createdProvince);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            result.put("success", false);
            result.put("message", "Lỗi khi tạo tỉnh thành");
            result.put("data", null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result);
        }
    }

    @PutMapping("/update-province/{id}")
    public ResponseEntity<Map<String, Object>> updateProvince(@RequestBody @Valid ProvinceRequest provinceRequest,
                                                              @PathVariable("id") long id
    ) {
        Map<String, Object> result = new HashMap<>();
        try {
            // Thực hiện xử lý khi dữ liệu hợp lệ và tạo tỉnh thành công
            ProvinceResponse updatedProvince = provinceCommonService.update(id, provinceRequest);
            if (updatedProvince == null) {
                result.put("success", false);
                result.put("message", "Tên tỉnh đã tồn tại!");
                return new ResponseEntity<>(result, HttpStatus.OK);
            }
            result.put("success", true);
            result.put("message", "Cập nhật tỉnh thành công");
            result.put("data", updatedProvince);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (CustomNotFoundException e) {
            result.put("success", false);
            result.put("message", e.getMessage());
            result.put("data", null);
            return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            result.put("success", false);
            result.put("message", "Lỗi khi cập nhật tỉnh thành");
            result.put("data", null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result);
        }
    }

    @DeleteMapping("/delete-province/{id}")
    public ResponseEntity<Map<String, Object>> delete(
            @PathVariable("id") long id
    ) {
        Map<String, Object> result = new HashMap<>();
        try {
            // Thực hiện xử lý khi dữ liệu hợp lệ và tạo tỉnh thành công
            this.provinceCommonService.delete(id);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (CustomNotFoundException e) {
            result.put("success", false);
            result.put("message", e.getMessage());
            result.put("data", null);
            return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            result.put("success", false);
            result.put("message", "Lỗi khi xóa tỉnh thành");
            result.put("data", null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result);
        }
    }
}

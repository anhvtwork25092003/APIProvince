package com.example.demo.restcontroller.admin;

import com.example.demo.dto.request.DistrictRequest;
import com.example.demo.dto.response.DistrictResponse;
import com.example.demo.exception.CustomNotFoundException;
import com.example.demo.service.CommonService;
import com.example.demo.service.DistrictService;
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
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/api/district")
public class DistrictApi {
    @Autowired
    CommonService<DistrictResponse, DistrictRequest> districtCommonService;

    @Autowired
    ProvinceService provinceService;

    @Autowired
    DistrictService districtServicel;

    @GetMapping("/get-list-district")
    public ResponseEntity<Page<DistrictResponse>> getListProvince(@RequestParam(defaultValue = "1") int page) {
        try {
            int pageSize = 5; // Đặt kích thước trang mặc định
            Pageable pageable = PageRequest.of(page - 1, pageSize); // Số trang bắt đầu từ 0

            Page<DistrictResponse> districtResponses = districtCommonService.getAll(pageable);

            if (districtResponses.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(districtResponses, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get-district/{id}")
    public ResponseEntity<Map<String, Object>> getDistrict(@PathVariable("id") long id) {
        Map<String, Object> result = new HashMap<>();
        try {
            DistrictResponse districtResponses = this.districtCommonService.getById(id);
            if (districtResponses == null) {
                result.put("success", false);
                result.put("message", "TKhông tìm thấy quận huyện có id la: " + id);
                return new ResponseEntity<>(result, HttpStatus.OK);
            }
            result.put("success", true);
            result.put("data", districtResponses);
            return new ResponseEntity<>(result, HttpStatus.OK);

        } catch (CustomNotFoundException e) {
            result.put("success", false);
            result.put("message", e.getMessage());
            result.put("data", null);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get-district-by-provinceId/{idProvince}")
    public ResponseEntity<Map<String, Object>> getDistrictByProvinceId(@PathVariable("idProvince") long id) {
        Map<String, Object> result = new HashMap<>();
        try {

            List<DistrictResponse> districtResponses = this.districtServicel.getListDistrictByProvinceName(id);
            if (districtResponses == null) {
                result.put("success", false);
                result.put("message", "Không co thông tin của tỉnh có id: " + id);
                return new ResponseEntity<>(result, HttpStatus.OK);
            }
            result.put("success", true);
            result.put("data", districtResponses);
            return new ResponseEntity<>(result, HttpStatus.OK);

        } catch (CustomNotFoundException e) {
            result.put("success", false);
            result.put("message", e.getMessage());
            result.put("data", null);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/create-district")
    public ResponseEntity<Map<String, Object>> createProvince(@RequestBody @Valid DistrictRequest districtRequest) {
        Map<String, Object> result = new HashMap<>();
        try {
            // Thực hiện xử lý khi dữ liệu hợp lệ và tạo tỉnh thành công
            DistrictResponse districtResponse = districtCommonService.create(districtRequest);
            if (districtResponse == null) {
                result.put("success", false);
                result.put("message", "Lỗi khi tạo quận huyện");
                return new ResponseEntity<>(result, HttpStatus.OK);
            }
            result.put("success", true);
            result.put("message", "Tạo quận huyện thành công");
            result.put("data", districtResponse);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            result.put("success", false);
            result.put("message", "Lỗi khi tạo quận huyện");
            result.put("data", null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result);
        }
    }

    @PutMapping("/update-district/{id}")
    public ResponseEntity<Map<String, Object>> updateProvince(@RequestBody @Valid DistrictRequest districtRequest,
                                                              @PathVariable("id") long id
    ) {
        Map<String, Object> result = new HashMap<>();
        try {
            // Thực hiện xử lý khi dữ liệu hợp lệ và tạo tỉnh thành công
            DistrictResponse districtRespons = districtCommonService.update(id, districtRequest);
            if (districtRespons == null) {
                result.put("success", false);
                result.put("message", "Lỗi khi cập nhật quận huyện!");
                return new ResponseEntity<>(result, HttpStatus.OK);
            }
            result.put("success", true);
            result.put("message", "Cập nhật quận huyện thành công");
            result.put("data", districtRespons);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (CustomNotFoundException e) {
            result.put("success", false);
            result.put("message", e.getMessage());
            result.put("data", null);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            result.put("success", false);
            result.put("message", "Lỗi khi cập nhật quận huyện!");
            result.put("data", null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result);
        }
    }

    @DeleteMapping("/delete-district/{id}")
    public ResponseEntity<Map<String, Object>> delete(
            @PathVariable("id") long id
    ) {
        Map<String, Object> result = new HashMap<>();
        try {
            // Thực hiện xử lý khi dữ liệu hợp lệ và tạo tỉnh thành công
            this.districtCommonService.delete(id);
            result.put("success", true);
            result.put("message", "Xóa quận huyện thành công");
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (CustomNotFoundException e) {
            result.put("success", false);
            result.put("message", e.getMessage());
            result.put("data", null);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            result.put("success", false);
            result.put("message", "Lỗi khi xóa quận huyện");
            result.put("data", null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result);
        }
    }
}

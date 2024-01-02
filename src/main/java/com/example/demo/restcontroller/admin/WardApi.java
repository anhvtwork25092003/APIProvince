package com.example.demo.restcontroller.admin;

import com.example.demo.dto.request.WardRequest;
import com.example.demo.dto.response.WardResponse;
import com.example.demo.exception.CustomNotFoundException;
import com.example.demo.service.CommonService;
import com.example.demo.service.WardService;
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
@RequestMapping("/admin/api/ward")
public class WardApi {


    @Autowired
    CommonService<WardResponse, WardRequest> wardCommonService;

    @Autowired
    WardService wardService;

    @GetMapping("/get-list-ward")
    public ResponseEntity<Map<String, Object>> getListWard(@RequestParam(defaultValue = "1") int page) {
        Map<String, Object> result = new HashMap<>();
        try {
            int pageSize = 5; // Đặt kích thước trang mặc định
            Pageable pageable = PageRequest.of(page - 1, pageSize); // Số trang bắt đầu từ 0

            Page<WardResponse> wardResponses = this.wardCommonService.getAll(pageable);
            result.put("success", true);
            result.put("message", "danh sach tat ca phuong/xa");
            result.put("data", wardResponses);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            result.put("success", false);
            result.put("message", "Lỗi khi lấy danh sách phuong/xa" + e);
            return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get-ward/{idward}")
    public ResponseEntity<Map<String, Object>> getListWard(@PathVariable("idward") long idward) {
        Map<String, Object> result = new HashMap<>();
        try {
            WardResponse wardResponses = this.wardCommonService.getById(idward);
            result.put("success", true);
            result.put("message", "Ket qua ");
            result.put("data", wardResponses);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            result.put("success", false);
            result.put("message", "Lỗi khi tìm phường xã" + e);
            return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/create-ward")
    public ResponseEntity<Map<String, Object>> createWard(@RequestBody @Valid WardRequest wardRequest) {
        Map<String, Object> result = new HashMap<>();
        try {
            // Thực hiện xử lý khi dữ liệu hợp lệ và tạo tỉnh thành công
            WardResponse wardResponses = this.wardCommonService.create(wardRequest);
            result.put("success", true);
            result.put("message", "Tạo phường xã thành công");
            result.put("data", wardResponses);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            result.put("success", false);
            result.put("message", "Lỗi khi tạo phường xã");
            result.put("data", null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result);
        }
    }

    @PutMapping("/create-ward/{idward}")
    public ResponseEntity<Map<String, Object>> createWard(@RequestBody @Valid WardRequest wardRequest,
                                                          @PathVariable("idward") long idward) {
        Map<String, Object> result = new HashMap<>();
        try {
            // Thực hiện xử lý khi dữ liệu hợp lệ và tạo tỉnh thành công
            WardResponse wardResponses = this.wardCommonService.update(idward, wardRequest);
            result.put("success", true);
            result.put("message", "Cập nhật  phường xã thành công");
            result.put("data", wardResponses);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            result.put("success", false);
            result.put("message", "Lỗi khi Cập nhật phường xã");
            result.put("data", null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result);
        }
    }
    @DeleteMapping("/delete-ward/{id}")
    public ResponseEntity<Map<String, Object>> delete(
            @PathVariable("id") long id
    ) {
        Map<String, Object> result = new HashMap<>();
        try {
            // Thực hiện xử lý khi dữ liệu hợp lệ và tạo tỉnh thành công
            this.wardCommonService.delete(id);
            result.put("success", true);
            result.put("message", "Xóa phường xã thành công");
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (CustomNotFoundException e) {
            result.put("success", false);
            result.put("message", e.getMessage());
            result.put("data", null);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            result.put("success", false);
            result.put("message", "Lỗi khi xóa phường xã");
            result.put("data", null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result);
        }
    }

}

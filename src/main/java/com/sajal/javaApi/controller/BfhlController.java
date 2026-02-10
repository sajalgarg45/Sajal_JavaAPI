package com.sajal.javaApi.controller;

import com.sajal.javaApi.dto.ApiResponse;
import com.sajal.javaApi.dto.BfhlRequest;
import com.sajal.javaApi.service.BfhlService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class BfhlController {

    private static final String EMAIL = "your_chitkara_email@chitkara.edu.in";

    private final BfhlService bfhlService;

    public BfhlController(BfhlService bfhlService) {
        this.bfhlService = bfhlService;
    }

    @GetMapping("/health")
    public ResponseEntity<ApiResponse<Void>> health() {
        return ResponseEntity.ok(
                new ApiResponse<>(true, EMAIL, null)
        );
    }

    @PostMapping("/bfhl")
    public ResponseEntity<ApiResponse<?>> bfhl(@RequestBody BfhlRequest request) {
        Object result = bfhlService.process(request);
        return ResponseEntity.ok(
                new ApiResponse<>(true, EMAIL, result)
        );
    }
}

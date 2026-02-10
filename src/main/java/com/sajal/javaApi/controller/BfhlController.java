package com.sajal.javaApi.controller;

import com.sajal.javaApi.dto.ApiResponse;
import com.sajal.javaApi.dto.BfhlRequest;
import com.sajal.javaApi.service.BfhlService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class BfhlController {

    @Value("${official.email}")
    private String email;

    private final BfhlService bfhlService;

    public BfhlController(BfhlService bfhlService) {
        this.bfhlService = bfhlService;
    }

    @GetMapping("/health")
    public ResponseEntity<ApiResponse<Void>> health() {
        return ResponseEntity.ok(
                new ApiResponse<>(true, email, null)
        );
    }

    @PostMapping("/bfhl")
    public ResponseEntity<ApiResponse<?>> bfhl(@RequestBody BfhlRequest request) {
        Object result = bfhlService.process(request);
        return ResponseEntity.ok(
                new ApiResponse<>(true, email, result)
        );
    }
}

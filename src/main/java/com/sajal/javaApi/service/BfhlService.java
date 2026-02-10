package com.sajal.javaApi.service;

import com.sajal.javaApi.dto.BfhlRequest;
import com.sajal.javaApi.exception.InvalidRequestException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BfhlService {

    private final AIService aiService;

    public BfhlService(AIService aiService) {
        this.aiService = aiService;
    }

    public Object process(BfhlRequest request) {

        int count =
                (request.fibonacci != null ? 1 : 0) +
                        (request.prime != null ? 1 : 0) +
                        (request.lcm != null ? 1 : 0) +
                        (request.hcf != null ? 1 : 0) +
                        (request.AI != null ? 1 : 0);

        if (count != 1) {
            throw new InvalidRequestException("Exactly one key must be provided");
        }

        if (request.fibonacci != null) {
            return fibonacci(request.fibonacci);
        }
        if (request.prime != null) {
            return request.prime.stream().filter(this::isPrime).collect(Collectors.toList());
        }
        if (request.lcm != null) {
            return request.lcm.stream().reduce(this::lcm).orElse(0);
        }
        if (request.hcf != null) {
            return request.hcf.stream().reduce(this::gcd).orElse(0);
        }
        return aiService.ask(request.AI);
    }

    private List<Integer> fibonacci(int n) {
        if (n < 0) throw new InvalidRequestException("Fibonacci must be >= 0");

        List<Integer> result = new ArrayList<>();
        int a = 0, b = 1;

        for (int i = 0; i < n; i++) {
            result.add(a);
            int c = a + b;
            a = b;
            b = c;
        }
        return result;
    }

    private boolean isPrime(int n) {
        if (n <= 1) return false;
        for (int i = 2; i * i <= n; i++)
            if (n % i == 0) return false;
        return true;
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    private int lcm(int a, int b) {
        return Math.abs(a * b) / gcd(a, b);
    }
}

package com.sajal.javaApi.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class AIService {

    @Value("${gemini.api.key}")
    private String apiKey;

    private final WebClient webClient = WebClient.create();

    public String ask(String question) {

        String requestBody = """
                {
                  "contents": [
                    {
                      "parts": [
                        { "text": "Answer in one word only: %s" }
                      ]
                    }
                  ]
                }
                """.formatted(question);

        String response = webClient.post()
                .uri("https://generativelanguage.googleapis.com/v1beta/models/gemini-pro:generateContent?key=" + apiKey)
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(String.class)
                .block();

        return extractSingleWord(response);
    }

    private String extractSingleWord(String raw) {
        if (raw == null) return "Unknown";
        return raw.replaceAll("[^A-Za-z]", "").trim();
    }
}

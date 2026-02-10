package com.sajal.javaApi.service;

import com.sajal.javaApi.exception.InvalidRequestException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Map;

@Service
public class AIService {

    @Value("${gemini.api.key}")
    private String geminiKey;

    private final WebClient webClient = WebClient.create(
            "https://generativelanguage.googleapis.com"
    );

    public String ask(String question) {

        if (question == null || question.trim().isEmpty()) {
            throw new InvalidRequestException("AI question cannot be empty");
        }

        try {
            Map response = webClient.post()
                    .uri("/v1beta/models/gemini-2.5-flash:generateContent?key=" + geminiKey)
                    .contentType(MediaType.APPLICATION_JSON)
                    .bodyValue(Map.of(
                            "contents", List.of(
                                    Map.of(
                                            "parts", List.of(
                                                    Map.of(
                                                            "text",
                                                            "Answer in ONE WORD only. No punctuation. No explanation. Question: "
                                                                    + question
                                                    )
                                            )
                                    )
                            )
                    ))
                    .retrieve()
                    .bodyToMono(Map.class)
                    .block();

            return extractSingleWord(response);

        } catch (Exception e) {
            // Graceful failure — required by problem
            throw new RuntimeException("AI service unavailable");
        }
    }

    @SuppressWarnings("unchecked")
    private String extractSingleWord(Map response) {

        try {
            List<Map> candidates = (List<Map>) response.get("candidates");
            Map firstCandidate = candidates.get(0);

            Map content = (Map) firstCandidate.get("content");
            List<Map> parts = (List<Map>) content.get("parts");

            String text = (String) parts.get(0).get("text");

            // Ensure SINGLE WORD only
            return text.trim().split("\\s+")[0];

        } catch (Exception e) {
            throw new RuntimeException("AI service unavailable");
        }
    }
}

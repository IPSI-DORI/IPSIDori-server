package com.server.dori.domain.curriculum.presentation;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

import com.server.dori.domain.curriculum.exception.ApiCallException;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/ai")
public class AIController {

	private final RestClient restClient;

	@GetMapping
	public ResponseEntity<String> getAIRoot() {
		try {
			String response = restClient.get()
				.uri("/")
				.retrieve()
				.body(String.class);

			System.out.println(response);
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			throw new ApiCallException(e.getMessage());
		}
	}

	@GetMapping("/curriculum")
	public ResponseEntity<String> getAICurriculum(@RequestParam(name = "query") String query) {
		try {
			String response = restClient.get()
				.uri(uriBuilder -> uriBuilder
					.path("/curriculum")
					.queryParam("user_question", query)
					.build())
				.retrieve()
				.body(String.class);

			System.out.println(response);
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			throw new ApiCallException(e.getMessage());
		}
	}
}
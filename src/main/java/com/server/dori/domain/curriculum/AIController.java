package com.server.dori.domain.curriculum;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/ai")
public class AIController {

	private final RestClient restClient = RestClient.builder().baseUrl("${AIServer}").build();

	@GetMapping
	public ResponseEntity<String> getAIRoot() {
		try {
			String response = restClient.get()
				.uri(uriBuilder -> uriBuilder
					.path("/")
					.build())
				.retrieve()
				.body(String.class);

			System.out.println(response);
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			System.err.println("Error while calling FastAPI: " + e.getMessage());
			return ResponseEntity.status(500).body("Error while calling FastAPI: " + e.getMessage());
		}
	}

	@GetMapping("/curriculum")
	public ResponseEntity<String> getAICurriculum(@RequestParam String query) {
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
			System.err.println("Error while calling FastAPI: " + e.getMessage());
			return ResponseEntity.status(500).body("Error while calling FastAPI: " + e.getMessage());
		}
	}
}
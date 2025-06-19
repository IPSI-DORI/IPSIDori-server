package com.server.dori.domain.curriculum.presentation;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

import com.server.dori.domain.curriculum.entity.Curriculum;
import com.server.dori.domain.curriculum.exception.ApiCallException;
import com.server.dori.domain.curriculum.presentation.dto.request.AICurriculumRequest;
import com.server.dori.domain.curriculum.repository.CurriculumRepository;
import com.server.dori.domain.grade.entity.Grade;
import com.server.dori.domain.grade.repository.GradeRepository;
import com.server.dori.domain.member.entity.CustomUserDetails;
import com.server.dori.domain.member.entity.Member;
import com.server.dori.domain.member.entity.MemberInfo;
import com.server.dori.domain.member.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/ai")
public class AIController {

	private final RestClient restClient;
	private final MemberRepository memberRepository;
	private final CurriculumRepository curriculumRepository;
	private final GradeRepository gradeRepository;

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
	public ResponseEntity<String> getAICurriculum(@AuthenticationPrincipal CustomUserDetails userDetails, @RequestParam(name = "curriculumId") Long curriculumId) {
		try {

			MemberInfo memberInfo = memberRepository.getById(userDetails.getMemberId()).getMemberInfo();
			Curriculum curriculum = curriculumRepository.getById(curriculumId);
			Grade grade = gradeRepository.getById(curriculumId);

			AICurriculumRequest request = new AICurriculumRequest(memberInfo, curriculum, grade);
			String query = AICurriculumRequest.toQuery(request);

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
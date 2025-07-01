package com.server.dori.domain.curriculum.presentation.dto.response;

import java.time.LocalDateTime;

import com.server.dori.domain.curriculum.entity.Curriculum;
import com.server.dori.domain.curriculum.entity.type.Platform;
import com.server.dori.domain.member.entity.Member;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "커리큘럼 설문 응답 DTO")
public record CurriculumSurveyResponse(

	@Schema(description = "설문 작성자 Id", example = "1")
	Long creatorId,

	@Schema(description = "공부 과목", example = "수학")
	String subject,

	@Schema(description = "선택 과목", example = "기하")
	String elective,

	@Schema(description = "하루 공부 시간", example = "1~2시간")
	String studyTime,

	@Schema(description = "주간 공부일수", example = "월, 화")
	String studyDays,

	@Schema(description = "문제를 풀 때 가장 고민되는 점은 무엇인가요?", example = "조건이 많거나 복잡한 문제에서 헷갈려요.")
	String weakProblemQuestion,

	@Schema(description = "현재 학습 목표가 무엇인가요?", example = "탄탄한 기본기 다지기")
	String learningGoalQuestion,

	@Schema(description = "사용 플랫폼", example = "MEGA")
	Platform platform,

	@Schema(description = "설문 생성 시간", example = "2025-06-25T14:30:00")
	LocalDateTime createAt
) {
	public static CurriculumSurveyResponse of(Curriculum curriculum) {
		return new CurriculumSurveyResponse(
			curriculum.getCreator().getId(),
			curriculum.getSubject(),
			curriculum.getElective(),
			curriculum.getStudyTime(),
			curriculum.getStudyDays(),
			curriculum.getWeakProblemQuestion(),
			curriculum.getLearningGoalQuestion(),
			curriculum.getPlatform(),
			curriculum.getCreatedAt()
		);
	}
}

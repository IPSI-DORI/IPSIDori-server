package com.server.dori.domain.member.exception;

import com.server.dori.global.response.exception.CustomException;
import com.server.dori.global.response.exception.ErrorStatus;

public class MemberException extends CustomException {
	public MemberException(ErrorStatus errorStatus) {
		super(errorStatus);
	}

	public static MemberException duplicateEmail() {
		return new MemberException(MemberErrorStatus.DUPLICATE_EMAIL);
	}

	public static MemberException invalidLearningStyleScore() {
		return new MemberException(MemberErrorStatus.INVALID_LEARNING_STYLE_SCORE);
	}

	public static MemberException invalidSubjectCombination() {
		return new MemberException(MemberErrorStatus.INVALID_SUBJECT_COMBINATION);
	}

	public static MemberException invalidGrade() {
		return new MemberException(MemberErrorStatus.INVALID_GRADE);
	}

}

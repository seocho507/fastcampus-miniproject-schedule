package com.fastcampus.schedule.exception.constant;

import org.springframework.http.HttpStatus;

public enum ErrorCode {

	// TODO message
	INVALID_TOKEN(HttpStatus.UNAUTHORIZED, "Invalid Token"),
	INVALID_PASSWORD(HttpStatus.UNAUTHORIZED, "Invalid Password"),
	USER_NOT_FOUND(HttpStatus.NOT_FOUND, "User not founded"),
	SCHEDULE_NOT_FOUND(HttpStatus.NOT_FOUND, "Schedule not founded"),
	DUPLICATED_EMAIL(HttpStatus.CONFLICT, "Email is duplicated"),
	DATABASE_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "DB Error"),
	;

	private final HttpStatus status;
	private final String message;

	ErrorCode(HttpStatus status, String message) {
		this.status = status;
		this.message = message;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public String getMessage() {
		return message;
	}
}
package com.fastcampus.schedule.user.service;

import static com.fastcampus.schedule.exception.constant.ErrorCode.*;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.fastcampus.schedule.exception.ScheduleException;
import com.fastcampus.schedule.exception.constant.ErrorCode;
import com.fastcampus.schedule.user.domain.User;
import com.fastcampus.schedule.user.jwt.JwtUtils;
import com.fastcampus.schedule.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LoginService {

	private final UserRepository userRepository;
	private final BCryptPasswordEncoder encoder;

	@Value("${jwt.secret-key}")
	private String secretKey;

	@Value("${jwt.token.expired-time-ms}")
	private Long expiredTimeMs;

	public String login(String email, String password) {
		User user = userRepository.findByEmail(email).orElseThrow(() -> new ScheduleException(USER_NOT_FOUND,
																							  "존재하지 않는 이메일 입니다."));
		if (!encoder.matches(password, user.getPassword())) {
			throw new ScheduleException(ErrorCode.INVALID_PASSWORD, "비밀번호를 확인해주세요.");
		}

		return JwtUtils.generateAccessToken(email, secretKey, expiredTimeMs);

	}
}
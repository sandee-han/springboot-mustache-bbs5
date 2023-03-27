package com.mustache.bbs5.service;

import com.mustache.bbs5.domain.entity.User;
import com.mustache.bbs5.domain.dto.UserDto;
import com.mustache.bbs5.domain.dto.UserJoinRequest;
import com.mustache.bbs5.exception.ErrorCode;
import com.mustache.bbs5.exception.HospitalReviewAppException;
import com.mustache.bbs5.repository.UserRepository;
import com.mustache.bbs5.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;

    @Value("${jwt.secret}")
    private String secretKey;
    private long expireTimeMs = 1000 * 60 * 60;
    public UserDto join(UserJoinRequest request) {

        userRepository.findByUserName(request.getUserName())
                .ifPresent(user -> new RuntimeException("해당 UserName이 중복 됩니다."));

        User savedUser = userRepository.save(request.toEntity(encoder.encode(request.getPassword())));
        return UserDto.builder()
                .id(savedUser.getId())
                .userName(savedUser.getUserName())
                .email(savedUser.getEmailAddress())
                .build();
    }

    public String login(String userName, String password) {
        // userName있는지 여부 확인
        // 없으면 NOT_FOUND에러 발생
        User user = userRepository.findByUserName(userName)
                .orElseThrow(() -> new HospitalReviewAppException
                        (ErrorCode.NOT_FOUND, String.format("%s는 가입된 적이 없습니다.", userName)));

        // password일치 하는지 여부 확인
        if (!encoder.matches(password, user.getPassword())){
            throw new HospitalReviewAppException
                    (ErrorCode.INVALID_PASSWORD, String.format("userName 또는 password가 잘못 됐습니다."));
        }
        // 두가지 확인중 예외 안났으면 Token발행
        return JwtUtil.createJwt(userName, secretKey, expireTimeMs);
    }

    public User getUserByUserName(String userName) {
        return userRepository.findByUserName(userName)
                .orElseThrow(() -> new HospitalReviewAppException(ErrorCode.NOT_FOUND, ""));
    }

}

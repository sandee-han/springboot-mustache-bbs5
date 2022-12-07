package com.mustache.bbs5.controller;

import com.mustache.bbs5.domain.dto.*;
import com.mustache.bbs5.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/join")
    public Response<UserJoinResponse> join(@RequestBody UserJoinRequest dto) {
        UserDto userDto = userService.join(dto);
        return Response.success(new UserJoinResponse(userDto.getUserName(), userDto.getEmail()));
    }

    @PostMapping("/login")
    public Response<UserloginResponse> login(@RequestBody UserloginRequest dto) {
        String token = userService.login(dto.getUserName(), dto.getPassword());
        return Response.success(new UserloginResponse(token));
    }

}

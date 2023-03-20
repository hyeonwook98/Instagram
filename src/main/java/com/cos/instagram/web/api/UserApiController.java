package com.cos.instagram.web.api;

import com.cos.instagram.config.auth.PrincipalDetails;
import com.cos.instagram.domain.user.User;
import com.cos.instagram.service.UserService;
import com.cos.instagram.web.dto.CMRespDto;
import com.cos.instagram.web.dto.user.UserUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class UserApiController {

    private final UserService userService;

    @PutMapping("/api/user/{id}")
    public CMRespDto<?> update(@PathVariable int id, UserUpdateDto userUpdateDto,
                               @AuthenticationPrincipal PrincipalDetails principalDetails) {
        User userEntity = userService.회원수정(id, userUpdateDto.toEntity());
        principalDetails.setUser(userEntity);
        return new CMRespDto<>(1, "회원수정완료", userEntity);
    }
}

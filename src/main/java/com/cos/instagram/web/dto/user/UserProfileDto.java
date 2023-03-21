package com.cos.instagram.web.dto.user;

import com.cos.instagram.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserProfileDto {
    private boolean pageOwnerState;
    private int imageCount;
    private boolean subscribeState;
    private int subscribeCount;
    private User user;
}

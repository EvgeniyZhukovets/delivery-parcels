package com.auth.dto.response;

import com.auth.dto.common.UserDto;
import com.common.ResponseInfo;
import com.common.StandardResponse;
import lombok.*;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse extends StandardResponse {

    private String token;
    private UserDto user;

    @Builder
    public LoginResponse(ResponseInfo responseInfo, String token, UserDto user) {
        super(responseInfo);
        this.token = token;
        this.user = user;
    }
}

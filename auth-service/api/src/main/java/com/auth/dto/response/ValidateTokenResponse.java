package com.auth.dto.response;

import com.auth.enums.Role;
import com.common.ResponseInfo;
import com.common.StandardResponse;
import lombok.*;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class ValidateTokenResponse extends StandardResponse {

    private String username;
    private Role role;

    @Builder
    public ValidateTokenResponse(ResponseInfo responseInfo, String username, Role role) {
        super(responseInfo);
        this.username = username;
        this.role = role;
    }
}

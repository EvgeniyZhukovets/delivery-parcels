package com.gateway.dto.auth.response;

import com.common.ResponseInfo;

public record ValidateTokenResponse(String username, String role, ResponseInfo responseInfo) {
}

package com.gateway.dto.auth.response;

import com.common.ResponseInfo;
import com.gateway.dto.auth.common.UserDto;

public record LoginResponse(String token, UserDto user, ResponseInfo responseInfo) {

}

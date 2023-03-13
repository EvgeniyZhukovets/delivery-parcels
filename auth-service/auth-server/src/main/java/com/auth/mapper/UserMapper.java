package com.auth.mapper;

import com.auth.dto.common.CourierDto;
import com.auth.dto.common.UserDto;
import com.auth.entity.User;

import java.util.List;

public class UserMapper {

    public static UserDto mapUserEntityToUserDto(User entity) {
        return UserDto.builder()
                .id(entity.getId())
                .username(entity.getUsername())
                .role(entity.getRole())
                .build();
    }

    public static List<CourierDto> mapUserEntitiesToCourierDtos(List<User> entities) {
        return entities.stream()
                .map(entity -> CourierDto.builder()
                        .username(entity.getUsername())
                        .courierStatus(entity.getCourierStatus())
                        .build())
                .toList();
    }
}

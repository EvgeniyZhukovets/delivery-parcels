package com.auth

import com.auth.entity.User
import com.auth.mapper.UserMapper
import org.jeasy.random.EasyRandom
import spock.lang.Specification

class UserMapperTest extends Specification {

    def "map user entity to user dto"() {
        setup:
        def generator = new EasyRandom()
        def user = generator.nextObject(User.class)
        when:
        def userDto = UserMapper.mapUserEntityToUserDto(user)
        then:
        user.getId() == userDto.getId()
        user.getUsername() == userDto.getUsername()
        user.getRole() == userDto.getRole()
    }

    def "map user entities to courier dto list"() {
        setup:
        def generator = new EasyRandom()
        def users = generator.objects(User.class, 2).toList()
        when:
        def courierDtos = UserMapper.mapUserEntitiesToCourierDtos(users)
        then:
        for (int i = 0; i < users.size(); i++) {
            assert users[0].getUsername() == courierDtos[0].getUsername()
            assert users[0].getCourierStatus() == courierDtos[0].getCourierStatus()
        }
    }
}

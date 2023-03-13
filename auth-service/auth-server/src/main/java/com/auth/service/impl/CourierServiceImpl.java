package com.auth.service.impl;

import com.auth.dto.common.CourierDto;
import com.auth.dto.response.GetAllCouriersResponse;
import com.auth.entity.User;
import com.auth.enums.Role;
import com.auth.event.ChangeCourierStatusEvent;
import com.auth.exception.UserNotFoundException;
import com.auth.mapper.UserMapper;
import com.auth.repository.UserRepository;
import com.auth.service.CourierService;
import lombok.RequiredArgsConstructor;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CourierServiceImpl implements CourierService {

    private final UserRepository userRepository;

    @Override
    public GetAllCouriersResponse getAllCouriers() {
        List<User> couriers = userRepository.findAllByRole(Role.COURIER);
        List<CourierDto> courierDtos = UserMapper.mapUserEntitiesToCourierDtos(couriers);

        return new GetAllCouriersResponse(courierDtos);
    }

    @Override
    @Transactional
    @Retryable(value = ObjectOptimisticLockingFailureException.class, maxAttempts = 5)
    public void changeCourierStatus(ChangeCourierStatusEvent event) {
        Optional<User> userOptional = userRepository.findById(event.getCourierId());
        if(userOptional.isEmpty()){
            throw new UserNotFoundException();
        }
        User user = userOptional.get();
        user.setCourierStatus(event.getCourierStatus());
        userRepository.save(user);
    }
}

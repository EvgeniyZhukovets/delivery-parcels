package com.delivery.service.impl;

import com.delivery.dto.common.ParcelDto;
import com.delivery.dto.request.*;
import com.delivery.dto.response.GetParcelCoordinatesResponse;
import com.delivery.dto.response.GetParcelResponse;
import com.delivery.dto.response.GetParcelsByCourierResponse;
import com.delivery.enums.CourierStatus;
import com.delivery.enums.ParcelStatus;
import com.delivery.event.ChangeCourierStatusEvent;
import com.delivery.event.CreateOrderEvent;
import com.delivery.exception.IllegalParcelStatusChangeException;
import com.delivery.exception.InvalidParcelStateException;
import com.delivery.exception.InvalidParcelStatusStateException;
import com.delivery.exception.ParcelNotFoundException;
import com.delivery.mapper.ParcelMapper;
import com.delivery.entity.Parcel;
import com.delivery.repository.ParcelRepository;
import com.delivery.service.ParcelService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static com.delivery.enums.ParcelStatus.*;

@Service
@RequiredArgsConstructor
public class ParcelServiceImpl implements ParcelService {

    private final ParcelRepository repository;
    private final RabbitTemplate rabbitTemplate;

    @Override
    @Transactional
    @Retryable(value = ObjectOptimisticLockingFailureException.class, maxAttempts = 5)
    public void assignCourier(AssignToCourierRequest request) {
        Optional<Parcel> parcelOptional = repository.findById(request.getParcelId());
        if (parcelOptional.isEmpty()) {
            throw new ParcelNotFoundException();
        }
        Parcel parcel = parcelOptional.get();
        parcel.setCourierId(request.getCourierId());
        parcel.setStatus(ParcelStatus.ASSIGNED);

        repository.save(parcel);
        ChangeCourierStatusEvent event = new ChangeCourierStatusEvent(request.getCourierId(), CourierStatus.BUSY);
        rabbitTemplate.convertAndSend("changeCourierStatus", event);
    }

    @Override
    @Transactional
    @Retryable(value = ObjectOptimisticLockingFailureException.class, maxAttempts = 5)
    public void createParcel(CreateOrderEvent event) {
        Parcel parcel = Parcel.builder()
                .orderId(event.getOrderId())
                .status(CREATED)
                .startPoint(event.getStartPoint())
                .destination(event.getDestination())
                .userId(event.getUserId())
                .build();
        repository.save(parcel);
    }

    @Override
    @Transactional
    @Retryable(value = ObjectOptimisticLockingFailureException.class, maxAttempts = 5)
    public void cancelParcel(Long orderId) {
        Optional<Parcel> parcelOptional = repository.findByOrderId(orderId);
        changeParcelStatus(parcelOptional, ParcelStatus.CANCELLED);
    }

    @Override
    @Transactional
    @Retryable(value = ObjectOptimisticLockingFailureException.class, maxAttempts = 5)
    public void changeDestination(ChangeDestinationRequest request) {
        Optional<Parcel> parcelOptional = repository.findById(request.getParcelId());
        if (parcelOptional.isEmpty()) {
            throw new ParcelNotFoundException();
        }
        Parcel parcel = parcelOptional.get();
        if (parcel.getStatus() == ParcelStatus.DELIVERING) {
            throw new InvalidParcelStateException();
        }
        parcel.setDestination(request.getDestination());
        repository.save(parcel);
    }

    @Override
    public void changeParcelStatusForCouriers(ChangeParcelStatusRequest request) {
        if (request.getParcelStatus() == CANCELLED) {
            throw new IllegalParcelStatusChangeException();
        }
        Optional<Parcel> parcelOptional = repository.findById(request.getParcelId());
        changeParcelStatus(parcelOptional, request.getParcelStatus());
    }

    @Override
    public GetParcelResponse getParcel(GetParcelByIdRequest request) {
        Optional<Parcel> parcelOptional = repository.findById(request.getParcelId());
        if (parcelOptional.isEmpty()) {
            throw new ParcelNotFoundException();
        }
        Parcel parcel = parcelOptional.get();
        ParcelDto parcelDto = ParcelMapper.mapParcelEntityToParcelDto(parcel);
        return new GetParcelResponse(parcelDto);
    }

    @Override
    public GetParcelResponse getParcelByOrderId(GetParcelByOrderIdRequest request) {
        Optional<Parcel> parcelOptional = repository.findByOrderId(request.getOrderId());
        if (parcelOptional.isEmpty()) {
            throw new ParcelNotFoundException();
        }
        Parcel parcel = parcelOptional.get();
        ParcelDto parcelDto = ParcelMapper.mapParcelEntityToParcelDto(parcel);
        return new GetParcelResponse(parcelDto);
    }

    @Override
    public GetParcelCoordinatesResponse getParcelCoordinates(GetParcelByIdRequest request) {
        Optional<Parcel> parcelOptional = repository.findParcelCoordinatesByOrderId(request.getParcelId());
        if (parcelOptional.isEmpty()) {
            throw new ParcelNotFoundException();
        }
        Parcel parcel = parcelOptional.get();

        return new GetParcelCoordinatesResponse(parcel.getLatitude(), parcel.getLongitude());
    }

    @Override
    @Transactional
    @Retryable(value = ObjectOptimisticLockingFailureException.class, maxAttempts = 5)
    public void changeParcelCoordinates(ChangeParcelCoordinatesRequest request) {
        Optional<Parcel> parcelOptional = repository.findParcelCoordinatesByOrderId(request.getParcelId());
        if (parcelOptional.isEmpty()) {
            throw new ParcelNotFoundException();
        }
        Parcel parcel = parcelOptional.get();

        parcel.setLatitude(request.getLatitude());
        parcel.setLongitude(request.getLongitude());
        repository.save(parcel);
    }

    @Override
    public GetParcelsByCourierResponse getParcelsByCourier(GetParcelsByCourierRequest request) {
        List<Parcel> parcels = repository.findAllByCourierId(request.getCourierId());
        List<ParcelDto> parcelDtos = ParcelMapper.mapParcelEntitiesToParcelDtos(parcels);
        return new GetParcelsByCourierResponse(parcelDtos);
    }

    @Override
    public GetParcelResponse getParcelByCourier(GetParcelsByCourierRequest request) {
        Optional<Parcel> parcelOptional = repository.findByCourierId(request.getCourierId());
        if (parcelOptional.isEmpty()) {
            throw new ParcelNotFoundException();
        }
        Parcel parcel = parcelOptional.get();
        ParcelDto parcelDto = ParcelMapper.mapParcelEntityToParcelDto(parcel);
        return new GetParcelResponse(parcelDto);
    }

    private void changeParcelStatus(Optional<Parcel> parcelOptional, ParcelStatus nextStatus) {
        if (parcelOptional.isEmpty()) {
            throw new ParcelNotFoundException();
        }
        Parcel parcel = parcelOptional.get();
        if (!isChangeAllowed(parcel.getStatus(), nextStatus)) {
            throw new InvalidParcelStatusStateException();
        }
        parcel.setStatus(nextStatus);
        repository.save(parcel);
        sendChangeParcelStatusEvents(nextStatus, parcel);
    }

    private void sendChangeParcelStatusEvents(ParcelStatus nextStatus, Parcel parcel) {
        if (nextStatus == DELIVERED) {
            rabbitTemplate.convertAndSend("parcelDelivered", parcel.getOrderId());
            rabbitTemplate.convertAndSend("changeCourierStatus", new ChangeCourierStatusEvent(parcel.getCourierId(), CourierStatus.AVAILABLE));
        }
        if (nextStatus == ASSIGNED) {
            rabbitTemplate.convertAndSend("changeCourierStatus", new ChangeCourierStatusEvent(parcel.getCourierId(), CourierStatus.BUSY));
        }
    }

    private boolean isChangeAllowed(ParcelStatus from, ParcelStatus to) {
        if (CREATED == from) {
            return ASSIGNED == to || DELIVERING == to || DELIVERED == to || CANCELLED == to;
        }
        if (ASSIGNED == from) {
            return DELIVERING == to || CANCELLED == to;
        }
        if (DELIVERING == from) {
            return DELIVERED == to || CANCELLED == to;
        }
        return false;
    }
}

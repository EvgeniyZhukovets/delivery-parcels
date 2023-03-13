package com.delivery.mapper;

import com.delivery.dto.common.ParcelDto;
import com.delivery.entity.Parcel;

import java.util.List;

public class ParcelMapper {

    public static ParcelDto mapParcelEntityToParcelDto(Parcel entity) {
        return ParcelDto.builder()
                .id(entity.getId())
                .createdTime(entity.getCreatedTime())
                .lastModifiedTime(entity.getLastModifiedTime())
                .status(entity.getStatus())
                .courierId(entity.getCourierId())
                .startPoint(entity.getStartPoint())
                .destination(entity.getDestination())
                .latitude(entity.getLatitude())
                .longitude(entity.getLongitude())
                .build();
    }

    public static List<ParcelDto> mapParcelEntitiesToParcelDtos(List<Parcel> entities) {
        return entities.stream()
                .map(ParcelMapper::mapParcelEntityToParcelDto)
                .toList();
    }
}

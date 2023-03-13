package com.delivery

import com.delivery.entity.Parcel
import com.delivery.mapper.ParcelMapper
import org.jeasy.random.EasyRandom
import spock.lang.Specification

class ParcelMapperTest extends Specification {

    def "map parcel entity to parcel dto"() {
        setup:
        def generator = new EasyRandom()
        def parcel = generator.nextObject(Parcel.class)
        when:
        def parcelDto = ParcelMapper.mapParcelEntityToParcelDto(parcel)
        then:
        parcel.getId() == parcelDto.getId()
        parcel.getStatus() == parcelDto.getStatus()
        parcel.getLastModifiedTime() == parcelDto.getLastModifiedTime()
        parcel.getCreatedTime() == parcelDto.getCreatedTime()
        parcel.getCourierId() == parcelDto.getCourierId()
        parcel.getDestination() == parcelDto.getDestination()
        parcel.getLatitude() == parcelDto.getLatitude()
        parcel.getLongitude() == parcelDto.getLongitude()
        parcel.getStartPoint() == parcelDto.getStartPoint()
    }

    def "map parcel entities to parcel dto list"() {
        setup:
        def generator = new EasyRandom()
        def parcels = generator.objects(Parcel.class, 2).toList()
        when:
        def parcelDtos = ParcelMapper.mapParcelEntitiesToParcelDtos(parcels)
        then:
        parcels.size() == parcelDtos.size()
        for (int i = 0; i < parcels.size(); i++) {
            assert parcels[0].getId() == parcelDtos[0].getId()
            assert parcels[0].getStatus() == parcelDtos[0].getStatus()
            assert parcels[0].getLastModifiedTime() == parcelDtos[0].getLastModifiedTime()
            assert parcels[0].getCreatedTime() == parcelDtos[0].getCreatedTime()
            assert parcels[0].getCourierId() == parcelDtos[0].getCourierId()
            assert parcels[0].getDestination() == parcelDtos[0].getDestination()
            assert parcels[0].getLatitude() == parcelDtos[0].getLatitude()
            assert parcels[0].getLongitude() == parcelDtos[0].getLongitude()
            assert parcels[0].getStartPoint() == parcelDtos[0].getStartPoint()
        }
    }
}

package com.delivery.repository;

import com.delivery.entity.Parcel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ParcelRepository extends JpaRepository<Parcel, Long> {

    Optional<Parcel> findByOrderId(Long orderId);

    @Query("SELECT p.latitude, p.longitude FROM Parcel p where p.orderId = :orderId")
    @Modifying
    Optional<Parcel> findParcelCoordinatesByOrderId(@Param("orderId") Long orderId);

    List<Parcel> findAllByCourierId(Long courierId);

    Optional<Parcel> findByCourierId(Long courierId);
}

package com.delivery.entity;

import com.delivery.enums.ParcelStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "parcel")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Parcel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "parcel_id")
    private Long id;
    @Column(name = "order_id")
    private Long orderId;
    @Column(name = "courier_id")
    private Long courierId;
    @Column(name = "user_id")
    private Long userId;
    @Column
    @Enumerated(EnumType.STRING)
    private ParcelStatus status;
    @Column(name = "start_point")
    private String startPoint;
    @Column
    private String destination;
    @CreatedDate
    @Column(name = "created_date")
    private Date createdTime;
    @LastModifiedDate
    @Column(name = "last_modified_date")
    private Date lastModifiedTime;
    @Column
    private Float latitude;
    @Column
    private Float longitude;
    @Version
    private int version;
}

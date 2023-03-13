package com.order.entity;

import com.order.enums.OrderStatus;
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
@Table(name = "`order`")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;
    @Column(name = "user_id")
    private Long userId;
    @Column
    @Enumerated(EnumType.STRING)
    private OrderStatus status;
    @CreatedDate
    @Column(name = "created_date")
    private Date createdTime;
    @LastModifiedDate
    @Column(name = "last_modified_date")
    private Date lastModifiedTime;
    @Column(name = "cancel_reason")
    private String cancelReason;
    @Version
    private int version;
}

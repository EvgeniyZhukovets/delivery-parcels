package com.auth.entity;

import com.auth.enums.CourierStatus;
import com.auth.enums.Role;
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
@Table(name = "users")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;
    @Column
    private String username;
    @Column
    private String password;
    @Column
    @Enumerated(EnumType.STRING)
    private Role role;
    @Column(name = "courier_status")
    @Enumerated(EnumType.STRING)
    private CourierStatus courierStatus;
    @CreatedDate
    @Column(name = "created_date")
    private Date createdTime;
    @LastModifiedDate
    @Column(name = "last_modified_date")
    private Date lastModifiedTime;
    @Version
    private int version;
}

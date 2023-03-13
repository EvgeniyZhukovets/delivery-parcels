package com.delivery.dto.common;

import com.delivery.enums.ParcelStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ParcelDto {

    private Long id;
    private Long courierId;
    private ParcelStatus status;
    private String startPoint;
    private String destination;
    private Date createdTime;
    private Date lastModifiedTime;
    private Float latitude;
    private Float longitude;
}

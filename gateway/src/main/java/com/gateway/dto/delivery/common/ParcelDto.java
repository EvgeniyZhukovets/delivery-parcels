package com.gateway.dto.delivery.common;

import java.util.Date;

public record ParcelDto(Long id,
                        Long courierId,
                        String status,
                        String startPoint,
                        String destination,
                        Date createdTime,
                        Date lastModifiedTime,
                        Float latitude,
                        Float longitude) {
}

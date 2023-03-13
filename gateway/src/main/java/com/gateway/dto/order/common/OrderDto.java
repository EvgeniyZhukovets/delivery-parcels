package com.gateway.dto.order.common;

import java.util.Date;

public record OrderDto(Long id,
                       String status,
                       Date createdTime,
                       Date lastModifiedTime,
                       String cancelReason) {
}

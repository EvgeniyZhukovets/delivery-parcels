package com.order.dto.response;

import com.common.ResponseInfo;
import com.common.StandardResponse;
import com.order.enums.OrderStatus;
import lombok.*;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrderResponse extends StandardResponse {

    private Long id;

    @Builder
    public CreateOrderResponse(ResponseInfo responseInfo, Long id, OrderStatus orderStatus) {
        super(responseInfo);
        this.id = id;
    }
}

package com.order.dto.response;

import com.common.ResponseInfo;
import com.common.StandardResponse;
import com.order.dto.OrderDto;
import lombok.*;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class GetOrdersResponse extends StandardResponse {

    private List<OrderDto> orders;

    @Builder
    public GetOrdersResponse(ResponseInfo responseInfo, List<OrderDto> orders) {
        super(responseInfo);
        this.orders = orders;
    }
}

package com.auth.dto.response;

import com.auth.dto.common.CourierDto;
import com.common.StandardResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class GetAllCouriersResponse extends StandardResponse {

    private List<CourierDto> couriers;
}

package com.delivery.dto.response;

import com.common.ResponseInfo;
import com.common.StandardResponse;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GetParcelCoordinatesResponse extends StandardResponse {

    private Float latitude;
    private Float longitude;

    @Builder
    public GetParcelCoordinatesResponse(ResponseInfo responseInfo, Float latitude, Float longitude) {
        super(responseInfo);
        this.latitude = latitude;
        this.longitude = longitude;
    }
}

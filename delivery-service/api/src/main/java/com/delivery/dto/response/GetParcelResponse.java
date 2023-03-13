package com.delivery.dto.response;

import com.common.ResponseInfo;
import com.common.StandardResponse;
import com.delivery.dto.common.ParcelDto;
import lombok.*;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class GetParcelResponse extends StandardResponse {

    private ParcelDto parcelDto;


    @Builder
    public GetParcelResponse(ResponseInfo responseInfo, ParcelDto parcelDto) {
        super(responseInfo);
        this.parcelDto = parcelDto;
    }
}

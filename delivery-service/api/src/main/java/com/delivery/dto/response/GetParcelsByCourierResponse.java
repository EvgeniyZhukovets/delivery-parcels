package com.delivery.dto.response;

import com.common.ResponseInfo;
import com.common.StandardResponse;
import com.delivery.dto.common.ParcelDto;
import lombok.*;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class GetParcelsByCourierResponse extends StandardResponse {

    private List<ParcelDto> parcelDtos;


    @Builder
    public GetParcelsByCourierResponse(ResponseInfo responseInfo, List<ParcelDto> parcelDtos) {
        super(responseInfo);
        this.parcelDtos = parcelDtos;
    }
}

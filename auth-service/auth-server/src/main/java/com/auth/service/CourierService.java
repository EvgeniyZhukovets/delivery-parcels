package com.auth.service;

import com.auth.dto.response.GetAllCouriersResponse;
import com.auth.event.ChangeCourierStatusEvent;

public interface CourierService {

    GetAllCouriersResponse getAllCouriers();

    void changeCourierStatus(ChangeCourierStatusEvent event);
}

package com.linkedin.learning.converter;

import com.linkedin.learning.entity.ReservationEntity;
import com.linkedin.learning.model.response.ReservationResponse;
import org.springframework.core.convert.converter.Converter;


public class ReservationEntityToReservationResponseConverter implements Converter<ReservationEntity, ReservationResponse> {

    @Override
    public ReservationResponse convert(ReservationEntity source) {

        ReservationResponse reservationResponse = new ReservationResponse();
        reservationResponse.setCheckin(source.getCheckin());
        reservationResponse.setCheckout(source.getCheckout());

        return reservationResponse;
    }
}

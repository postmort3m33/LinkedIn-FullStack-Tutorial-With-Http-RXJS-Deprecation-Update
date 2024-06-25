package com.linkedin.learning.converter;

import com.linkedin.learning.model.Links;
import com.linkedin.learning.model.Self;
import com.linkedin.learning.rest.ResourceConstants;
import org.springframework.core.convert.converter.Converter;

import com.linkedin.learning.entity.RoomEntity;
import com.linkedin.learning.model.response.ReservableRoomResponse;
import org.springframework.stereotype.Component;

@Component
public class RoomEntityToReservableRoomResponseConverter implements Converter<RoomEntity, ReservableRoomResponse> {

    @Override
    public ReservableRoomResponse convert(RoomEntity source) {

        ReservableRoomResponse reservableRoomResponse = new ReservableRoomResponse();
        reservableRoomResponse.setId(source.getId());
        reservableRoomResponse.setRoomNumber(source.getRoomNumber());
        reservableRoomResponse.setPrice(Integer.valueOf(source.getPrice()));

        Links links = new Links();
        Self self = new Self();
        self.setRef(ResourceConstants.ROOM_RESERVATION_V1 + "/" + source.getId());
        links.setSelf(self);

        reservableRoomResponse.setLinks(links);

        return reservableRoomResponse;
    }
}

package com.linkedin.learning.entity;

import jakarta.annotation.Nonnull;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name="Reservation")
public class ReservationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Nonnull
    private LocalDate checkin;

    @Nonnull
    private LocalDate checkout;

    @ManyToOne
    private RoomEntity roomEntity;

    public RoomEntity getRoomEntity() {
        return roomEntity;
    }

    public void setRoomEntity(RoomEntity roomEntity) {
        this.roomEntity = roomEntity;
    }

    public ReservationEntity() {
    }

    public ReservationEntity(@Nonnull LocalDate checkin, @Nonnull LocalDate checkout) {
        this.checkin = checkin;
        this.checkout = checkout;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Nonnull
    public LocalDate getCheckin() {
        return checkin;
    }

    public void setCheckin(@Nonnull LocalDate checkin) {
        this.checkin = checkin;
    }

    @Nonnull
    public LocalDate getCheckout() {
        return checkout;
    }

    public void setCheckout(@Nonnull LocalDate checkout) {
        this.checkout = checkout;
    }
}

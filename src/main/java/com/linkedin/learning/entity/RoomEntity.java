package com.linkedin.learning.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.annotation.Nonnull;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name= "Room")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class RoomEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Nonnull
    private Integer roomNumber;

    @Nonnull
    private String price;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private List<ReservationEntity> reservationEntityList;

    // Constructors
    public RoomEntity() {
        super();
    }

    public RoomEntity(@Nonnull Integer roomNumber, @Nonnull String price) {
        super();
        this.roomNumber = roomNumber;
        this.price = price;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Nonnull
    public Integer getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(@Nonnull Integer roomNumber) {
        this.roomNumber = roomNumber;
    }

    @Nonnull
    public String getPrice() {
        return price;
    }

    public void setPrice(@Nonnull String price) {
        this.price = price;
    }

    public List<ReservationEntity> getReservationEntityList() {
        return reservationEntityList;
    }

    public void setReservationEntityList(List<ReservationEntity> reservationEntityList) {
        this.reservationEntityList = reservationEntityList;
    }

    // Methods
    public void addReservationEntity(ReservationEntity reservationEntity) {
        if(null == reservationEntityList)
            reservationEntityList = new ArrayList<>();

        reservationEntityList.add(reservationEntity);

    }

}

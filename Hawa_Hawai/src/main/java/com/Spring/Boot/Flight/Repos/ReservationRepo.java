package com.Spring.Boot.Flight.Repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Spring.Boot.Flight.entities.Reservation;

public interface ReservationRepo extends JpaRepository<Reservation, Long>{

}

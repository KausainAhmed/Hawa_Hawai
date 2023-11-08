package com.Spring.Boot.Flight.services;

import com.Spring.Boot.Flight.dto.ReservationRequest;
import com.Spring.Boot.Flight.entities.Reservation;

public interface ReservationService {
public Reservation bookFlight(ReservationRequest request);

}

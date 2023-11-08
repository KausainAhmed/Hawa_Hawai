package com.Spring.Boot.Flight.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.Spring.Boot.Flight.Repos.ReservationRepo;
import com.Spring.Boot.Flight.dto.ReservationUpdateRequest;
import com.Spring.Boot.Flight.entities.Reservation;

@RestController
@CrossOrigin
public class ReservationRestController {

	@Autowired
	ReservationRepo reservationRepo;
	
@RequestMapping("/reservations/{id}")
public Reservation findReservation(@PathVariable("id")Long id) {
	Reservation reservation = reservationRepo.findById(id).get();
	return reservation;
	
}

@RequestMapping("/reservations")
public Reservation updateReservation(@RequestBody ReservationUpdateRequest request){
Reservation reservation=reservationRepo.findById(request.getId()).get();
	reservation.setNumberOfBags(request.getNumberOfBags());
	reservation.setCheckedIn(request.getCheckedIn());
	return reservationRepo.save(reservation);
	
}
}

package com.Spring.Boot.Flight.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Spring.Boot.Flight.Repos.FlightRepo;
import com.Spring.Boot.Flight.Repos.PassengerRepo;
import com.Spring.Boot.Flight.Repos.ReservationRepo;
import com.Spring.Boot.Flight.Util.EmailUtil;
import com.Spring.Boot.Flight.Util.PDFGenerator;
import com.Spring.Boot.Flight.dto.ReservationRequest;
import com.Spring.Boot.Flight.entities.Flight;
import com.Spring.Boot.Flight.entities.Passenger;
import com.Spring.Boot.Flight.entities.Reservation;

@Service
public class ReservationServiceImpl implements ReservationService {
	@Autowired
	FlightRepo flightRepo;

	@Autowired
	PassengerRepo passengerRepo;

	@Autowired
	ReservationRepo reservationRepo;

	@Autowired
	EmailUtil emailUtil;
	
	@Autowired
	PDFGenerator pdfGenerator;
	
	@Override
	public Reservation bookFlight(ReservationRequest request) {
		// Make Payment
		Long flightId = request.getFlightId();
		Flight flight = flightRepo.findById(flightId).get();

		Passenger passenger = new Passenger();

		passenger.setFirstName(request.getPassengerFirstName());
		passenger.setLastName(request.getPassengerLastName());
		passenger.setPhone(request.getPassengerPhone());
		passenger.setEmail(request.getPassengerEmail());
		Passenger savedPassenger = passengerRepo.save(passenger);

		Reservation reservation = new Reservation();
		reservation.setFlight(flight);
		reservation.setPassenger(passenger);
		reservation.setCheckedIn(false);
		Reservation savedReservation = reservationRepo.save(reservation);
		
		String filePath="E:\\reservationMail\\reservation"+savedReservation.getId()+".pdf";
		pdfGenerator.generateItinerary(savedReservation, filePath);
		emailUtil.sendItinerary(passenger.getEmail(), filePath);

		return savedReservation;
	}

}

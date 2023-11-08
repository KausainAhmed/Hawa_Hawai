package com.Spring.Boot.Flight.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.Spring.Boot.Flight.Repos.FlightRepo;
import com.Spring.Boot.Flight.dto.ReservationRequest;
import com.Spring.Boot.Flight.entities.Flight;
import com.Spring.Boot.Flight.entities.Reservation;
import com.Spring.Boot.Flight.services.ReservationService;

@Controller
public class ReservationController {
	@Autowired
	FlightRepo flightRepo;
	@Autowired
	ReservationService reservationService;

	@RequestMapping("/showCompleteReservation")
	public String showCompleteReservation(@RequestParam("flightId") Long flightId, ModelMap modelMap) {
		Flight flight = flightRepo.findById(flightId).get();
		modelMap.addAttribute("flight", flight);
		return "completeReservation";
	} 

	@RequestMapping(value = "/completeReservation")
	public String completeReservation(ReservationRequest request, ModelMap modelMap) {
		Reservation reservation = reservationService.bookFlight(request);
		modelMap.addAttribute("msg", "Reservation Created Successfully and the id is" + reservation.getId());
		return "reservationConfirmation";
	}
}

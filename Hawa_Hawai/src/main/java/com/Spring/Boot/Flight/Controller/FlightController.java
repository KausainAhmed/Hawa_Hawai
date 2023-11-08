package com.Spring.Boot.Flight.Controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.Spring.Boot.Flight.Repos.FlightRepo;
import com.Spring.Boot.Flight.entities.Flight;

@Controller
public class FlightController {

	@Autowired
	FlightRepo flightRepo;

	@RequestMapping("/findFlights")
	public String findFlights(@RequestParam("from") String from, @RequestParam("to") String to,
			@RequestParam("departureDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date departureDate,
			ModelMap modelMap) {
		List<Flight> flights = flightRepo.findFlights(from, to, departureDate);
		System.out.println(flights);
		for (Flight f : flights) {
			System.out.println(f.getArrivalCity());
		}
		modelMap.addAttribute("flights", flights);
		return "displayFlights";

	}

}
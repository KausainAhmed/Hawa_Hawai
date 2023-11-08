package com.Spring.Boot.Flight.Repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Spring.Boot.Flight.entities.Passenger;

public interface PassengerRepo extends JpaRepository<Passenger, Long>{

}

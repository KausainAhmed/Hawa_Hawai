package com.Spring.Boot.Flight.Repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Spring.Boot.Flight.entities.Myuser;

public interface MyuserRepo extends JpaRepository<Myuser, Long>{
	Myuser findByEmail(String email);
}

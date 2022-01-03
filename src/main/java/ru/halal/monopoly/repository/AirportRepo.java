package ru.halal.monopoly.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.halal.monopoly.domain.ownerships.Airport;

public interface AirportRepo extends JpaRepository<Airport, Integer> {
    Airport findByName(String name);
}

package ru.halal.monopoly.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.halal.monopoly.domain.Gamer;
import ru.halal.monopoly.domain.ownerships.City;

public interface CityRepo extends JpaRepository<City, Integer> {
    City findByName(String name);
}

package ru.halal.monopoly.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.halal.monopoly.domain.ownerships.Communal;

public interface CommunalRepo extends JpaRepository<Communal, Integer> {
    Communal findByName(String name);
}

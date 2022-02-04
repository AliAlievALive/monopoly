package ru.halal.monopoly.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.halal.monopoly.domain.Gamer;
import ru.halal.monopoly.domain.ownerships.Ownership;

public interface OwnershipRepo extends JpaRepository<Ownership, Integer> {
    Ownership findByName(String name);
}

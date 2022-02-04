package ru.halal.monopoly.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.halal.monopoly.domain.ownerships.GamerOwns;
import ru.halal.monopoly.domain.ownerships.Ownership;

public interface OwnershipRepo extends JpaRepository<GamerOwns, Integer> {
}

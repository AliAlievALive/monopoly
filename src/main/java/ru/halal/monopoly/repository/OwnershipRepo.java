package ru.halal.monopoly.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.halal.monopoly.domain.GamerOwns;

public interface OwnershipRepo extends JpaRepository<GamerOwns, Integer> {
}

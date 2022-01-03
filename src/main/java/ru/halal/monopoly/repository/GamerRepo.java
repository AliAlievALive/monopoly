package ru.halal.monopoly.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.halal.monopoly.domain.Gamer;

public interface GamerRepo extends JpaRepository<Gamer, Integer> {
    Gamer findByName(String name);
}

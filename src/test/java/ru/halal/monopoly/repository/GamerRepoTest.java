package ru.halal.monopoly.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import ru.halal.monopoly.domain.Gamer;

import static org.assertj.core.api.BDDAssertions.then;

@DataJpaTest
public class GamerRepoTest {
    @Autowired
    private GamerRepo gamerRepo;

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    void getGamerByName_returnGamerDetails() {
        Gamer almir = Gamer.builder().name("Almir").money(15000).build();
        testEntityManager.persistAndFlush(almir);
        Gamer byName = gamerRepo.findByName("Almir");
        then(almir.getName()).isEqualTo(byName.getName());
    }
}

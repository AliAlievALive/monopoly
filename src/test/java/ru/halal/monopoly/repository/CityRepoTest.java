package ru.halal.monopoly.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import ru.halal.monopoly.domain.ownerships.City;

import static org.assertj.core.api.BDDAssertions.then;
import static ru.halal.monopoly.domain.ownerships.EColors.BLUE;

@DataJpaTest
public class CityRepoTest {
    @Autowired
    private CityRepo cityRepo;

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    void getCityByName_returnCityDetails() {
        City perm = City.builder()
                .name("Perm")
                .color(BLUE)
                .cost(1000)
                .depositCost(800)
                .homeCost(300)
                .oneCityCost(200)
                .twoCityCost(350)
                .threeCityCost(500)
                .isInDeposit(false)
                .isEnableToBuy(true)
                .rentCost(250)
                .build();
        testEntityManager.persistAndFlush(perm);
        City cityByName = cityRepo.findByName("Perm");
        then(perm.getName()).isEqualTo(cityByName.getName());
    }
}

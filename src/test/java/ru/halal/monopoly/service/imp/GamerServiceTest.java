package ru.halal.monopoly.service.imp;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.halal.monopoly.domain.Gamer;
import ru.halal.monopoly.repository.GamerRepo;
import ru.halal.monopoly.service.GamerService;

import javax.transaction.Transactional;

import static org.assertj.core.api.BDDAssertions.then;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@Transactional
public class GamerServiceTest {
    @Autowired
    private GamerRepo gamerRepo;

    @Autowired
    private GamerService gamerService;

    @DisplayName("Returning saved gamer from service layer")
    @Test
    void getGamersById_forSavedGamer_isReturned() {
        Gamer saved = gamerRepo.save(Gamer.builder()
                .name("Rita")
                .money(15000)
                .build());
        Gamer gamer = gamerService.getGamerById(saved.getId());
        then(gamer.getName()).isEqualTo(saved.getName());
    }
}

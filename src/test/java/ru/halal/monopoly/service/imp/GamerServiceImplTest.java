package ru.halal.monopoly.service.imp;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.halal.monopoly.domain.Gamer;
import ru.halal.monopoly.repository.GamerRepo;
import ru.halal.monopoly.service.GamerService;

import java.util.Optional;

@SpringBootTest
class GamerServiceImplTest {
    @Autowired
    private GamerService gamerService;

    @MockBean
    private GamerRepo gamerRepo;

    @Test
    void createGamer() {
        Gamer gamer = new Gamer();
        gamerService.create(gamer);
    }

    @Test
    void getGamer() {
        int id = 13;
        Optional<Gamer> gamerOptional = Optional.of(new Gamer(13, "Alim", 2660, null));
        Mockito.doReturn(gamerOptional)
                .when(gamerRepo)
                .findById(id);
        gamerService.getGamerById(id);
        Mockito.verify(gamerRepo, Mockito.times(1)).findById(id);
    }

    @Test
    void getGamerFailTest() {
        int id = -4;
        Mockito.doReturn(null)
                .when(gamerRepo)
                .findById(id);
    }
}

package ru.halal.monopoly.cache;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import ru.halal.monopoly.domain.Gamer;
import ru.halal.monopoly.repository.GamerRepo;
import ru.halal.monopoly.service.GamerService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.NONE;

@SpringBootTest(webEnvironment = NONE)
public class GamerCacheTest {
    @Autowired
    private GamerService gamerService;

    @MockBean
    private GamerRepo gamerRepo;

    @Test
    void getGamerById_forMultipleRequests_isRetrievedFromCache() {
        int id = 50;
        given(gamerRepo.findById(id))
                .willReturn(Optional.of(new Gamer(id, "Hava", 15000, null)));

        gamerService.getGamerById(id);
        gamerService.getGamerById(id);
        gamerService.getGamerById(id);
        gamerService.getGamerById(id);

        then(gamerRepo).should(times(1)).findById(id);
    }

    @Test
    void getGamers_forMultipleRequests_isRetrievedFromCache() {

        given(gamerRepo.findAll())
                .willReturn(List.of(
                        new Gamer(1, "Hava", 15000, null),
                        new Gamer(2, "Alim", 15000, null),
                        new Gamer(3, "Rita", 15000, null),
                        new Gamer(4, "Almir", 15000, null)
                ));

        gamerService.getGamers();
        gamerService.getGamers();
        gamerService.getGamers();
        gamerService.getGamers();

        then(gamerRepo).should(times(1)).findAll();
    }
}

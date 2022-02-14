package ru.halal.monopoly.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.halal.monopoly.domain.Gamer;
import ru.halal.monopoly.service.GamerService;

import static org.hamcrest.core.StringContains.containsString;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
//@TestPropertySource("/application-test.properties")
//@Sql(value = {"/gamers-list-before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
//@Sql(value = {"/gamers-list-after.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class GamerControllerTest {
    private final static String URL = "/api/gamers";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GamerService gamerService;

    @Test
    void gamersGets_forSavedGamer_isReturned() throws Exception {
        given(gamerService.getGamerById(anyInt())).willReturn(
                Gamer.builder()
                        .id(50)
                        .name("Alim")
                        .money(15000)
                        .build()
        );

        mockMvc.perform(get(URL + "/50"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("statusCode").value(200))
                .andExpect(jsonPath("status").value("OK"))
                .andExpect(content().string(containsString("Gamer with 50 is found")));
    }


//    @Test
//    void saveGamerTest() throws Exception {
//        Gamer anton = new Gamer(0, "Anton", 15000, new GamerOwns());
//        System.out.println("ghjghj");
//        mockMvc.perform(post(URL)
//                        .content(asJsonString(anton))
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(content().string(containsString("Gamer Anton is save")));
//    }
//
//    @Test
//    void updateGamerTest() throws Exception {
//        Gamer anton = new Gamer(0, "Anton", 15000,
//                new GamerOwns());
//        this.mockMvc.perform(put(URL)
//                        .content(asJsonString(anton))
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(content().string(containsString("Gamer Anton is save")));
//    }

//    private static String asJsonString(final Object obj) {
//        try {
//            return new ObjectMapper().writeValueAsString(obj);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
}

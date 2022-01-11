package ru.halal.monopoly;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import ru.halal.monopoly.controller.GamerController;
import ru.halal.monopoly.domain.Gamer;

import java.util.ArrayList;

import static org.hamcrest.core.StringContains.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("/application-test.properties")
@Sql(value = {"/gamers-list-before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"/gamers-list-after.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class GamerControllerTest {
    @Autowired
    private GamerController controller;
    @Autowired
    private MockMvc mockMvc;

//    @Test
//    void gamersGetsTest() throws Exception {
//        this.mockMvc.perform(get("/api/gamer/list"))
//                .andDo(print())
//                .andExpect(status().isOk());
//                .andExpect(content().string(containsString("Gamers list limited 10")));
//    }

    @Test
    void saveGamerTest() throws Exception {
        this.mockMvc.perform(post("/api/gamer/save")
                        .content(asJsonString(
                                new Gamer(0, "ALi", 15000,
                                        new ArrayList<>(), new ArrayList<>(), new ArrayList<>())))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("")));
    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

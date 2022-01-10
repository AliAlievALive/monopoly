package ru.halal.monopoly;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import ru.halal.monopoly.controller.GamerController;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureMockMvc
class MonopolyApplicationTests {
    @Autowired
    private GamerController controller;

    @Test
    void contextLoads() {
        assertThat(controller).isNotNull();
    }
}

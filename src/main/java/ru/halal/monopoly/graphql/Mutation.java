package ru.halal.monopoly.graphql;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import org.springframework.stereotype.Component;
import ru.halal.monopoly.domain.Gamer;
import ru.halal.monopoly.repository.GamerRepo;

@Component
public class Mutation implements GraphQLMutationResolver {
    private final GamerRepo gamerRepo;

    public Mutation(GamerRepo gamerRepo) {
        this.gamerRepo = gamerRepo;
    }

    public Gamer createGamer(String name, int money) {
        return gamerRepo.saveAndFlush(
                Gamer.builder()
                        .name(name)
                        .money(money)
                        .build()
        );
    }

    public Gamer updateGamer(String name, int money) {
        return gamerRepo.saveAndFlush(
                Gamer.builder()
                        .name(name)
                        .money(money)
                        .build()
        );
    }

    public boolean deleteGamer(int id) {
        gamerRepo.deleteById(id);
        return true;
    }
}

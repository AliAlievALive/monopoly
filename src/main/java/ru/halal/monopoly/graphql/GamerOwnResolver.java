package ru.halal.monopoly.graphql;

import com.coxautodev.graphql.tools.GraphQLResolver;
import ru.halal.monopoly.domain.Gamer;
import ru.halal.monopoly.repository.GamerRepo;

public class GamerOwnResolver implements GraphQLResolver<Gamer> {
    private final GamerRepo gamerRepo;

    public GamerOwnResolver(GamerRepo gamerRepo) {
        this.gamerRepo = gamerRepo;
    }

}

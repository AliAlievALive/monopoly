package ru.halal.monopoly;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import org.springframework.stereotype.Component;
import ru.halal.monopoly.domain.Gamer;
import ru.halal.monopoly.repository.GamerRepo;

import java.util.List;

@Component
public class Query implements GraphQLQueryResolver {
    private final GamerRepo gamerRepo;

    public Query(GamerRepo gamerRepo) {
        this.gamerRepo = gamerRepo;
    }

    public List<Gamer> findAllGamers() {
        return gamerRepo.findAll();
    }

    public Gamer gamerById(Integer id) {
        return gamerRepo.findById(id).orElse(null);
    }
}

package ru.halal.monopoly.service.imp;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.halal.monopoly.domain.Gamer;
import ru.halal.monopoly.domain.GamerOwns;
import ru.halal.monopoly.domain.ownerships.*;
import ru.halal.monopoly.repository.*;
import ru.halal.monopoly.service.GamerService;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Optional;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static ru.halal.monopoly.domain.ownerships.EType.*;

@RequiredArgsConstructor
@Service
@Transactional
@Slf4j
public class GamerServiceImpl implements GamerService {
    private final GamerRepo gamerRepo;
    private final CityRepo cityRepo;
    private final CommunalRepo communalRepo;
    private final AirportRepo airportRepo;
    private final OwnershipRepo ownershipRepo;

    @Override
    public Gamer create(Gamer gamer) {
        return gamerRepo.save(gamer);
    }

    @Override
    public Gamer update(Gamer gamer) {
        return gamerRepo.saveAndFlush(gamer);
    }

    @Override
    public int changeMoneyCounts(int id, int money) {
        Gamer gamer = getGamerById(id);
        gamer.setMoney(gamer.getMoney() + money);
        gamerRepo.save(gamer);
        return gamer.getMoney();
    }

    @Override
    public Boolean moneyToGamerFromGamerName(String name1, int money, String name2) {
        Gamer gamer1 = gamerRepo.findByName(name1);
        Gamer gamer2 = gamerRepo.findByName(name2);
        gamer1.setMoney(gamer1.getMoney() - money);
        gamer2.setMoney(gamer2.getMoney() + money);
        gamerRepo.save(gamer1);
        gamerRepo.save(gamer2);
        return TRUE;
    }

    @Override
    public Boolean addOwnToGamer(int ownershipId, int gamerId, TypeWrapper type) {
        Gamer gamerRepoById = gamerRepo.getById(gamerId);
        GamerOwns ownership = gamerRepoById.getOwnership();
        if (ownership == null) {
            ownership = new GamerOwns();
            ownership.setGamer(gamerRepoById);
        }
        if (type.getType().equals(CITY)) {
            ownership.addCityToGamer(cityRepo.getById(ownershipId));
        }
        if (type.getType().equals(COMMUNAL)) {
            ownership.addCommunalToGamer(communalRepo.getById(ownershipId));
        }
        if (type.getType().equals(AIRPORT)) {
            ownership.addAirportToGamer(airportRepo.getById(ownershipId));
        }
        gamerRepoById.setOwnership(ownershipRepo.save(ownership));
        return TRUE;
    }

    @Override
    public GamerOwns getOwn(Gamer gamer) {
        return getGamerById(gamer.getId()).getOwnership();
    }

    @Override
    public int getMoney(String name) {
        return gamerRepo.findByName(name).getMoney();
    }

    @Override
    public int getMoney(Gamer gamer) {
        return gamerRepo.findByName(gamer.getName()).getMoney();
    }

    @Override
    @Cacheable("gamers")
    public Collection<Gamer> getGamers(int limit) {
        return gamerRepo.findAll(PageRequest.of(0, limit)).toList();
    }

    @Override
    public Boolean giveOwnToAnotherGamer(TypeWrapper type, int fromId, int toId, int ownId) {
        GamerOwns fromOwn = getGamerById(fromId).getOwnership();
        GamerOwns toOwn = getGamerById(toId).getOwnership();

        Object own = findOwn(type, ownId, fromOwn);
        if (own instanceof City) {
            ((City) own).setGamerOwns(toOwn);
            cityRepo.save((City) own);
            return TRUE;
        }
        if (own instanceof Communal) {
            ((Communal) own).setGamerOwns(toOwn);
            communalRepo.save((Communal) own);
            return TRUE;
        }
        if (own instanceof Airport) {
            ((Airport) own).setGamerOwns(toOwn);
            airportRepo.save((Airport) own);
            return TRUE;
        }
        return FALSE;
    }

    private Object findOwn(TypeWrapper type, int ownId, GamerOwns fromGamer) {
        if (type.getType().equals(CITY)) {
            return fromGamer.getCities().stream()
                    .filter(city -> city.getId() == ownId).findAny();
        }
        if (type.getType().equals(COMMUNAL)) {
            return fromGamer.getCommunals().stream()
                    .filter(communal -> communal.getId() == ownId).findAny();
        }
        if (type.getType().equals(AIRPORT)) {
            return fromGamer.getAirports().stream()
                    .filter(airport -> airport.getId() == ownId).findAny();
        }
        throw new IllegalArgumentException(String.format("Gamer haven't %s with id %s", type, ownId));
    }

    @Override
    @Cacheable("gamer")
    public Gamer getGamerById(int id) {
       return gamerRepo.findById(id)
               .orElseThrow(() -> new IllegalStateException(String.format("Gamer with id %s is not found", id)));
    }

    @Override
    public Boolean delete(int id) {
        gamerRepo.deleteById(id);
        return TRUE;
    }
}

package ru.halal.monopoly.service.imp;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
        Optional<Gamer> gamerOptional = gamerRepo.findById(id);
        Gamer gamer = gamerOptional.get();
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
        return gamerRepo.findById(gamer.getId()).get().getOwnership();
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
    public Collection<Gamer> getGamers(int limit) {
        return gamerRepo.findAll(PageRequest.of(0, limit)).toList();
    }

    @Override
    public Boolean giveOwnToAnotherGamer(TypeWrapper type, int fromId, int toId, int ownId) {
        GamerOwns fromOwn = gamerRepo.findById(fromId).get().getOwnership();
        GamerOwns toOwn = gamerRepo.findById(toId).get().getOwnership();

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
            Optional<City> cityOptional = fromGamer.getCities().stream()
                    .filter(city -> city.getId() == ownId).findAny();
            if (cityOptional.isPresent()) {
                return cityOptional.get();
            }
        }
        if (type.getType().equals(COMMUNAL)) {
            Optional<Communal> communalOptional = fromGamer.getCommunals().stream()
                    .filter(communal -> communal.getId() == ownId).findAny();
            if (communalOptional.isPresent()) {
                return communalOptional.get();
            }
        }
        if (type.getType().equals(AIRPORT)) {
            Optional<Airport> airportOptional = fromGamer.getAirports().stream()
                    .filter(airport -> airport.getId() == ownId).findAny();
            if (airportOptional.isPresent()) {
                return airportOptional.get();
            }
        }
        return null;
    }

    @Override
    public Gamer getGamerById(int id) {
        Gamer gamer = null;
        Optional<Gamer> optionalGamer = gamerRepo.findById(id);
        if (optionalGamer.isPresent()) {
            gamer = optionalGamer.get();
        }
        return gamer;
    }

    @Override
    public Boolean delete(int id) {
        gamerRepo.deleteById(id);
        return TRUE;
    }
}

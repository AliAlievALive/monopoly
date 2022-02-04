package ru.halal.monopoly.service.imp;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.halal.monopoly.domain.Gamer;
import ru.halal.monopoly.domain.ownerships.*;
import ru.halal.monopoly.repository.GamerRepo;
import ru.halal.monopoly.repository.OwnershipRepo;
import ru.halal.monopoly.service.GamerService;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

@RequiredArgsConstructor
@Service
@Transactional
@Slf4j
public class GamerServiceImpl implements GamerService {
    private final GamerRepo gamerRepo;
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
    public Boolean addOwnToGamer(Ownership ownership, int gamerId) {
        Optional<Gamer> gamerOptional = gamerRepo.findById(gamerId);
        Gamer gamer = gamerOptional.get();
        if (ownership instanceof City) {
            gamer.getOwnership().getCities().add((City) ownership);
        } else if (ownership instanceof Airport) {
            gamer.getOwnership().getAirports().add((Airport) ownership);
        } else if (ownership instanceof Communal) {
            gamer.getOwnership().getCommunals().add((Communal) ownership);
        }
        gamerRepo.save(gamer);
        return TRUE;
    }

    @Override
    public GamerOwns getOwns(Gamer gamer) {
        GamerOwns ownerships = new GamerOwns();
        Optional<Gamer> optionalGamer = gamerRepo.findById(gamer.getId());
        if (optionalGamer.isPresent()) {
            ownerships = optionalGamer.get().getOwnership();
        }
        return ownerships;
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
    public Boolean giveOwnToAnotherGamer(int fromId, int toId, int ownId) {
        Optional<Gamer> optionalFromGamer = gamerRepo.findById(fromId);
        Optional<Gamer> optionalToGamer = gamerRepo.findById(toId);
        Gamer fromGamer = optionalFromGamer.get();
        Gamer toGamer = optionalToGamer.get();
        GamerOwns gamerOwns = fromGamer.getOwnership();
        Ownership ownership = findOwnership(ownId, gamerOwns);
        if (gamerOwns.removeOwn(ownership)) {
            toGamer.getOwnership().addOwn(ownership);
            gamerRepo.save(fromGamer);
            gamerRepo.save(toGamer);
            return TRUE;
        }
        return FALSE;
    }

    private Ownership findOwnership(int ownId, GamerOwns gamerOwns) {
        Optional<Airport> optionalAirport = gamerOwns.getAirports().stream()
                .filter(airport -> airport.getId() == ownId)
                .findAny();

        Optional<Communal> optionalCommunal = gamerOwns.getCommunals().stream()
                .filter(communal -> communal.getId() == ownId)
                .findAny();

        Optional<City> optionalCity = gamerOwns.getCities().stream()
                .filter(city -> city.getId() == ownId)
                .findAny();
        Ownership ownership = optionalAirport.isPresent() ?
                optionalAirport.get() :
                    (optionalCity.isPresent() ?
                        optionalCity.get() : optionalCommunal.get());
        return ownership;
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

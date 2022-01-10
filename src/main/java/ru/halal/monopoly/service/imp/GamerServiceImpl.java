package ru.halal.monopoly.service.imp;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.halal.monopoly.domain.Gamer;
import ru.halal.monopoly.domain.ownerships.Airport;
import ru.halal.monopoly.domain.ownerships.City;
import ru.halal.monopoly.domain.ownerships.Communal;
import ru.halal.monopoly.repository.CityRepo;
import ru.halal.monopoly.repository.GamerRepo;
import ru.halal.monopoly.service.GamerService;

import javax.transaction.Transactional;
import java.util.ArrayList;
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
    private final CityRepo cityRepo;

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
    public Boolean addCityToGamer(int cityId, int gamerId) {
        Optional<Gamer> gamerOptional = gamerRepo.findById(gamerId);
        Optional<City> cityOptional = cityRepo.findById(cityId);
        Gamer gamer = gamerOptional.get();
        City city = cityOptional.get();
        gamer.addCity(city);
        gamerRepo.save(gamer);
        return TRUE;
    }

    @Override
    public void addCommunalToGamer(Communal communal, int id) {
        Optional<Gamer> gamerOptional = gamerRepo.findById(id);
        if (gamerOptional.isPresent()) {
            Gamer gamer = gamerOptional.get();
            gamer.addCommunal(communal);
            gamerRepo.save(gamer);
        }
    }

    @Override
    public void addAirportToGamer(Airport airport, int id) {
        Optional<Gamer> gamerOptional = gamerRepo.findById(id);
        if (gamerOptional.isPresent()) {
            Gamer gamer = gamerOptional.get();
            gamer.addAirport(airport);
            gamerRepo.save(gamer);
        }
    }

    @Override
    public List<City> getCities(Gamer gamer) {
        List<City> cities = new ArrayList<>();
        Optional<Gamer> citiesOptional = gamerRepo.findById(gamer.getId());
        if (citiesOptional.isPresent()) {
            cities = citiesOptional.get().getCities();
        }
        return cities;
    }

    @Override
    public List<Communal> getCommunal(Gamer gamer) {
        List<Communal> communals = new ArrayList<>();
        Optional<Gamer> citiesOptional = gamerRepo.findById(gamer.getId());
        if (citiesOptional.isPresent()) {
            communals = citiesOptional.get().getCommunal();
        }
        return communals;
    }

    @Override
    public List<Airport> getAirport(Gamer gamer) {
        List<Airport> airports = new ArrayList<>();
        Optional<Gamer> citiesOptional = gamerRepo.findById(gamer.getId());
        if (citiesOptional.isPresent()) {
            airports = citiesOptional.get().getAirports();
        }
        return airports;
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
    public Boolean giveCityToAnotherGamer(int fromId, int toId, int cityId) {
        Optional<Gamer> optionalFromGamer = gamerRepo.findById(fromId);
        Optional<Gamer> optionalToGamer = gamerRepo.findById(toId);
        Gamer fromGamer = optionalFromGamer.get();
        Gamer toGamer = optionalToGamer.get();
        Optional<City> optionalCity = fromGamer.getCities().stream().filter(city -> city.getId() == cityId).findFirst();
        City cityForMove = optionalCity.get();
        if (fromGamer.removeCity(cityForMove)) {
            toGamer.addCity(cityForMove);
            gamerRepo.save(fromGamer);
            gamerRepo.save(toGamer);
            return TRUE;
        }
        return FALSE;
    }

    @Override
    public Gamer getGamer(int id) {
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

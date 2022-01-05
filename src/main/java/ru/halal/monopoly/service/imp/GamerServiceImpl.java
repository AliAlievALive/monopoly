package ru.halal.monopoly.service.imp;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.halal.monopoly.domain.Gamer;
import ru.halal.monopoly.domain.ownerships.Airport;
import ru.halal.monopoly.domain.ownerships.City;
import ru.halal.monopoly.domain.ownerships.Communal;
import ru.halal.monopoly.repository.GamerRepo;
import ru.halal.monopoly.service.GamerService;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional
@Slf4j
public class GamerServiceImpl implements GamerService {
    private final GamerRepo gamerRepo;

    @Override
    public Gamer create(Gamer gamer) {
        return gamerRepo.save(gamer);
    }

    @Override
    public void update(Gamer gamer) {
        gamerRepo.save(gamer);
    }

    @Override
    public void changeMoneyCounts(int id, int money) {
        Optional<Gamer> gamerOptional = gamerRepo.findById(id);
        if (gamerOptional.isPresent()) {
            Gamer gamer = gamerOptional.get();
            gamer.setMoney(gamer.getMoney() + money);
            gamerRepo.save(gamer);
        }
    }

    @Override
    public void addCityToGamer(City city, int id) {
        Optional<Gamer> gamerOptional = gamerRepo.findById(id);
        if (gamerOptional.isPresent()) {
            Gamer gamer = gamerOptional.get();
            gamer.addCity(city);
            gamerRepo.save(gamer);
        }
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
    public Gamer getGamer(int id) {
        Gamer gamer = null;
        Optional<Gamer> optionalGamer = gamerRepo.findById(id);
        if (optionalGamer.isPresent()) {
            gamer = optionalGamer.get();
        }
        return gamer;
    }

    @Override
    public void delete(Gamer gamer) {
        gamerRepo.delete(gamer);
    }
}

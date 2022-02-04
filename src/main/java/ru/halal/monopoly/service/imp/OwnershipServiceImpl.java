package ru.halal.monopoly.service.imp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.halal.monopoly.domain.ownerships.Airport;
import ru.halal.monopoly.domain.ownerships.City;
import ru.halal.monopoly.domain.ownerships.Communal;
import ru.halal.monopoly.domain.ownerships.Ownership;
import ru.halal.monopoly.repository.*;
import ru.halal.monopoly.service.OwnershipService;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static ru.halal.monopoly.domain.ownerships.EType.*;

@Service
@Transactional
@Slf4j
public class OwnershipServiceImpl implements OwnershipService {
    private final CityRepo cityRepo;
    private final CommunalRepo communalRepo;
    private final AirportRepo airportRepo;

    @Autowired
    public OwnershipServiceImpl(CityRepo cityRepo,
                                CommunalRepo communalRepo,
                                AirportRepo airportRepo) {
        this.cityRepo = cityRepo;
        this.communalRepo = communalRepo;
        this.airportRepo = airportRepo;
    }

    @Override
    public <T extends Ownership> Ownership createOrUpdate(T ownership) {
        if (ownership.getType().equals(CITY)) {
            City city = new City(ownership);
            return cityRepo.save(city);
        }
        if (ownership.getType().equals(COMMUNAL)) {
            Communal communal = new Communal(ownership);
            return communalRepo.save(communal);
        }
        if (ownership.getType().equals(AIRPORT)) {
            Airport airport = new Airport(ownership);
            return airportRepo.save(airport);
        }
        return ownership;
    }

    @Override
    public Collection<Ownership> getOwnerships(int limit) {
        List<City> allCity = cityRepo.findAll();
        List<Communal> allCommunal = communalRepo.findAll();
        List<Airport> allAirport = airportRepo.findAll();
        List<Ownership> ownerships = new ArrayList<>();
        ownerships.addAll(allCity);
        ownerships.addAll(allCommunal);
        ownerships.addAll(allAirport);
        return ownerships.stream().limit(limit).toList();
    }

    @Override
    public Ownership getOwnership(int id) {
        Optional<City> city = cityRepo.findById(id);
        Optional<Airport> airport = airportRepo.findById(id);
        Optional<Communal> communal = communalRepo.findById(id);
        if (city.isPresent()) {
            return city.get();
        }
        if (airport.isPresent()) {
            return airport.get();
        }
        return communal.orElseThrow();
    }

    @Override
    public Boolean delete(int id) {
        Optional<City> optionalCity = cityRepo.findById(id);
        if (optionalCity.isPresent()) {
            cityRepo.delete(optionalCity.get());
            return TRUE;
        }
        Optional<Communal> optionalCommunal = communalRepo.findById(id);
        if (optionalCommunal.isPresent()) {
            communalRepo.delete(optionalCommunal.get());
            return TRUE;
        }
        Optional<Airport> optionalAirport = airportRepo.findById(id);
        if (optionalAirport.isPresent()) {
            airportRepo.delete(optionalAirport.get());
            return TRUE;
        }
        return FALSE;
    }

//    @Override
//    public int ownershipToDeposit(Ownership ownership) {
//        ownership.setInDeposit(true);
//        return ownershipRepo.findByName(ownership.getName()).getDepositCost();
//    }
//
//    @Override
//    public void changeToAnotherGamer(Ownership ownership, Gamer gamer2) {
//        Gamer gamerOwner = ownership.getGamer();
//        List<Ownership> ownerships = gamerOwner.getOwnership();
//        Optional<Ownership> optionalOwnership = ownerships.stream().filter(own -> own == ownership).findAny();
//        if (optionalOwnership.isPresent()) {
//            gamer2.addOwn(optionalOwnership.get());
//            gamerOwner.getOwnership().remove(optionalOwnership.get());
//            gamerRepo.save(gamerOwner);
//            gamerRepo.save(gamer2);
//        }
//    }
}

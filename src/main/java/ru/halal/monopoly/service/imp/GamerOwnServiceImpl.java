package ru.halal.monopoly.service.imp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.halal.monopoly.domain.ownerships.Airport;
import ru.halal.monopoly.domain.ownerships.City;
import ru.halal.monopoly.domain.ownerships.Communal;
import ru.halal.monopoly.domain.ownerships.Ownership;
import ru.halal.monopoly.repository.AirportRepo;
import ru.halal.monopoly.repository.CityRepo;
import ru.halal.monopoly.repository.CommunalRepo;
import ru.halal.monopoly.repository.GamerRepo;
import ru.halal.monopoly.service.GamerOwnsService;

import javax.transaction.Transactional;

@Service
@Transactional
@Slf4j
public class GamerOwnServiceImpl implements GamerOwnsService {
    //    private final OwnershipRepo ownershipRepo;
    private final CityRepo cityRepo;
    private final CommunalRepo communalRepo;
    private final AirportRepo airportRepo;
    private final GamerRepo gamerRepo;

    @Autowired
    public GamerOwnServiceImpl(CityRepo cityRepo, CommunalRepo communalRepo, AirportRepo airportRepo, GamerRepo gamerRepo) {
        this.cityRepo = cityRepo;
        this.communalRepo = communalRepo;
        this.airportRepo = airportRepo;
        this.gamerRepo = gamerRepo;
    }

    @Override
    public Ownership addOwn(Ownership ownership) {
        switch (ownership.getType()) {
            case CITY -> {
                return cityRepo.save((City) ownership);
            }
            case AIRPORT -> {
                return airportRepo.save((Airport) ownership);
            }
            case COMMUNAL -> {
                return communalRepo.save((Communal) ownership);
            }
            default -> {
                return new Ownership();
            }
        }
    }
}

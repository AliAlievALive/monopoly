package ru.halal.monopoly.service.imp;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.halal.monopoly.domain.Gamer;
import ru.halal.monopoly.domain.ownerships.Airport;
import ru.halal.monopoly.domain.ownerships.Communal;
import ru.halal.monopoly.repository.AirportRepo;
import ru.halal.monopoly.repository.GamerRepo;
import ru.halal.monopoly.service.AirportService;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional
@Slf4j
public class AirportServiceImpl implements AirportService {
    private final AirportRepo airportRepo;
    private final GamerRepo gamerRepo;

    @Override
    public Airport create(Airport airport) {
        return airportRepo.save(airport);
    }

    @Override
    public int airportToDeposit(Airport airport) {
        airport.setInDeposit(true);
        return airportRepo.findByName(airport.getName()).getDepositCost();
    }

    @Override
    public void changeToAnotherGamer(Airport airport, Gamer gamer2) {
        Gamer gamerOwner = airport.getGamer();
        List<Airport> owner1Communal = gamerOwner.getAirports();
        Optional<Airport> airportOpt = owner1Communal.stream().filter(air -> air == airport).findFirst();
        if (airportOpt.isPresent()) {
            gamer2.addAirport(airportOpt.get());
            gamerOwner.getAirports().remove(airportOpt.get());
            gamerRepo.save(gamerOwner);
            gamerRepo.save(gamer2);
        }
    }
}

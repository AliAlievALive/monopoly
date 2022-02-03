package ru.halal.monopoly.service.imp;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.halal.monopoly.domain.Gamer;
import ru.halal.monopoly.domain.ownerships.Airport;
import ru.halal.monopoly.domain.ownerships.Ownership;
import ru.halal.monopoly.repository.AirportRepo;
import ru.halal.monopoly.repository.GamerRepo;
import ru.halal.monopoly.service.AirportService;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static java.lang.Boolean.TRUE;

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
    public Airport update(Airport airport) {
        return airportRepo.saveAndFlush(airport);
    }

    @Override
    public Collection<Airport> getAirportList(int limit) {
        return airportRepo.findAll(PageRequest.of(0, limit)).toList();
    }

    @Override
    public Airport getAirport(int id) {
        Airport airport = null;
        Optional<Airport> airportOptional = airportRepo.findById(id);
        if (airportOptional.isPresent()) {
            airport = airportOptional.get();
        }
        return airport;
    }

    @Override
    public Boolean delete(int id) {
        airportRepo.deleteById(id);
        return TRUE;
    }

    @Override
    public int airportToDeposit(Airport airport) {
        airport.setInDeposit(true);
        return airportRepo.findByName(airport.getName()).getDepositCost();
    }
}

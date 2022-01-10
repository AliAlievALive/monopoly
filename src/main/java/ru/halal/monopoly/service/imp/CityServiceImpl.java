package ru.halal.monopoly.service.imp;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.halal.monopoly.domain.Gamer;
import ru.halal.monopoly.domain.ownerships.City;
import ru.halal.monopoly.repository.CityRepo;
import ru.halal.monopoly.service.CityService;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

@RequiredArgsConstructor
@Service
@Transactional
@Slf4j
public class CityServiceImpl implements CityService {
    private static final int MAX_HOME_COUNT = 5;
    private final CityRepo cityRepo;

    @Override
    public City create(City city) {
        return cityRepo.save(city);
    }

    @Override
    public City update(City city) {
        return cityRepo.saveAndFlush(city);
    }

    @Override
    public int cityToDeposit(int id) {
        int depositMoney = 0;
        Optional<City> optionalCity = cityRepo.findById(id);
        if (optionalCity.isPresent()) {
            City city = optionalCity.get();
            if (!city.isInDeposit()) {
                city.setInDeposit(true);
                depositMoney = city.getDepositCost();
                Gamer gamer = city.getGamer();
                gamer.setMoney(gamer.getMoney() + depositMoney);
                cityRepo.save(city);
            }
        }
        return depositMoney;
    }

    @Override
    public Boolean cityFromDeposit(int id) {
        Optional<City> optionalCity = cityRepo.findById(id);
        if (optionalCity.isPresent()) {
            City city = optionalCity.get();
            Gamer gamer = optionalCity.get().getGamer();
            int gamerMoney = gamer.getMoney();
            int depositCost = city.getDepositCost();
            if (gamerMoney >= depositCost && city.isInDeposit()) {
                gamer.setMoney(gamerMoney - depositCost);
                city.setInDeposit(false);
                cityRepo.save(city);
                return TRUE;
            }
        }
        return FALSE;
    }

    @Override
    public Boolean addHome(int id) {
        Optional<City> optionalCity = cityRepo.findById(id);
        City city = optionalCity.get();
        Gamer gamer = optionalCity.get().getGamer();
        int gamerMoney = gamer.getMoney();
        int homeCost = city.getHomeCost();
        if (gamerMoney >= homeCost && !city.isInDeposit() && city.getHomeCount() < MAX_HOME_COUNT) {
            gamer.setMoney(gamerMoney - homeCost);
            city.setHomeCount(city.getHomeCount() + 1);
            cityRepo.save(city);
            return TRUE;
        }
        return FALSE;
    }

    @Override
    public Boolean soldHome(int id) {
        Optional<City> optionalCity = cityRepo.findById(id);
        City city = optionalCity.get();
        Gamer gamer = optionalCity.get().getGamer();
        int gamerMoney = gamer.getMoney();
        int homeCost = city.getHomeCost();
        if (!city.isInDeposit() && city.getHomeCount() > 0) {
            gamer.setMoney(gamerMoney + homeCost);
            city.setHomeCount(city.getHomeCount() - 1);
            cityRepo.save(city);
            return TRUE;
        }
        return FALSE;
    }

    @Override
    public List<City> getCities(int limit) {
        return cityRepo.findAll(PageRequest.of(0, limit)).toList();
    }

    @Override
    public City getCity(int id) {
        City city = null;
        Optional<City> optionalCity = cityRepo.findById(id);
        if (optionalCity.isPresent()) {
            city = optionalCity.get();
        }
        return city;
    }

    @Override
    public Boolean delete(int id) {
        cityRepo.deleteById(id);
        return TRUE;
    }
}

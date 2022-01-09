package ru.halal.monopoly.service.imp;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.halal.monopoly.domain.Gamer;
import ru.halal.monopoly.domain.ownerships.City;
import ru.halal.monopoly.repository.CityRepo;
import ru.halal.monopoly.repository.GamerRepo;
import ru.halal.monopoly.service.CityService;

import javax.transaction.Transactional;
import java.util.ArrayList;
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
    private final GamerRepo gamerRepo;

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
            city.setInDeposit(true);
            depositMoney = city.getDepositCost();
            cityRepo.save(city);
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
            if (gamerMoney >= depositCost) {
                gamer.setMoney(gamerMoney - depositCost);
                city.setInDeposit(false);
                cityRepo.save(city);
                gamerRepo.save(gamer);
                return TRUE;
            }
        }
        return FALSE;
    }

    @Override
    public void addHome(City city, int money) {
        if (city.getHomeCount() < MAX_HOME_COUNT && money == city.getHomeCost()) {
            city.setHomeCount(city.getHomeCount() + 1);
            cityRepo.save(city);
        }
    }

    @Override
    public int soldHome(City city) {
        city.setHomeCount(city.getHomeCount() - 1);
        return city.getHomeCost();
    }

    @Override
    public void changeToAnotherGamer(City city, Gamer gamer2) {
        Gamer gamerOwner = city.getGamer();
        List<City> owner1City = gamerOwner.getCities();
        Optional<City> foundCityOpt = owner1City.stream().filter(c -> c == city).findFirst();
        if (foundCityOpt.isPresent()) {
            gamer2.addCity(foundCityOpt.get());
            gamerOwner.getCities().remove(foundCityOpt.get());
            gamerRepo.save(gamerOwner);
            gamerRepo.save(gamer2);
        }
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

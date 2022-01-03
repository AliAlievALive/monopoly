package ru.halal.monopoly.service.imp;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.halal.monopoly.domain.Gamer;
import ru.halal.monopoly.domain.ownerships.City;
import ru.halal.monopoly.repository.CityRepo;
import ru.halal.monopoly.repository.GamerRepo;
import ru.halal.monopoly.service.CityService;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

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
    public int cityToDeposit(City city) {
        city.setInDeposit(true);
        return cityRepo.findByName(city.getName()).getDepositCost();
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
}

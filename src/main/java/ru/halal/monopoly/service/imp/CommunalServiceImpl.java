package ru.halal.monopoly.service.imp;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.halal.monopoly.domain.Gamer;
import ru.halal.monopoly.domain.ownerships.Communal;
import ru.halal.monopoly.repository.CommunalRepo;
import ru.halal.monopoly.repository.GamerRepo;
import ru.halal.monopoly.service.CommunalService;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional
@Slf4j
public class CommunalServiceImpl implements CommunalService {
    private final CommunalRepo communalRepo;
    private final GamerRepo gamerRepo;

    @Override
    public Communal create(Communal communal) {
        return communalRepo.save(communal);
    }

    @Override
    public int communalToDeposit(Communal communal) {
        communal.setInDeposit(true);
        return communalRepo.findByName(communal.getName()).getDepositCost();
    }

    @Override
    public void changeToAnotherGamer(Communal communal, Gamer gamer2) {
        Gamer gamerOwner = communal.getGamer();
        List<Communal> owner1Communal = gamerOwner.getCommunal();
        Optional<Communal> commun = owner1Communal.stream().filter(comm -> comm == communal).findFirst();
        if (commun.isPresent()) {
            gamer2.addCommunal(commun.get());
            gamerOwner.getCommunal().remove(commun.get());
            gamerRepo.save(gamerOwner);
            gamerRepo.save(gamer2);
        }
    }
}

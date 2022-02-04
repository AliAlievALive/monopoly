package ru.halal.monopoly.service.imp;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.halal.monopoly.domain.ownerships.Communal;
import ru.halal.monopoly.repository.CommunalRepo;
import ru.halal.monopoly.service.CommunalService;

import javax.transaction.Transactional;
import java.util.Collection;

import static java.lang.Boolean.TRUE;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class CommunalServiceImpl implements CommunalService {
    private final CommunalRepo communalRepo;

    @Override
    public Communal createOrUpdate(Communal communal) {
        return communalRepo.save(communal);
    }

    @Override
    public Communal update(Communal communal) {
        return communalRepo.save(communal);
    }

    @Override
    public Collection<Communal> getCommunalList(int limit) {
        return communalRepo.findAll(PageRequest.of(0, limit)).toList();
    }

    @Override
    public Communal getCommunal(int id) {
        return communalRepo.getById(id);
    }

    @Override
    public Boolean delete(int id) {
        communalRepo.deleteById(id);
        return TRUE;
    }

    @Override
    public int communalToDeposit(Communal communal) {
        communal.setInDeposit(true);
        return communalRepo.findById(communal.getId()).get().getDepositCost();
    }
}



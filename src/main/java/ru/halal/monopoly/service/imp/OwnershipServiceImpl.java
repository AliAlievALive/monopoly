package ru.halal.monopoly.service.imp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.halal.monopoly.domain.Gamer;
import ru.halal.monopoly.domain.ownerships.Ownership;
import ru.halal.monopoly.repository.GamerRepo;
import ru.halal.monopoly.repository.OwnershipRepo;
import ru.halal.monopoly.service.OwnershipService;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static java.lang.Boolean.TRUE;

@Service
@Transactional
@Slf4j
public class OwnershipServiceImpl implements OwnershipService {
    private final OwnershipRepo ownershipRepo;
    private final GamerRepo gamerRepo;

    @Autowired
    public OwnershipServiceImpl(OwnershipRepo ownershipRepo, GamerRepo gamerRepo) {
        this.ownershipRepo = ownershipRepo;
        this.gamerRepo = gamerRepo;
    }

    @Override
    public Ownership create(Ownership ownership) {
        return ownershipRepo.save(ownership);
    }

    @Override
    public Ownership update(Ownership ownership) {
        return ownershipRepo.save(ownership);
    }

    @Override
    public Collection<Ownership> getOwnerships(int limit) {
        return ownershipRepo.findAll(PageRequest.of(0, limit)).toList();
    }

    @Override
    public Ownership getOwnership(int id) {
        Ownership ownership = null;
        Optional<Ownership> optionalOwnership = ownershipRepo.findById(id);
        if (optionalOwnership.isPresent()) {
            ownership = optionalOwnership.get();
        }
        return ownership;
    }

    @Override
    public Boolean delete(int id) {
        ownershipRepo.deleteById(id);
        return TRUE;
    }

    @Override
    public int ownershipToDeposit(Ownership ownership) {
        ownership.setInDeposit(true);
        return ownershipRepo.findByName(ownership.getName()).getDepositCost();
    }

    @Override
    public void changeToAnotherGamer(Ownership ownership, Gamer gamer2) {
        Gamer gamerOwner = ownership.getGamer();
        List<Ownership> ownerships = gamerOwner.getOwnerships();
        Optional<Ownership> optionalOwnership = ownerships.stream().filter(own -> own == ownership).findAny();
        if (optionalOwnership.isPresent()) {
            gamer2.addOwn(optionalOwnership.get());
            gamerOwner.getOwnerships().remove(optionalOwnership.get());
            gamerRepo.save(gamerOwner);
            gamerRepo.save(gamer2);
        }
    }
}

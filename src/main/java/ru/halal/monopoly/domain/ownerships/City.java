package ru.halal.monopoly.domain.ownerships;

import lombok.*;
import ru.halal.monopoly.domain.ownerships.interfaces.CanBuildHome;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class City extends Ownership implements CanBuildHome {
    @Id
    @SequenceGenerator(name = "city_id_sequence", sequenceName = "city_id_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "city_id_sequence")
    private int id;
    private String name;

    private EColors color;
    private int homeCost;
    private int homeCount;
    private int oneCityCost;
    private int twoCityCost;
    private int threeCityCost;
    private int payToStayOnCity = 0;

    public City(Ownership ownership) {
        super(ownership.getId(),
                ownership.getName(),
                ownership.getCost(),
                ownership.getRentCost(),
                ownership.getDepositCost(),
                ownership.isEnableToBuy,
                ownership.isInDeposit,
                ownership.type);
    }

    @Override
    public int addHome() {
        return ++homeCount;
    }

    @Override
    public int takeHome() {
        return --homeCount;
    }

    @Override
    public int increasePay() {
        if (homeCount == 1) {
            return oneCityCost;
        } else if (homeCount == 2) {
            return twoCityCost;
        } else {
            return threeCityCost;
        }
    }

    @Override
    public int decreasePay() {
        return 0;
    }
}

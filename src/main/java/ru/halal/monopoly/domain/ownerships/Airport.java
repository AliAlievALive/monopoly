package ru.halal.monopoly.domain.ownerships;

import lombok.*;
import ru.halal.monopoly.domain.ownerships.interfaces.AirportCostCalc;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Airport extends Ownership implements AirportCostCalc {
    @Id
    @SequenceGenerator(name = "airport_id_sequence", sequenceName = "airport_id_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "airport_id_sequence")
    private int id;
    private String name;
    private int cost = 250;

    public Airport (Ownership ownership) {
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
    public int increasePay() {
        return cost *= 2;
    }

    @Override
    public int decreasePay() {
        return cost /= 2;
    }
}

package ru.halal.monopoly.domain.ownerships;

import lombok.*;
import ru.halal.monopoly.domain.ownerships.interfaces.CommunalCostCalc;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Communal extends Ownership implements CommunalCostCalc {
    @Id
    @SequenceGenerator(name = "communal_id_sequence", sequenceName = "communal_id_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "communal_id_sequence")
    private int id;
    private String name;
    private int multiplier = 4;

    public Communal(Ownership ownership) {
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
        return multiplier = 10;
    }

    @Override
    public int decreasePay() {
        return multiplier = 4;
    }
}

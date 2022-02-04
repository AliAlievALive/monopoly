package ru.halal.monopoly.domain.ownerships;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;
import lombok.experimental.SuperBuilder;
import ru.halal.monopoly.domain.Gamer;
import ru.halal.monopoly.domain.ownerships.interfaces.CanSale;
import ru.halal.monopoly.domain.ownerships.interfaces.CostCalculate;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@SuperBuilder
@JsonDeserialize(as=City.class)
public abstract class Ownership implements CanSale, CostCalculate {
    @Id
    @SequenceGenerator(
            name = "owner_id_sequence",
            sequenceName = "owner_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "owner_id_sequence"
    )
    private int id;
    private String name;
    private int cost;
    private int rentCost;
    private int depositCost;
    private boolean isEnableToBuy;
    private boolean isInDeposit;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.REFRESH, CascadeType.MERGE})
    @JoinColumn(name = "gamer_id")
    @JsonBackReference
    private Gamer gamer;

    @Override
    public int sale() {
        return cost;
    }
}

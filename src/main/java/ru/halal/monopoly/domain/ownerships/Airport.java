package ru.halal.monopoly.domain.ownerships;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import ru.halal.monopoly.domain.Gamer;

import javax.persistence.*;

import java.util.Objects;

import static javax.persistence.GenerationType.AUTO;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@ToString
public class Airport extends Ownership {
    @Id
    @GeneratedValue(strategy = AUTO)
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Airport airport = (Airport) o;
        return id == airport.id && cost == airport.cost && rentCost == airport.rentCost && depositCost == airport.depositCost && Objects.equals(name, airport.name) && Objects.equals(gamer, airport.gamer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, cost, rentCost, depositCost, gamer);
    }

    @Override
    public int sale() {
        return cost;
    }
}

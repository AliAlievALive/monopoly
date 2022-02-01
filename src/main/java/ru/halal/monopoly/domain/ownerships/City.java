package ru.halal.monopoly.domain.ownerships;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import ru.halal.monopoly.domain.Gamer;
import ru.halal.monopoly.domain.ownerships.interfaces.CanBuildHome;

import javax.persistence.*;
import java.util.Objects;

import static javax.persistence.GenerationType.AUTO;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class City extends Ownership implements CanBuildHome {
    @Id
    @GeneratedValue(strategy = AUTO)
    private int id;
    private String name;
    private int cost;
    private EColors color;
    private int rentCost;
    private int depositCost;
    private int homeCost;
    private boolean isEnableToBuy;
    private boolean isInDeposit;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.REFRESH, CascadeType.MERGE})
    @JoinColumn(name = "gamer_id")
    @JsonBackReference
    private Gamer gamer;
    private int homeCount;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        City city = (City) o;
        return id == city.id && cost == city.cost && rentCost == city.rentCost && depositCost == city.depositCost && homeCount == city.homeCount && name.equals(city.name) && color == city.color && Objects.equals(gamer, city.gamer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, cost, color, rentCost, depositCost, gamer, homeCount);
    }

    @Override
    public int sale() {
        return cost;
    }

    @Override
    public int addHome() {
        return ++homeCount;
    }

    @Override
    public int takeHome() {
        return --homeCount;
    }
}

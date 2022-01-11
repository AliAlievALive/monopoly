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
public class Communal extends Ownership {
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
        Communal communal = (Communal) o;
        return id == communal.id && cost == communal.cost && rentCost == communal.rentCost && depositCost == communal.depositCost && Objects.equals(name, communal.name) && Objects.equals(gamer, communal.gamer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, cost, rentCost, depositCost, gamer);
    }
}

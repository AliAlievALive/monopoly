package ru.halal.monopoly.domain;

import lombok.*;
import lombok.experimental.SuperBuilder;
import ru.halal.monopoly.domain.ownerships.GamerOwns;

import javax.persistence.*;
import java.util.Objects;

import static javax.persistence.GenerationType.AUTO;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@SuperBuilder
public class Gamer {
    @Id
    @GeneratedValue(strategy = AUTO)
    private int id;
    private String name;
    private int money;
    @OneToOne(mappedBy = "gamer", cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
    private GamerOwns ownership;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Gamer)) return false;
        Gamer gamer = (Gamer) o;
        return getId() == gamer.getId() && getMoney() == gamer.getMoney() && getName().equals(gamer.getName()) && Objects.equals(this.getOwnership(), gamer.getOwnership());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}

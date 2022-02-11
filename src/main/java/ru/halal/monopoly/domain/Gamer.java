package ru.halal.monopoly.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.Objects;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@SuperBuilder
public class Gamer implements Comparable<Gamer> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private int money;
    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "gamer_owns_id")
    @JsonManagedReference
//    @JsonManagedReference
    private GamerOwns ownership;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Gamer gamer = (Gamer) o;
        return id == gamer.id && money == gamer.money && name.equals(gamer.name) && Objects.equals(ownership, gamer.ownership);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public int compareTo(Gamer gamer) {
        return this.getName().compareTo(gamer.getName());
    }
}

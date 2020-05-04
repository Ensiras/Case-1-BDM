package domain;

import javax.persistence.*;
import java.math.BigDecimal;

import static javax.persistence.DiscriminatorType.STRING;

@Entity
@DiscriminatorColumn(name="soort", discriminatorType = STRING)
public abstract class Artikel {

    @Id @GeneratedValue
    private int id;

    @OneToOne
    private Gebruiker aanbieder;
    private String naam;
    private BigDecimal prijs;

    @Lob
    private String omschrijving;

//    @OneToMany
//    private List<Bijlage> bijlages;

    public Artikel() {
    }

    public Artikel(Gebruiker aanbieder, String naam, BigDecimal prijs) {
        this.aanbieder = aanbieder;
        this.naam = naam;
        this.prijs = prijs;
    }
}

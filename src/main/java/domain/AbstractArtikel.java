package domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

import static javax.persistence.DiscriminatorType.STRING;

@Entity
@DiscriminatorColumn(name="soort", discriminatorType = STRING)
@Table(name = "artikel")
public abstract class AbstractArtikel {

    @Id @GeneratedValue
    private int id;

    @OneToOne
    private Gebruiker aanbieder;
    private String naam;
    private BigDecimal prijs;

    @Lob
    private String omschrijving;

    // TODO: Add bijlagen
//    @OneToMany
//    private List<Bijlage> bijlages;

    // TODO: Add bezorgwijzen
//    @ElementCollection
//    @Enumerated(EnumType.STRING)
//    private Set<Bezorgwijze> bezorgwijzen;

    public AbstractArtikel() {
    }

    public AbstractArtikel(Gebruiker aanbieder, String naam, BigDecimal prijs) {
        this.aanbieder = aanbieder;
        this.naam = naam;
        this.prijs = prijs;
    }
}

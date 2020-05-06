package domain;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import static javax.persistence.DiscriminatorType.STRING;
import static org.hibernate.annotations.CascadeType.PERSIST;

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

    // TODO: bij relaties expliciet toevoegen en ook relatie aan andere kant neerzetten
    @OneToMany(mappedBy = "artikel")
    @Cascade(PERSIST)
    private List<Bijlage> bijlagen = new ArrayList<>();

    @ElementCollection
    @Enumerated(EnumType.STRING)
    private Set<Bezorgwijze> bezorgwijzen = new LinkedHashSet<>();;

    public AbstractArtikel() {
    }

    public AbstractArtikel(Gebruiker aanbieder, String naam, BigDecimal prijs, String omschrijving, List<Bijlage> bijlagen, Set<Bezorgwijze> bezorgwijzen) {
        this.aanbieder = aanbieder;
        this.naam = naam;
        this.prijs = prijs;
        this.omschrijving = omschrijving;
        this.bezorgwijzen = bezorgwijzen;
        addBijlagen(bijlagen);
    }

    // TODO: waarschijnlijk enum toch naar een entity veranderen en dan relaties inbouwen
    public void addBijlagen(List<Bijlage> bijlagen) {
        for (Bijlage bijlage : bijlagen) {
            this.bijlagen.add(bijlage);
            bijlage.setArtikel(this);
        }
    }


}

package domain;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.DiscriminatorType.STRING;
import static org.hibernate.annotations.CascadeType.PERSIST;

@Entity
@DiscriminatorColumn(name = "soort", discriminatorType = STRING)
@Table(name = "artikel")
public abstract class AbstractArtikel {

    @Id
    @GeneratedValue
    private int id;
    private String naam;
    private BigDecimal prijs;

    @OneToOne
    private Gebruiker aanbieder;

    @Lob
    private String omschrijving;

    @OneToMany(mappedBy = "artikel") @Cascade(PERSIST)
    private List<Bijlage> bijlagen = new ArrayList<>();

    public AbstractArtikel() {
    }

    public AbstractArtikel(Gebruiker aanbieder, String naam, BigDecimal prijs, String omschrijving, List<Bijlage> bijlagen) {
        this.aanbieder = aanbieder;
        this.naam = naam;
        this.prijs = prijs;
        this.omschrijving = omschrijving;
        addBijlagen(bijlagen);
    }


    public void addBijlagen(List<Bijlage> bijlagen) {
        if (!(bijlagen == null)) {
            for (Bijlage bijlage : bijlagen) {
                this.bijlagen.add(bijlage);
                bijlage.setArtikel(this);
            }
        }
    }
}

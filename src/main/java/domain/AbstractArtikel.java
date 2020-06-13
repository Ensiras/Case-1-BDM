package domain;

import org.hibernate.annotations.Cascade;
import org.hibernate.validator.constraints.Currency;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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

    @NotNull
    private String naam;

    @NotNull
    private BigDecimal prijs;

    @OneToOne @Cascade(PERSIST) //FIXME: @Cascade should only be temporary because no Gebruikers exist when persisting artikel
    private Gebruiker aanbieder;

    @Lob
    private String omschrijving;

    @OneToMany(mappedBy = "artikel") @Cascade(PERSIST)
    private List<Bijlage> bijlagen = new ArrayList<>();

    public AbstractArtikel() {
    }

    public AbstractArtikel(Gebruiker aanbieder, String naam, BigDecimal prijs) {
        this.aanbieder = aanbieder;
        this.naam = naam;
        this.prijs = prijs;
    }


    public void addBijlagen(List<Bijlage> bijlagen) {
        if (!(bijlagen == null)) {
            for (Bijlage bijlage : bijlagen) {
                this.bijlagen.add(bijlage);
                bijlage.setArtikel(this);
            }
        }
    }

    public void setOmschrijving(String omschrijving) {
        this.omschrijving = omschrijving;
    }

    // Getters needed for JAXB
    public int getId() {
        return id;
    }

    public String getNaam() {
        return naam;
    }

    public BigDecimal getPrijs() {
        return prijs;
    }

    public Gebruiker getAanbieder() {
        return aanbieder;
    }

    public String getOmschrijving() {
        return omschrijving;
    }

    public List<Bijlage> getBijlagen() {
        return bijlagen;
    }

    // Setters needed for mapping
    // If anything goes wrong with DB-stuff check if this setter was necessary.
    /*public void setId(int id) {
        this.id = id;
    }*/

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public void setPrijs(BigDecimal prijs) {
        this.prijs = prijs;
    }

    public void setAanbieder(Gebruiker aanbieder) {
        this.aanbieder = aanbieder;
    }

    public void setBijlagen(List<Bijlage> bijlagen) {
        this.bijlagen = bijlagen;
    }
}

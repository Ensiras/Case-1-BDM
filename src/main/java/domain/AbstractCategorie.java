package domain;

import javax.persistence.*;

import static javax.persistence.DiscriminatorType.*;

@Entity
@DiscriminatorColumn(name = "soort", discriminatorType = STRING)
@Table(name = "categorie")
public abstract class AbstractCategorie {

    @Id @Column(name = "naam", nullable = false, unique = true, columnDefinition = "VARCHAR(64)")
    private String naam;
    @Lob
    private String omschrijving;

    public AbstractCategorie(String naam, String omschrijving) {
        this.naam = naam;
        this.omschrijving = omschrijving;
    }

    public AbstractCategorie() {
    }

    @Override
    public String toString() {
        return naam;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public String getOmschrijving() {
        return omschrijving;
    }

    public void setOmschrijving(String omschrijving) {
        this.omschrijving = omschrijving;
    }
}

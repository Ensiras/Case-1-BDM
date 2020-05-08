package domain;

import javax.persistence.*;

import static javax.persistence.DiscriminatorType.*;

@Entity
@DiscriminatorColumn(name = "soort", discriminatorType = STRING)
@Table(name = "categorie")
public abstract class AbstractCategorie {

    @Id
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
}

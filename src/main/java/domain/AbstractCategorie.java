package domain;

import javax.persistence.*;

import static javax.persistence.DiscriminatorType.*;

@Entity
@DiscriminatorColumn(name = "soort", discriminatorType = STRING)
@Table(name = "categorie")
public abstract class AbstractCategorie {

    @Id
    private String naam; // Kan evt. ook de primary key zijn (moet toch uniek zijn).
    @Lob
    private String omschrijving;

    public AbstractCategorie(String naam, String omschrijving) {
        this.naam = naam;
        this.omschrijving = omschrijving;
    }

    public AbstractCategorie() {
    }

}

package domain;

import javax.persistence.*;

import static javax.persistence.DiscriminatorType.*;

@Entity
@DiscriminatorColumn(name = "Soort", discriminatorType = STRING)
public abstract class Categorie {

    @Id
    private String naam; // Kan evt. ook de primary key zijn (moet toch uniek zijn).
    @Lob
    private String omschrijving;

    public Categorie(String naam, String omschrijving) {
        this.naam = naam;
        this.omschrijving = omschrijving;
    }

    public Categorie() {
    }

}

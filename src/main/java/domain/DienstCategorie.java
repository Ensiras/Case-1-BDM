package domain;

import javax.persistence.Entity;
import javax.persistence.Inheritance;

import static javax.persistence.InheritanceType.SINGLE_TABLE;

@Entity
@Inheritance(strategy = SINGLE_TABLE)
public class DienstCategorie extends Categorie {

    public DienstCategorie(String naam, String omschrijving) {
        super(naam, omschrijving);
    }

    public DienstCategorie() {
    }
}

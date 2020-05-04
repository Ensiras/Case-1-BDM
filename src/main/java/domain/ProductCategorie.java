package domain;

import javax.persistence.Entity;
import javax.persistence.Inheritance;

import static javax.persistence.InheritanceType.SINGLE_TABLE;

@Inheritance(strategy = SINGLE_TABLE)
@Entity
public class ProductCategorie extends Categorie {

    public ProductCategorie(String naam, String omschrijving) {
        super(naam, omschrijving);
    }

    public ProductCategorie() {
    }
}

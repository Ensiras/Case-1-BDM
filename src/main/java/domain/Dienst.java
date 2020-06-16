package domain;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import java.math.BigDecimal;

import static javax.persistence.InheritanceType.SINGLE_TABLE;

@Entity
@Inheritance(strategy = SINGLE_TABLE)
public class Dienst extends AbstractArtikel {

    public Dienst(Gebruiker aanbieder, String naam, BigDecimal prijs) {
        super(aanbieder, naam, prijs);
    }

    public Dienst() {
    }

    public Dienst(String naam, Gebruiker aanbieder, BigDecimal prijs, String categorie, String omschrijving) {
        super(naam, aanbieder, prijs, categorie, omschrijving);
    }
}

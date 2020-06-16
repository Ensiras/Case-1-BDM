package domain;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.math.BigDecimal;


import static javax.persistence.InheritanceType.SINGLE_TABLE;
import static org.hibernate.annotations.CascadeType.PERSIST;

@Entity
@Inheritance(strategy = SINGLE_TABLE)
public class Dienst extends AbstractArtikel {

    // TODO: dit definitief weghalen als categorieprobleem opgelost is
    /* @ManyToOne @JoinColumn(name = "categorie") *//*@Cascade(PERSIST)*//* //@Cascade needed because no categorieën exist yet when persisting
    private DienstCategorie dienstCategorie;*/

    public Dienst(Gebruiker aanbieder, String naam, BigDecimal prijs) {
        super(aanbieder, naam, prijs);
    }

    public Dienst() {
    }

    public Dienst(String naam, Gebruiker aanbieder, BigDecimal prijs, String categorie, String omschrijving) {
        super(naam, aanbieder, prijs, categorie, omschrijving);
    }
}

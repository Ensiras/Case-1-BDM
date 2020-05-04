package domain;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;

import static javax.persistence.InheritanceType.SINGLE_TABLE;

@Entity
@Inheritance(strategy = SINGLE_TABLE)
public class Dienst extends AbstractArtikel {

    @ManyToOne @Cascade(CascadeType.PERSIST)
    private DienstCategorie dienstCategorie;

    public Dienst(Gebruiker aanbieder, String naam, BigDecimal prijs, String soort, DienstCategorie dienstCategorie) {
        super(aanbieder, naam, prijs);
        this.dienstCategorie = dienstCategorie;
    }

    public Dienst() {
    }
}

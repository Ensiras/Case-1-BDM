package domain;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import java.util.List;

import static javax.persistence.InheritanceType.SINGLE_TABLE;

@Entity
@Inheritance(strategy = SINGLE_TABLE)
public class Dienst extends AbstractArtikel {

    @ManyToOne @Cascade(CascadeType.PERSIST)
    private DienstCategorie dienstCategorie;

    public Dienst(Gebruiker aanbieder, String naam, BigDecimal prijs, String omschrijving, List<Bijlage> bijlagen, DienstCategorie dienstCategorie) {
        super(aanbieder, naam, prijs, omschrijving, bijlagen);
        this.dienstCategorie = dienstCategorie;
    }

    public Dienst() {
    }
}
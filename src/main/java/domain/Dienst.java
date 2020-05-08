package domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

import static javax.persistence.InheritanceType.SINGLE_TABLE;

@Entity
@Inheritance(strategy = SINGLE_TABLE)
public class Dienst extends AbstractArtikel {

    @ManyToOne @JoinColumn(name = "categorie") /*@Cascade(CascadeType.PERSIST)*/
    private DienstCategorie dienstCategorie;

    public Dienst(Gebruiker aanbieder, String naam, BigDecimal prijs, DienstCategorie dienstCategorie) {
        super(aanbieder, naam, prijs);
        this.dienstCategorie = dienstCategorie;
    }

    public Dienst() {
    }
}

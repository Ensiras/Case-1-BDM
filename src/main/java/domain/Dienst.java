package domain;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.math.BigDecimal;


import static javax.persistence.InheritanceType.SINGLE_TABLE;
import static org.hibernate.annotations.CascadeType.PERSIST;

@Entity
@Inheritance(strategy = SINGLE_TABLE)
public class Dienst extends AbstractArtikel {

    @ManyToOne @JoinColumn(name = "categorie") /*@Cascade(PERSIST)*/ //@Cascade needed because no categorieën exist yet when persisting
    private DienstCategorie dienstCategorie;

    public Dienst(Gebruiker aanbieder, String naam, BigDecimal prijs, DienstCategorie dienstCategorie) {
        super(aanbieder, naam, prijs);
        this.dienstCategorie = dienstCategorie;
    }

    public Dienst() {
    }

    public void setDienstCategorie(DienstCategorie dienstCategorie) {
        this.dienstCategorie = dienstCategorie;
    }

    public DienstCategorie getDienstCategorie() {
        return dienstCategorie;
    }
}

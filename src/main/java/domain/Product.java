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
public class Product extends AbstractArtikel {

    @ManyToOne @Cascade(CascadeType.PERSIST)
    private ProductCategorie productCategorie;

    public Product() {
    }

    public Product(Gebruiker aanbieder, String naam, BigDecimal prijs, ProductCategorie productCategorie) {
        super(aanbieder, naam, prijs);
        this.productCategorie = productCategorie;
    }
}


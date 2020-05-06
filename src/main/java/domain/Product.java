package domain;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

import static javax.persistence.InheritanceType.SINGLE_TABLE;

@Entity
@Inheritance(strategy = SINGLE_TABLE) // TODO: bekijken of andere strategies wat beter werken, een veld blijft nu steeds leeg
public class Product extends AbstractArtikel {

    @ManyToOne
    private ProductCategorie productCategorie;

    public Product() {
    }

    public Product(Gebruiker aanbieder, String naam, BigDecimal prijs,
                   String omschrijving, List<Bijlage> bijlagen, Set<Bezorgwijze> bezorgwijzen, ProductCategorie productCategorie) {
        super(aanbieder, naam, prijs, omschrijving, bijlagen, bezorgwijzen);
        this.productCategorie = productCategorie;

    }

   /* public Product(Gebruiker aanbieder, String naam, BigDecimal prijs, ProductCategorie productCategorie) {
        super(aanbieder, naam, prijs);
        this.productCategorie = productCategorie;
    }*/
}


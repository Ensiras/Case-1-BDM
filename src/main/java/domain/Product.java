package domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import static javax.persistence.InheritanceType.SINGLE_TABLE;

@Entity
@Inheritance(strategy = SINGLE_TABLE)
public class Product extends AbstractArtikel {

    @ElementCollection @Enumerated(EnumType.STRING) @JoinTable(name = "product_bezorgwijzen")
    private Set<Bezorgwijze> bezorgwijzen = new LinkedHashSet<>();;

    @ManyToOne @JoinColumn(name = "categorie")
    private ProductCategorie productCategorie;

    public Product() {
    }

    public Product(Gebruiker aanbieder, String naam, BigDecimal prijs,
                   String omschrijving, List<Bijlage> bijlagen, Set<Bezorgwijze> bezorgwijzen, ProductCategorie productCategorie) {
        super(aanbieder, naam, prijs, omschrijving, bijlagen);
        this.bezorgwijzen = bezorgwijzen;
        this.productCategorie = productCategorie;

    }

}


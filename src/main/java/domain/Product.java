package domain;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import static javax.persistence.InheritanceType.SINGLE_TABLE;
import static org.hibernate.annotations.CascadeType.*;

@Entity
@Inheritance(strategy = SINGLE_TABLE)
public class Product extends AbstractArtikel {

    @ElementCollection @Enumerated(EnumType.STRING) @JoinTable(name = "product_bezorgwijzen")
    private Set<Bezorgwijze> bezorgwijzen = new LinkedHashSet<>();

    public Product() {
    }

    public Product(Gebruiker aanbieder, String naam, BigDecimal prijs,
                   Set<Bezorgwijze> bezorgwijzen) {
        super(aanbieder, naam, prijs);
        this.bezorgwijzen = bezorgwijzen;
    }

    public Product(String naam, Gebruiker aanbieder, BigDecimal prijs,
                   String categorie, String omschrijving) {
        super(naam, aanbieder, prijs, categorie, omschrijving);
    }

    public void setBezorgwijzen(Set<Bezorgwijze> bezorgwijzen) {
        this.bezorgwijzen = bezorgwijzen;
    }


    public Set<Bezorgwijze> getBezorgwijzen() {
        return bezorgwijzen;
    }


}


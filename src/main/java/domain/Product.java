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
    private Set<Bezorgwijze> bezorgwijzen = new LinkedHashSet<>();;

    @ManyToOne @JoinColumn(name = "categorie") @Cascade(PERSIST) //@Cascade needed because no categorieën exist yet when persisting
    private ProductCategorie productCategorie;

    public Product() {
    }

    public Product(Gebruiker aanbieder, String naam, BigDecimal prijs,
                   Set<Bezorgwijze> bezorgwijzen, ProductCategorie productCategorie) {
        super(aanbieder, naam, prijs);
        this.bezorgwijzen = bezorgwijzen;
        this.productCategorie = productCategorie;

    }

    public void setBezorgwijzen(Set<Bezorgwijze> bezorgwijzen) {
        this.bezorgwijzen = bezorgwijzen;
    }

    public void setProductCategorie(ProductCategorie productCategorie) {
        this.productCategorie = productCategorie;
    }

    public Set<Bezorgwijze> getBezorgwijzen() {
        return bezorgwijzen;
    }

    public ProductCategorie getProductCategorie() {
        return productCategorie;
    }
}


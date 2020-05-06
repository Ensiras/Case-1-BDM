package domain;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import static javax.persistence.EnumType.STRING;
import static javax.persistence.FetchType.EAGER;


@Entity
public class Gebruiker {

    @Id
    @GeneratedValue
    private int id;

    @Email
    private String email;
    private String straat;
    private String huisnummer;
    private String postcode;
    private boolean toestemmingRegelement;

    @ElementCollection(targetClass = Bezorgwijze.class, fetch = EAGER)
    @Enumerated(STRING)
    private Set<Bezorgwijze> bezorgwijzen = new LinkedHashSet<>();

    public Gebruiker(String email, Set<Bezorgwijze> bezorgwijzen, String[] adres, boolean toestemmingRegelement) {
        this.email = email;
        this.bezorgwijzen = bezorgwijzen;
        this.straat = adres[0];
        this.huisnummer = adres[1];
        this.postcode = adres[2];
        this.toestemmingRegelement = toestemmingRegelement;
    }

    public Gebruiker() {
    }

    public String getEmail() {
        return email;
    }

    public String getStraat() {
        return straat;
    }

    public String getHuisnummer() {
        return huisnummer;
    }

    public String getPostcode() {
        return postcode;
    }

    public Set<Bezorgwijze> getBezorgwijzen() {
        return bezorgwijzen;
    }

    public void addBezorgwijze(Bezorgwijze bezorgwijze) {
        bezorgwijzen.add(bezorgwijze);
    }

    @Override
    public String toString() {
        return "Gebruiker{" +
                "email='" + email + '\'' +
                ", adres='" + straat + " " + huisnummer + " " + postcode + '\'' +
                ", bezorgWijzen=" + bezorgwijzen +
                '}';
    }
}

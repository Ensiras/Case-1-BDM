package domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.EnumType.STRING;

//TODO Toestemming naleven regelement

@Entity
public class Gebruiker {

    @Id
    @GeneratedValue
    private int id;
    private String email;
    private String straat;
    private String huisnummer;
    private String postcode;

    @ElementCollection(targetClass = Bezorgwijze.class)
    @Enumerated(STRING)
    private Set<Bezorgwijze> bezorgWijzen = new HashSet<>();

    public Gebruiker(String email, Set<Bezorgwijze> bezorgwijzen, String[] adres) {
        this.email = email;
        this.bezorgWijzen = bezorgwijzen;
        this.straat = adres[0];
        this.huisnummer = adres[1];
        this.postcode = adres[2];
    }

    public Gebruiker(String email, Set<Bezorgwijze> bezorgwijzen) {
        this(email, bezorgwijzen, new String[3]);
        this.email = email;
        this.bezorgWijzen = bezorgwijzen;
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

    public Set<Bezorgwijze> getBezorgWijzen() {
        return bezorgWijzen;
    }

    @Override
    public String toString() {
        return "Gebruiker{" +
                "email='" + email + '\'' +
                ", adres='" + straat + " " + huisnummer + " " + postcode + '\'' +
                ", bezorgWijzen=" + bezorgWijzen +
                '}';
    }
}

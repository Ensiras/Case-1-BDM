package domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

import static domain.Bezorgwijze.AFHALEN_THUIS;
import static javax.persistence.EnumType.STRING;

//TODO Toestemming naleven regelement

@Entity
public class Gebruiker {

    @Id @GeneratedValue
    private int id;
    private String email;
    private String adres;

    @ElementCollection(targetClass = Bezorgwijze.class)
    @Enumerated(STRING)
    private Set<Bezorgwijze> bezorgWijzen = new HashSet<>();

    public Gebruiker(String email, Set<Bezorgwijze> bezorgwijzen, String adres) {
        this.email = email;
        this.bezorgWijzen = bezorgwijzen;
        this.adres = adres;
    }

    public Gebruiker() {
    }

    public String getEmail() {
        return email;
    }


    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    public void add(Bezorgwijze bw) {
        if (bw.equals(AFHALEN_THUIS)) {
            setAdres("Nergens");
        }
        bezorgWijzen.add(bw);
    }

    public Set<Bezorgwijze> getBezorgWijzen() {
        return bezorgWijzen;
    }

    @Override
    public String toString() {
        return "Gebruiker{" +
                "email='" + email + '\'' +
                ", adres='" + adres + '\'' +
                ", bezorgWijzen=" + bezorgWijzen +
                '}';
    }
}

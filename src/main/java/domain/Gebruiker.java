package domain;

import javax.persistence.*;
import javax.validation.constraints.Email;
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
/*    private String straat;
    private String huisnummer;
    private String postcode;*/
    @Embedded
    private Adres adres;
    private boolean akkoordVoorwaarden;

    @ElementCollection(targetClass = Bezorgwijze.class, fetch = EAGER)
    @Enumerated(STRING)
    private Set<Bezorgwijze> bezorgwijzen = new LinkedHashSet<>();

    public Gebruiker(String email, Set<Bezorgwijze> bezorgwijzen, String[] adres, boolean akkoordVoorwaarden) {
        this.email = email;
        this.bezorgwijzen = bezorgwijzen;
        this.akkoordVoorwaarden = akkoordVoorwaarden;
    }

    public Gebruiker(String email) {
        this.email = email;
    }

    public Gebruiker() {
    }

    public void addBezorgwijze(Bezorgwijze bezorgwijze) {
        bezorgwijzen.add(bezorgwijze);
    }

    // Getters and setters needed for mapping.
    public Adres getAdres() {
        return adres;
    }

    public String getEmail() {
        return email;
    }

    public int getId() {
        return id;
    }

    public Set<Bezorgwijze> getBezorgwijzen() {
        return bezorgwijzen;
    }

    public boolean isAkkoordVoorwaarden() {
        return akkoordVoorwaarden;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAdres(Adres adres) {
        this.adres = adres;
    }

    public void setAkkoordVoorwaarden(boolean akkoordVoorwaarden) {
        this.akkoordVoorwaarden = akkoordVoorwaarden;
    }

    public void setBezorgwijzen(Set<Bezorgwijze> bezorgwijzen) {
        this.bezorgwijzen = bezorgwijzen;
    }

    public void setId(int id) {
        this.id = id;
    }
}


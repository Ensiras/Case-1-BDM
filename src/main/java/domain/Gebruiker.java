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
//    @Column(name="id", unique = true, nullable = false, columnDefinition = "int") possible workaround for unable to apply constraints
    private int id;

    @Email
    private String email;
    private String straat;
    private String huisnummer;
    private String postcode;
    private String stad;

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
        this.straat = adres.getStraat();
        this.huisnummer = adres.getHuisnummer();
        this.postcode = adres.getPostcode();
        this.stad = adres.getStad();
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

    public String getStraat() {
        return straat;
    }

    public void setStraat(String straat) {
        this.straat = straat;
    }

    public String getHuisnummer() {
        return huisnummer;
    }

    public void setHuisnummer(String huisnummer) {
        this.huisnummer = huisnummer;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getStad() {
        return stad;
    }

    public void setStad(String stad) {
        this.stad = stad;
    }

    // To string just for testing purposes
    // TODO: get rid of this later
    @Override
    public String toString() {
        return "Gebruiker{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", straat='" + straat + '\'' +
                ", huisnummer='" + huisnummer + '\'' +
                ", postcode='" + postcode + '\'' +
                ", stad='" + stad + '\'' +
                ", akkoordVoorwaarden=" + akkoordVoorwaarden +
                ", bezorgwijzen=" + bezorgwijzen +
                '}';
    }

}


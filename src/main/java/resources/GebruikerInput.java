package resources;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

// Class representing Gebruiker data from backend.
public class GebruikerInput {
    private int id;
    private String email;
    private boolean bezorgAfhalenThuis;
    private boolean bezorgAfhalenMagazijn;
    private boolean bezorgVersturenVooruit;
    private boolean bezorgVersturenRembours;
    private String straat;
    private String huisnummer;
    private String postcode;
    private String stad;
    private boolean akkoordVoorwaarden;

    public GebruikerInput() {
    }

    public int getId() {
        return id;
    }

    // Getters needed for JSON to Java object mapping.
    public String getEmail() {
        return email;
    }

    public String getStraat() {
        return straat;
    }

    public boolean isBezorgAfhalenThuis() {
        return bezorgAfhalenThuis;
    }

    public boolean isBezorgAfhalenMagazijn() {
        return bezorgAfhalenMagazijn;
    }

    public boolean isBezorgVersturenVooruit() {
        return bezorgVersturenVooruit;
    }

    public boolean isBezorgVersturenRembours() {
        return bezorgVersturenRembours;
    }

    public String getHuisnummer() {
        return huisnummer;
    }

    public String getPostcode() {
        return postcode;
    }

    public String getStad() {
        return stad;
    }

    public boolean isAkkoordVoorwaarden() {
        return akkoordVoorwaarden;
    }

    // Setters needed for testing.
    public void setId(int id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setBezorgAfhalenThuis(boolean bezorgAfhalenThuis) {
        this.bezorgAfhalenThuis = bezorgAfhalenThuis;
    }

    public void setBezorgAfhalenMagazijn(boolean bezorgAfhalenMagazijn) {
        this.bezorgAfhalenMagazijn = bezorgAfhalenMagazijn;
    }

    public void setBezorgVersturenVooruit(boolean bezorgVersturenVooruit) {
        this.bezorgVersturenVooruit = bezorgVersturenVooruit;
    }

    public void setBezorgVersturenRembours(boolean bezorgVersturenRembours) {
        this.bezorgVersturenRembours = bezorgVersturenRembours;
    }

    public void setStraat(String straat) {
        this.straat = straat;
    }

    public void setHuisnummer(String huisnummer) {
        this.huisnummer = huisnummer;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public void setStad(String stad) {
        this.stad = stad;
    }

    public void setAkkoordVoorwaarden(boolean akkoordVoorwaarden) {
        this.akkoordVoorwaarden = akkoordVoorwaarden;
    }
}

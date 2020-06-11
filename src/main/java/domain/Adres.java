package domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Adres {
    @Column(name = "straat")
    private String straat;

    @Column(name = "huisnummer")
    private String huisnummer;

    @Column(name = "postcode")
    private String postcode;

    @Column(name = "stad")
    private String stad;

    public Adres() {
    }

    public Adres(String straat, String huisnummer, String postcode, String stad) {
        this.straat = straat;
        this.huisnummer = huisnummer;
        this.postcode = postcode;
        this.stad = stad;
    }

    // Getters and setters needed for testing.
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
}

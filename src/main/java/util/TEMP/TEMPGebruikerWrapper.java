package util.TEMP;

public class TEMPGebruikerWrapper {
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

    public TEMPGebruikerWrapper() {
    }

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
}

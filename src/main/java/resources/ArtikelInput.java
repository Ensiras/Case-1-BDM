package resources;


public class ArtikelInput {
    private String soort;
    private int id;
    private int gebruikerId;
    private String naam;
    private Double prijs;
    private String categorie;
    private String omschrijving;
    private boolean bezorgAfhalenThuis;
    private boolean bezorgAfhalenMagazijn;
    private boolean bezorgVersturenVooruit;
    private boolean bezorgVersturenRembours;
    private String[] bijlagen;

    public ArtikelInput() {
    }

    public String getSoort() {
        return soort;
    }

    public void setSoort(String soort) {
        this.soort = soort;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGebruikerId() {
        return gebruikerId;
    }

    public void setGebruikerId(int gebruikerId) {
        this.gebruikerId = gebruikerId;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public Double getPrijs() {
        return prijs;
    }

    public void setPrijs(Double prijs) {
        this.prijs = prijs;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getOmschrijving() {
        return omschrijving;
    }

    public void setOmschrijving(String omschrijving) {
        this.omschrijving = omschrijving;
    }

    public boolean isBezorgAfhalenThuis() {
        return bezorgAfhalenThuis;
    }

    public void setBezorgAfhalenThuis(boolean bezorgAfhalenThuis) {
        this.bezorgAfhalenThuis = bezorgAfhalenThuis;
    }

    public boolean isBezorgAfhalenMagazijn() {
        return bezorgAfhalenMagazijn;
    }

    public void setBezorgAfhalenMagazijn(boolean bezorgAfhalenMagazijn) {
        this.bezorgAfhalenMagazijn = bezorgAfhalenMagazijn;
    }

    public boolean isBezorgVersturenVooruit() {
        return bezorgVersturenVooruit;
    }

    public void setBezorgVersturenVooruit(boolean bezorgVersturenVooruit) {
        this.bezorgVersturenVooruit = bezorgVersturenVooruit;
    }

    public boolean isBezorgVersturenRembours() {
        return bezorgVersturenRembours;
    }

    public void setBezorgVersturenRembours(boolean bezorgVersturenRembours) {
        this.bezorgVersturenRembours = bezorgVersturenRembours;
    }

    public String[] getBijlagen() {
        return bijlagen;
    }

    public void setBijlagen(String[] bijlagen) {
        this.bijlagen = bijlagen;
    }
}

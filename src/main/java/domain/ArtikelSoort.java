package domain;

public enum ArtikelSoort {
    PRODUCT, DIENST;

    @Override
    public String toString() {
        return name().toLowerCase();
    }
}

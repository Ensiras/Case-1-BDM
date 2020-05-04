package domain;

public enum Bezorgwijze {
    AFHALEN_MAGAZIJN("Afhalen magazijn"),
    AFHALEN_THUIS("Afhalen thuis"),
    VERSTUREN_VOORBET("Versturen (vooruitbetaald)"),
    VERSTUREN_REMBOURS("Versturen onder rembours");

    private String typePrintbaar;

    Bezorgwijze(String typePrintbaar) {
        this.typePrintbaar = typePrintbaar;
    }

    public String getTypePrintbaar() {
        return typePrintbaar;
    }
}

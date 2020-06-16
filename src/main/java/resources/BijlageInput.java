package resources;

import java.io.File;

public class BijlageInput {
    private String bijlageNaam;
    private String bijlageType;
    private String artikelId;
    private File dataIn;

    public BijlageInput(String bijlageNaam, String bijlageType, String artikelId, File dataIn) {
        this.bijlageNaam = bijlageNaam;
        this.bijlageType = bijlageType;
        this.artikelId = artikelId;
        this.dataIn = dataIn;
    }

    public BijlageInput() {
    }

    public String getBijlageNaam() {
        return bijlageNaam;
    }

    public String getBijlageType() {
        return bijlageType;
    }

    public String getArtikelId() {
        return artikelId;
    }

    public File getDataIn() {
        return dataIn;
    }

    public void setBijlageNaam(String bijlageNaam) {
        this.bijlageNaam = bijlageNaam;
    }

    public void setBijlageType(String bijlageType) {
        this.bijlageType = bijlageType;
    }

    public void setArtikelId(String artikelId) {
        this.artikelId = artikelId;
    }

    public void setDataIn(File dataIn) {
        this.dataIn = dataIn;
    }
}

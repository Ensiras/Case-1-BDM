package domain;

import javax.persistence.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static javax.persistence.EnumType.STRING;


@Entity
public class Bijlage {

    @Id
    @GeneratedValue
    private int id;
    private String bestandsNaam;

    private String type;

    @ManyToOne
    private AbstractArtikel artikel;

    @Lob
    private byte[] data;


    public Bijlage() {
    }

    public Bijlage(String bestandsNaam, String type, byte[] data) {
        this.bestandsNaam = bestandsNaam;
        this.type = type;
        this.data = data;
    }

    public void setArtikel(AbstractArtikel artikel) {
        this.artikel = artikel;
    }

    public void setBestandsNaam(String bestandsNaam) {
        this.bestandsNaam = bestandsNaam;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public int getId() {
        return id;
    }

    public String getBestandsNaam() {
        return bestandsNaam;
    }

    public String getType() {
        return type;
    }

    public AbstractArtikel getArtikel() {
        return artikel;
    }

    public byte[] getData() {
        return data;
    }
}

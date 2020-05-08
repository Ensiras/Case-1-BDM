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

    @Enumerated(STRING)
    private BijlageType type;

    @ManyToOne
    private AbstractArtikel artikel;

    @Lob
    private byte[] data;


    public Bijlage() {
    }

    public Bijlage(String bestandsNaam, BijlageType type, byte[] data) {
        this.bestandsNaam = bestandsNaam;
        this.type = type;
        this.data = data;
    }

    public void setArtikel(AbstractArtikel artikel) {
        this.artikel = artikel;
    }

}

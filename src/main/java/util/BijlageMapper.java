package util;

import domain.AbstractArtikel;
import domain.Bijlage;
import resources.BijlageInput;
import service.ArtikelService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

@Stateless
public class BijlageMapper implements Mapper<BijlageInput, Bijlage>{

    @Inject
    ArtikelService artikelService;

    public BijlageMapper() {
    }

    @Override
    public Bijlage mapFromInputToEntity(BijlageInput bijlageInput) {
        Bijlage bijlage = new Bijlage();
        File dataIn = bijlageInput.getDataIn();
        int artikelId = Integer.parseInt(bijlageInput.getArtikelId());
        bijlage.setBestandsNaam(bijlageInput.getBijlageNaam());
        bijlage.setType(bijlageInput.getBijlageType());

        try (FileInputStream stream = new FileInputStream(dataIn)) {
            byte[] dataUit = new byte[(int) dataIn.length()];
            stream.read(dataUit);
            bijlage.setData(dataUit);
        } catch(FileNotFoundException e){
            System.err.println("Bestand niet gevonden door InputStream " + e.getMessage());
        } catch(IOException e){
            System.err.println("Lezen data bijlage mislukt " + e.getMessage());
        }
        AbstractArtikel bijbehorendArtikel = artikelService.zoek(artikelId);
        bijlage.setArtikel(bijbehorendArtikel);
        return bijlage;
    }
}

package service;

import dao.ArtikelDao;
import dao.BijlageDao;
import domain.AbstractArtikel;
import domain.Bijlage;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

@Stateless
public class BijlageService {

    @Inject
    private BijlageDao dao;

    @Inject
    private ArtikelDao artikelDao;

    public Bijlage verwerkNieuweBijlage(File dataIn, String bijlageNaam, String bijlageType, String artikelId) {
        System.out.println("BijlageService verwerkNieuweBijlage met waarde: " + bijlageNaam);
        Bijlage bijlage = new Bijlage();
        bijlage.setBestandsNaam(bijlageNaam);
        bijlage.setType(bijlageType);

        try (FileInputStream stream = new FileInputStream(dataIn)) {
            byte[] data = new byte[(int) dataIn.length()];
            stream.read(data);
            bijlage.setData(data);
            System.out.println("BijlageService verwerkNieuweBijlage bijlagedata gelezen");
        } catch(FileNotFoundException e){
            System.err.println("Bestand niet gevonden door InputStream " + e.getMessage());
        } catch(IOException e){
            System.err.println("Lezen data bijlage mislukt " + e.getMessage());
        }
        AbstractArtikel bijbehorendArtikel = artikelDao.getById(Integer.parseInt(artikelId));
        bijlage.setArtikel(bijbehorendArtikel);
        dao.persist(bijlage);
        System.out.println("BijlageService verwerkNieuweBijlage einde methode");
        return bijlage;
    }

}

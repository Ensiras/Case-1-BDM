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
public class BijlageInputMapper implements InputMapper<BijlageInput, Bijlage> {

    @Inject
    ArtikelService artikelService;

    @Inject
    FileToByteArrayUtil fileUtil;

    public BijlageInputMapper() {
    }

    @Override
    public Bijlage mapFromInputToEntity(BijlageInput bijlageInput) {
        Bijlage bijlage = new Bijlage();

        bijlage.setBestandsNaam(bijlageInput.getBijlageNaam());
        bijlage.setType(bijlageInput.getBijlageType());
        bijlage.setData(fileUtil.readData(bijlageInput.getDataIn()));
        bijlage.setArtikel(setBijbehorendArtikel(bijlageInput));

        return bijlage;
    }

    AbstractArtikel setBijbehorendArtikel(BijlageInput bijlageInput) {
        int artikelId = Integer.parseInt(bijlageInput.getArtikelId());
        return artikelService.zoek(artikelId);
    }

}

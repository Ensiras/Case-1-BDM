package service;

import dao.ArtikelDao;
import domain.AbstractArtikel;
import resources.ArtikelInput;
import util.ArtikelInputMapper;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class ArtikelService {

    @Inject
    ArtikelDao dao;

    @Inject
    ArtikelInputMapper artikelMapper;

    public ArtikelService() {
    }

    public AbstractArtikel aanbiedenArtikel(ArtikelInput artikelInput) {
        AbstractArtikel artikelEntity = artikelMapper.mapArtikelInputToArtikelEntity(artikelInput);
        System.out.println("Did this work? " + artikelInput.getNaam());
        dao.persist(artikelEntity);
        return artikelEntity;
    }
}

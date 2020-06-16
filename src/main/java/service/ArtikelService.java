package service;

import dao.ArtikelDao;
import domain.AbstractArtikel;
import domain.Gebruiker;
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
        dao.persist(artikelEntity);
        return artikelEntity;
    }

    public AbstractArtikel zoek(int id) {
        return dao.find(id, AbstractArtikel.class);
    }
}

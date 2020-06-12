package service;

import dao.ArtikelDao;
import domain.AbstractArtikel;
import domain.Product;
import resources.ArtikelInput;
import util.ArtikelInputMapper;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class ArtikelService {

    @Inject
    ArtikelDao dao;

    @Inject
    ArtikelInputMapper mapper;

    public AbstractArtikel aanbiedenArtikel(ArtikelInput artikelInput) {
        AbstractArtikel artikelInput1 = mapper.mapArtikelInputToArtikelEntity(artikelInput);
        System.out.println("Did this work? " + artikelInput.getNaam());
        /*Product product = mapper.mapArtikelInputToArtikelEntity(artikelInput);
        dao.persist(product);*/
        return null;
    }
}

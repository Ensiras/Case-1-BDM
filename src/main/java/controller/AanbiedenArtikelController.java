package controller;

import dao.ArtikelDao;
import dao.CategorieDao;
import dao.GebruikerDao;
import domain.*;
import util.DBUtil;
import views.AanbiedenArtikelView;

import java.math.BigDecimal;
import java.util.List;

import static domain.ArtikelSoort.DIENST;
import static domain.ArtikelSoort.PRODUCT;

public class AanbiedenArtikelController extends AbstractController<ArtikelDao, AanbiedenArtikelView> {


    public AanbiedenArtikelController(AanbiedenArtikelView view) {
        this.view = view;
        this.dao = new ArtikelDao(DBUtil.getEntityManager());
        view.toonBericht("Nieuw artikel aanbieden");
    }

    public void aanbiedenArtikel() {
        String naam = vraagNaam();
        BigDecimal prijs = vraagPrijs();
        ArtikelSoort artikelSoort = vraagArtikelSoort();
        AbstractCategorie categorie = vraagCategorie(artikelSoort);

        GebruikerDao gebDao = new GebruikerDao(DBUtil.getEntityManager());
        Gebruiker gebruiker = gebDao.zoek(1, Gebruiker.class);

        if (artikelSoort == PRODUCT) {
            ProductCategorie categorie1 = (ProductCategorie) vraagCategorie(PRODUCT);
            new Product(gebruiker, naam, prijs, categorie1);
        }


    }

    // TODO: public naar package zetten
    public AbstractCategorie vraagCategorie(ArtikelSoort soort) {
        view.toonBericht("Kies de subcategorie van uw " + soort + ".");
        CategorieDao catDao = new CategorieDao(DBUtil.getEntityManager());
        List<AbstractCategorie> categorieen = catDao.zoekAlles(soort);
        int[] opties = new int[categorieen.size()];

        // TODO: printen van een lijst (vaker nodig) naar abstractView
        // TODO: fixen twee keer printen en moeten kiezen van lijst
        for (int i = 0; i < categorieen.size(); i++) {
            String naam = categorieen.get(i).getNaam();
            System.out.println("(" + i + ") " + naam);

            // TODO: dit naar aparte methode
            opties[i] = i;
        }

        String input = view.vraagInput();
        // TODO: fixen. 1: overloaded methode, 2: werken met list<?> of opties converteren naar String[]
//        boolean valideInput = checkInput(input, opties)
        // TODO: manier om entitymanager vanaf de dao te sluiten
//        catDao.close();
        return categorieen.get(Integer.parseInt(input));


    }

    // TODO: dit soort methodes misschien in een proppen, anders vrij veel duplicate code
    private ArtikelSoort vraagArtikelSoort() {
        view.toonBericht("Kies de categorie van uw artikel.");
        String[] opties = {"1", "2"};
        String input = "";
        boolean valideInput = false;

        while (!valideInput) {
            input = view.vraagInput("(1) Product of (2) Dienst.");
            valideInput = checkInput(input, opties);
        }
        if (input.equals("1")) {
            return PRODUCT;
        } else {
            return DIENST;
        }
    }

    private BigDecimal vraagPrijs() {
        BigDecimal prijs = null;
        while (prijs == null) {
            String prijsString = view.vraagInput("Geef een prijs op: ");
            prijs = checkPrijsInput(prijsString);
        }
        return prijs;
    }

    private String vraagNaam() {
        String naam = "";
        boolean valideInput = false;
        while (!valideInput) {
            naam = view.vraagInput("Geef een artikelnaam op: ");
            valideInput = checkInputNietLeeg(naam);
        }
        return naam;
    }
}

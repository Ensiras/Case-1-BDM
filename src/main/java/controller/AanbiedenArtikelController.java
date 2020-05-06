package controller;

import dao.ArtikelDao;
import dao.CategorieDao;
import dao.GebruikerDao;
import domain.*;
import util.DBUtil;
import views.AanbiedenArtikelView;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static domain.ArtikelSoort.DIENST;
import static domain.ArtikelSoort.PRODUCT;

public class AanbiedenArtikelController extends AbstractController<ArtikelDao, AanbiedenArtikelView> {

    public AanbiedenArtikelController(AanbiedenArtikelView view) {
        this.view = view;
        this.dao = new ArtikelDao(DBUtil.getEntityManager());
        view.toonBericht("Nieuw artikel aanbieden");
    }

    public void aanbiedenArtikel() {
        String naam = vraagInput("Geef een artikelnaam op: ");
        BigDecimal prijs = vraagPrijs();
        ArtikelSoort artikelSoort = vraagArtikelSoort();
        AbstractCategorie categorie = vraagCategorie(artikelSoort);

        String omschrijving = view.vraagInput("Geef een omschrijving van uw product (optioneel)");

        List<Bijlage> bijlagen = vraagBijlagen();

        GebruikerDao gebDao = new GebruikerDao(DBUtil.getEntityManager());
        Gebruiker gebruiker = gebDao.zoek(1, Gebruiker.class);
        gebDao.sluitEntityManager();

        if (artikelSoort == PRODUCT) {
            ProductCategorie prodCat = (ProductCategorie) categorie;
            Set<Bezorgwijze> bezorgwijzen = vraagBezorgwijzen(gebruiker);
            dao.opslaan(new Product(gebruiker, naam, prijs, omschrijving, bijlagen, bezorgwijzen, prodCat));
        } else {
            System.out.println("Diensten zijn nog niet geïmplementeerd, goedendag!");
        }

    }

    private List<Bijlage> vraagBijlagen() {
        List<Bijlage> bijlagen = new ArrayList<>();
        String input = view.vraagInput("Wilt u bijlagen toevoegen aan uw product (j/n)?");

        if (input.equals("n")) {
            return null;
        } else {
            return toevoegenBijlagen(bijlagen);
        }
    }

    private List<Bijlage> toevoegenBijlagen(List<Bijlage> bijlagen) {
        String input = view.vraagInput("Voer het volledige pad naar het bestand dat u wilt toevoegen in (ja dit is omslachtig, deal with it)");
        bijlagen.add(new Bijlage(input));
        return bijlagen;
    }

    private String vraagOmschrijving() {
        return view.vraagInput("Geef een omschrijving van uw product (optioneel)");
    }

    // TODO: check invoegen dat bezorgwijzen niet leeg kan zijn
    Set<Bezorgwijze> vraagBezorgwijzen(Gebruiker gebruiker) {
        view.toonBericht("Welke bezorgwijzen wilt u ondersteunen voor uw product?");
        Set<Bezorgwijze> bezorgWijzen = gebruiker.getBezorgwijzen();
        String[] opties = {"j", "n"};
        for (Bezorgwijze bezorgwijze : bezorgWijzen) {
            String input = vraagInput(opties, bezorgwijze.getTypePrintbaar());
            if (input.equals("n")) {
                bezorgWijzen.remove(bezorgwijze);
            }
        }
        return bezorgWijzen;
    }

    // TODO: public naar package zetten
    public AbstractCategorie vraagCategorie(ArtikelSoort soort) {
        view.toonBericht("Kies de subcategorie van uw " + soort + ".");

        CategorieDao catDao = new CategorieDao(DBUtil.getEntityManager());
        List<AbstractCategorie> categorieen = catDao.zoekAlles(soort);
        String[] opties = bepaalOpties(categorieen);
        view.toonLijst(categorieen);

        String input = vraagInput(opties);

        catDao.sluitEntityManager();
        return categorieen.get(Integer.parseInt(input));

    }

    private ArtikelSoort vraagArtikelSoort() {

        // TODO: dit stukje evt. nog in een eigen methode zetten
        view.toonBericht("Kies de categorie van uw artikel.");
        String[] opties = {"1", "2"};
        String input = vraagInput(opties, "(1) Product of (2) Dienst.");

        if (input.equals("1")) {
            return PRODUCT;
        } else {
            return DIENST;
        }
    }

    // Zou dit nog als een overloaded methode kunnen?
    private BigDecimal vraagPrijs() {
        BigDecimal prijs = null;
        while (prijs == null) {
            String prijsString = view.vraagInput("Geef een prijs op: ");
            prijs = checkPrijsInput(prijsString);
        }
        return prijs;
    }

    private String vraagNaam() {
        return vraagInput("Geef een artikelnaam op: ");
    }
}

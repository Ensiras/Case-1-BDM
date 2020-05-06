package controller;

import dao.ArtikelDao;
import dao.CategorieDao;
import domain.*;
import util.DBUtil;
import views.AanbiedenArtikelView;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import static domain.ArtikelSoort.DIENST;
import static domain.ArtikelSoort.PRODUCT;
import static util.GebruikerUtil.huidigeGebruiker;
import static util.GebruikerUtil.setHuidigeGebruikerById;

public class AanbiedenArtikelController extends AbstractController<ArtikelDao, AanbiedenArtikelView> {

    public AanbiedenArtikelController(AanbiedenArtikelView view) {
        this.view = view;
        this.dao = new ArtikelDao(DBUtil.getEntityManager());
        setHuidigeGebruikerById(1); // Methode om ingelogde gebruiker te simuleren

        view.toonBericht("Nieuw artikel aanbieden als gebruiker: " + huidigeGebruiker.getEmail());
    }

    public void aanbiedenArtikel() {
        String naam = vraagInput("Geef een artikelnaam op: ");
        BigDecimal prijs = vraagPrijs();
        ArtikelSoort artikelSoort = vraagArtikelSoort();
        AbstractCategorie categorie = vraagCategorie(artikelSoort);

        String omschrijving = view.vraagInput("Geef een omschrijving van uw product (optioneel)");

        List<Bijlage> bijlagen = vraagBijlagen();

        if (artikelSoort == PRODUCT) {
            maakProduct(naam, prijs, categorie, omschrijving, bijlagen);
        } else {
            maakDienst(naam, prijs, categorie, omschrijving, bijlagen);
        }
    }

    void maakDienst(String naam, BigDecimal prijs, AbstractCategorie categorie, String omschrijving, List<Bijlage> bijlagen) {
        DienstCategorie dienstCat = (DienstCategorie) categorie;

        dao.opslaan(new Dienst(huidigeGebruiker, naam, prijs, omschrijving, bijlagen, dienstCat));
        view.toonBericht("Uw dienst is opgeslagen.");
    }

    void maakProduct(String naam, BigDecimal prijs, AbstractCategorie categorie, String omschrijving, List<Bijlage> bijlagen) {
        ProductCategorie prodCat = (ProductCategorie) categorie;
        Set<Bezorgwijze> bezorgwijzen = vraagBezorgwijzen(huidigeGebruiker);

        dao.opslaan(new Product(huidigeGebruiker, naam, prijs, omschrijving, bijlagen, bezorgwijzen, prodCat));
        view.toonBericht("Uw product is opgeslagen.");
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


    Set<Bezorgwijze> vraagBezorgwijzen(Gebruiker gebruiker) {
        view.toonBericht("Welke bezorgwijzen wilt u ondersteunen voor uw product?");
        Set<Bezorgwijze> bezorgWijzenGebr = gebruiker.getBezorgwijzen();
        Set<Bezorgwijze> bezorgWijzenProd = new LinkedHashSet<>();

        String[] opties = {"j", "n"};

        // TODO: Deze check verplaatsen direct na of tijdens keuze product/dienst + exception netter maken
        if (bezorgWijzenGebr.isEmpty()) {
            view.toonBericht("U kunt geen product aanbieden als u geen bezorgwijzen ondersteunt." +
                    "Ga naar uw profiel om daar aan te geven welke bezorgwijzen u ondersteunt" +
                    "Dit is, uiteraard, nog niet geïmplementeerd, vette pech!");
            throw new RuntimeException("Deze fout wordt nog niet helemaal lekker afgehandeld");
        }

        // TODO: misschien naar een aparte methode knallen?
        while(bezorgWijzenProd.isEmpty()) {
            for (Bezorgwijze bezorgwijze : bezorgWijzenGebr) {
                String input = vraagInput(opties, bezorgwijze.getTypePrintbaar());
                if (input.equals("n")) {
                    bezorgWijzenProd.add(bezorgwijze);
                }
            }
            if (bezorgWijzenProd.isEmpty()) {
                view.toonBericht("U moet minimaal een bezorgwijze ondersteunen voor dit product.");
            }
        }

        return bezorgWijzenGebr;
    }

    AbstractCategorie vraagCategorie(ArtikelSoort soort) {
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
        view.toonBericht("Wilt u een product of dienst aanbieden?");
        String[] opties = {"1", "2"};
        String input = vraagInput(opties, "(1) Product (2) Dienst.");

        if (input.equals("1")) {
            return PRODUCT;
        } else {
            return DIENST;
        }
    }

    // TODO: Zou dit nog als een overloaded methode kunnen?
    private BigDecimal vraagPrijs() {
        BigDecimal prijs = null;
        while (prijs == null) {
            String prijsString = view.vraagInput("Geef een prijs op: ");
            prijs = checkPrijsInput(prijsString);
        }
        return prijs;
    }

}

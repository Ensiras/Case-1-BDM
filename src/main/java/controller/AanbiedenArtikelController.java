package controller;

import dao.ArtikelDao;
import dao.CategorieDao;
import domain.*;
import util.DBUtil;
import util.NotImplementedException;
import views.AanbiedenArtikelView;
import views.Hoofdmenu;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import static domain.ArtikelSoort.DIENST;
import static domain.ArtikelSoort.PRODUCT;
import static util.BijlageUtil.ERROR_MESSAGE;
import static util.BijlageUtil.maakBijlage;
import static util.GebruikerUtil.huidigeGebruiker;

public class AanbiedenArtikelController extends AbstractController<ArtikelDao, AanbiedenArtikelView> {

    public AanbiedenArtikelController(AanbiedenArtikelView view) {
        this.view = view;
        this.dao = new ArtikelDao(DBUtil.getEntityManager("MySQL"));
    }

    public void aanbiedenArtikel() {
        ArtikelSoort artikelSoort = vraagArtikelSoort();

        // TODO: zorgen dat aanbieden ook echt afgebroken wordt als deze check niet slaagt
        if (artikelSoort == PRODUCT) {
            checkBezorgwijzen();
        }

        String naam = vraagInput("Geef een artikelnaam op: ");
        BigDecimal prijs = vraagPrijs();
        AbstractCategorie categorie = vraagCategorie(artikelSoort);
        String omschrijving = view.vraagInput("Geef een omschrijving van uw product (optioneel)");
        List<Bijlage> bijlagen = vraagBijlagen();

        // TODO iets met een dao doen hier: of hier maken en dan aan methodes geven of in iedere methode apart maken
        if (artikelSoort == PRODUCT) {
            maakProduct(naam, prijs, categorie, omschrijving, bijlagen);
        } else {
            maakDienst(naam, prijs, categorie, omschrijving, bijlagen);
        }
    }

    private void checkBezorgwijzen() {
        if (huidigeGebruiker.getBezorgwijzen().isEmpty()) {
            view.toonBericht("U kunt geen producten aanbieden als u geen bezorgwijzen ondersteunt.");
            String[] opties = {"1", "2"};
            String input = vraagInput(opties, "U kunt (1) terug naar het hoofdmenu of (2) uw bezorgwijzen aanpassen (niet geïmplementeerd)");

            // TODO: zorgen dat de methode aanbieden product ook echt afloopt
            if (input.equals("1")) {
                Hoofdmenu.toon();
            } else {
                throw new NotImplementedException("Deze functie is nog niet geïmplementeerd!");
            }
        }
    }

    private void maakDienst(String naam, BigDecimal prijs, AbstractCategorie categorie, String omschrijving, List<Bijlage> bijlagen) {
        DienstCategorie dienstCat = (DienstCategorie) categorie;

        dao.opslaan(new Dienst(huidigeGebruiker, naam, prijs, omschrijving, bijlagen, dienstCat));
        view.toonBericht("Uw dienst is opgeslagen.");
    }

    private void maakProduct(String naam, BigDecimal prijs, AbstractCategorie categorie, String omschrijving, List<Bijlage> bijlagen) {
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
        while (bijlagen.size() < 3) {
            Bijlage bijlage = toevoegenBijlage();

            // Als toevoegen van een bijlage wordt afgebroken, stop dan het hele proces en return al bestaande bijlagen
            if (bijlage == null) {
                return bijlagen;
            } else {
                bijlagen.add(bijlage);
                String input = view.vraagInput("Bijlage toegevoegd. U heeft " + bijlagen.size() + " bijlage(n) toegevoegd aan uw artikel." +
                        " Wilt u nog een bijlage toevoegen (j/n)?");
                if (input.equals("n")) {
                    return bijlagen; // Als gebruiker geen bijlagen meer wilt toevoegen, dan
                }
            }
        }
        view.toonBericht("U heeft het maximale aantal bijlagen toegevoegd aan uw artikel.");
        return bijlagen;
    }

    Bijlage toevoegenBijlage() {
        Bijlage bijlage = null;
        String input = view.vraagInput("Voer het volledige pad naar het bestand dat u wilt toevoegen in. " +
                "Maximale grootte: 10mb");

        // Zolang bijlage null is, blijf vragen tenzij gebruiker 'n' invoert.
        while (bijlage == null) {
            bijlage = maakBijlage(input);
            if (bijlage == null) {
                input = view.vraagInput(ERROR_MESSAGE + " Bijlage niet toegevoegd. Probeert u het nog eens" +
                        " of (n) voeg geen bijlage toe en ga door met het aanbieden van uw product");
                if (input.equals("n")) {
                    return null;
                }
            }
        }
        return bijlage;
    }


    Set<Bezorgwijze> vraagBezorgwijzen(Gebruiker gebruiker) {
        view.toonBericht("Welke bezorgwijzen wilt u ondersteunen voor uw product (j/n)?");
        Set<Bezorgwijze> bezorgWijzenGebr = gebruiker.getBezorgwijzen();
        Set<Bezorgwijze> bezorgWijzenProd = new LinkedHashSet<>();
        String[] opties = {"j", "n"};

        while (bezorgWijzenProd.isEmpty()) {
            for (Bezorgwijze bezorgwijze : bezorgWijzenGebr) {
                String input = vraagInput(opties, bezorgwijze.getTypePrintbaar());
                if (input.equals("j")) {
                    bezorgWijzenProd.add(bezorgwijze);
                }
            }
            if (bezorgWijzenProd.isEmpty()) {
                view.toonBericht("U moet minimaal een bezorgwijze ondersteunen voor dit product.");
            }
        }

        return bezorgWijzenProd;
    }

    AbstractCategorie vraagCategorie(ArtikelSoort soort) {
        view.toonBericht("Kies de subcategorie van uw " + soort + ".");

        CategorieDao catDao = new CategorieDao(DBUtil.getEntityManager("MySQL"));
        List<AbstractCategorie> categorieen = catDao.zoekAlles(soort);
        String[] opties = bepaalOpties(categorieen);

        view.toonLijst(categorieen);
        String input = vraagInput(opties);

        return categorieen.get(Integer.parseInt(input));

    }

    ArtikelSoort vraagArtikelSoort() {
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

    BigDecimal vraagPrijs() {
        BigDecimal prijs = null;
        while (prijs == null) {
            String prijsString = view.vraagInput("Geef een prijs op: ");
            prijs = checkPrijsInput(prijsString);
        }
        return prijs;
    }

}

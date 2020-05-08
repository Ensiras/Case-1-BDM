package controller;

import dao.ArtikelDao;
import dao.CategorieDao;
import domain.*;
import util.EntityManagerWrapper;
import util.NotImplementedException;
import views.AanbiedenArtikelView;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import static domain.ArtikelSoort.DIENST;
import static domain.ArtikelSoort.PRODUCT;
import static util.BijlageUtil.*;
import static util.GebruikerUtil.huidigeGebruiker;

public class AanbiedenArtikelController extends AbstractController<AanbiedenArtikelView> {

    public AanbiedenArtikelController(AanbiedenArtikelView view) {
        super(view);
    }

    public boolean aanbiedenArtikel() {
        ArtikelSoort artikelSoort;
        if((artikelSoort = vraagArtikelSoort()) == null) {
            return false; // Afbreken aanbieden product als gebruiker geen bezorgwijzen ondersteunt
        }

        String naam = vraagInputNietLeeg("Geef de naam van uw " + artikelSoort + " op: ");
        BigDecimal prijs = vraagPrijs();
        AbstractCategorie categorie = vraagCategorie(artikelSoort);
        String omschrijving = view.vraagInput("Geef een omschrijving van uw product (optioneel)");
        List<Bijlage> bijlagen = vraagBijlagen();

        ArtikelDao dao = new ArtikelDao(EntityManagerWrapper.getEntityManager("MySQL"));
        if (artikelSoort == PRODUCT) {
            maakProduct(naam, prijs, categorie, omschrijving, bijlagen, dao);
        } else {
            maakDienst(naam, prijs, categorie, omschrijving, bijlagen, dao);
        }

        return true;
    }

    ArtikelSoort vraagArtikelSoort() {
        view.toonBericht("Wilt u een product of dienst aanbieden?");
        String input = vraagInput("(1) Product (2) Dienst.");
        if (input.equals("1")) {
            if(!checkBezorgwijzen()) {
                    return null;
                }
            return PRODUCT;
        } else {
            return DIENST;
        }
    }

    private boolean checkBezorgwijzen() {
        if (huidigeGebruiker.getBezorgwijzen().isEmpty()) {
            view.toonBericht("U kunt geen producten aanbieden als u geen bezorgwijzen ondersteunt.");
            String input = vraagInput("U kunt (1) terug naar het hoofdmenu of (2) uw bezorgwijzen aanpassen (niet geïmplementeerd)");

            if (input.equals("1")) {
                return false;
            } else {
                throw new NotImplementedException("Deze functie is nog niet geïmplementeerd!");
            }
        }
        return true;
    }

    BigDecimal vraagPrijs() {
        BigDecimal prijs = null;
        while (prijs == null) {
            String prijsString = view.vraagInput("Geef een prijs op: ");
            prijs = checkPrijsInput(prijsString);
        }
        return prijs;
    }

    AbstractCategorie vraagCategorie(ArtikelSoort soort) {
        view.toonBericht("Kies de subcategorie van uw " + soort + ".");

        CategorieDao catDao = new CategorieDao(EntityManagerWrapper.getEntityManager("MySQL"));
        List<AbstractCategorie> categorieen = catDao.zoekAlles(soort);
        String[] opties = bepaalOpties(categorieen);

        view.toonLijst(categorieen);
        String input = vraagInput(opties);

        return categorieen.get(Integer.parseInt(input));

    }

    List<Bijlage> vraagBijlagen() {
        List<Bijlage> bijlagen = new ArrayList<>();
        String input = vraagInput("Wilt u bijlagen toevoegen aan uw product? (1) Ja, (2) Nee");
        if (input.equals("2")) {
            return null;
        } else {
            return toevoegenBijlagen();
        }
    }

    List<Bijlage> toevoegenBijlagen() {
        List<Bijlage> bijlagen = new ArrayList<>();
        while (bijlagen.size() < 3) {
            Bijlage bijlage = toevoegenBijlage();

            // Als toevoegen van een bijlage wordt afgebroken, stop dan het hele proces en return al bestaande bijlagen
            if (bijlage == null) {
                return bijlagen;
            } else {
                bijlagen.add(bijlage);
                String input = vraagInputNietLeeg("Bijlage toegevoegd. U heeft " + bijlagen.size() + " bijlage(n) toegevoegd aan uw artikel." +
                        " (1) Nog een bijlage toevoegen, (2) Door met aanbieden. ");
                if (input.equals("2")) {
                    return bijlagen;
                }
            }
        }
        view.toonBericht("U heeft het maximale aantal bijlagen toegevoegd aan uw artikel.");
        return bijlagen;
    }

    Bijlage toevoegenBijlage() {
        Bijlage bijlage = null;
        String input = vraagInputNietLeeg("Voer het volledige pad naar het bestand dat u wilt toevoegen in. " +
                "Maximale grootte: 10MB");

        // Zolang bijlage null is, blijf vragen tenzij gebruiker 'n' invoert.
        while (bijlage == null) {
            bijlage = maakBijlage(input);
            if (bijlage == null) {
                input = vraagInputNietLeeg(getErrorMessage() + " Bijlage niet toegevoegd. Probeert u het nog eens" +
                        " of (1) voeg geen bijlage toe en ga door met het aanbieden van uw product");
                if (input.equals("1")) {
                    return null;
                }
            }
        }
        return bijlage;
    }

    Set<Bezorgwijze> vraagBezorgwijzen(Gebruiker gebruiker) {
        view.toonBericht("Welke bezorgwijzen wilt u ondersteunen voor uw product?");
        Set<Bezorgwijze> bezorgWijzenGebr = gebruiker.getBezorgwijzen();
        Set<Bezorgwijze> bezorgWijzenProd = new LinkedHashSet<>();

        while (bezorgWijzenProd.isEmpty()) {
            for (Bezorgwijze bezorgwijze : bezorgWijzenGebr) {
                String input = vraagInput(bezorgwijze.getTypePrintbaar() +
                        ": (1) Ondesteunen, (2) Niet ondersteunen.");
                if (input.equals("1")) {
                    bezorgWijzenProd.add(bezorgwijze);
                }
            }
            if (bezorgWijzenProd.isEmpty()) {
                view.toonBericht("U moet minimaal een bezorgwijze ondersteunen voor dit product.");
            }
        }

        return bezorgWijzenProd;
    }

    private void maakDienst(String naam, BigDecimal prijs, AbstractCategorie categorie,
                            String omschrijving, List<Bijlage> bijlagen, ArtikelDao dao) {

        DienstCategorie dienstCat = (DienstCategorie) categorie;
        dao.opslaan(new Dienst(huidigeGebruiker, naam, prijs, omschrijving, bijlagen, dienstCat));
        dao.sluitEntityManager();
        view.toonBericht("Uw dienst is opgeslagen.");
    }

    private void maakProduct(String naam, BigDecimal prijs, AbstractCategorie categorie,
                             String omschrijving, List<Bijlage> bijlagen, ArtikelDao dao) {

        ProductCategorie prodCat = (ProductCategorie) categorie;
        Set<Bezorgwijze> bezorgwijzen = vraagBezorgwijzen(huidigeGebruiker);
        dao.opslaan(new Product(huidigeGebruiker, naam, prijs, omschrijving, bijlagen, bezorgwijzen, prodCat));
        dao.sluitEntityManager();
        view.toonBericht("Uw product is opgeslagen.");
    }


}

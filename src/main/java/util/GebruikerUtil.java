package util;

import dao.GebruikerDao;
import domain.Gebruiker;

public class GebruikerUtil {

    public static Gebruiker huidigeGebruiker;

    private GebruikerUtil() {};

    // Sluiten nodig omdat anders de collection bezorgwijzen in twee sessies openstaat, dit gaat mis bij het persisten
    // van een nieuw artikel.
    public static void setHuidigeGebruikerById(int id) {
        GebruikerDao gebDao = new GebruikerDao();
        huidigeGebruiker = gebDao.find(1, Gebruiker.class);
//        gebDao.sluitEntityManager();
    }


}

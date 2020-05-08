package domain;

public class Regelement {

    private final static String REGELEMENT_TITEL = "Gebruikersregelement BDM";

    private final static String[] REGELEMENT_TEKST =
            {"Gebruikers van Belastingdienst Markt dienen zich aan de volgende regels te houden:\n",
                    "1. Verkoop alleen uw eigen bezit, niet dat van anderen.\n",
                    "2. Biedt alleen diensten aan die u ook daadwerkelijk uit kunt voeren.\n",
                    "3. Etc. etc..\n",
                    "Ook geeft u toestemming voor het verwerken van uw gegevens op welke manier dan ook.\n",
                    "Stem nou maar gewoon in, niemand leest deze dingen toch helemaal door."};

    private final static String REGELEMENT_VOET = "(j) Ja, ik stem in met dit regelement, " +
            "(n) Nee, ik stem niet in met dit regelement (breek registratie af).";

    public static void toon() {
        System.out.println(REGELEMENT_TITEL);
        for (String regel : REGELEMENT_TEKST) {
            System.out.println(regel);
        }
    }

    public static String getRegelementVoet() {
        return REGELEMENT_VOET;
    }
}


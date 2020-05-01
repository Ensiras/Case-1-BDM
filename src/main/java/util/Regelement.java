package util;

public class Regelement {

    private final static String REGELEMENT_TITEL = "Gebruikersregelement BDM";

    private final static String[] REGELEMENT_TEKST =
            {"Gebruikers van Belastingdienst Markt dienen zich aan de volgende regels te houden: ",
                    "1. Verkoop alleen uw eigen bezit, niet dat van anderen.",
                    "2. Biedt alleen diensten aan die u ook daadwerkelijk uit kunt voeren.",
                    "3. Het verkopen van BWD's en/of PSU's is ten strengste verboden.",
                    "4. Bla, bla, zo gaat het nog wel een tijdje door.",
                    "Ook geeft u toestemming voor het verwerken van uw gegevens op welke manier dan ook.",
                    "Stem nou maar gewoon in, niemand leest deze dingen helemaal door."};

    private final static String REGELEMENT_VOET = "(1) Ja, ik stem in met dit regelement, " +
            "(2) Nee, ik stem niet in met dit regelement.)";

    public static void toonRegelement(boolean toestemming) {
        System.out.println(REGELEMENT_TITEL);
        for (String regel : REGELEMENT_TEKST) {
            System.out.println(regel);
        }
        if (toestemming = true) {
            System.out.println(REGELEMENT_VOET);
        }
    }

}


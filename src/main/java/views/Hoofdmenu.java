package views;

import java.util.Scanner;

public class Hoofdmenu extends AbstractView {

    private static Hoofdmenu instance;

    public static Hoofdmenu getInstance() {
        if(instance == null) {
            instance = new Hoofdmenu();
        }
        return instance;
    }

    private Hoofdmenu() {};

    public void toon() {
        System.out.println("Welkom bij BDM!");
        Scanner scanner = new Scanner(System.in);

        while(true) {
            System.out.println("Maak een keuze");
            System.out.println("1) Registreren");
            System.out.println("2) Artikel aanbieden");
            System.out.println("3) Stoppen");

            int keuze = Integer.parseInt(scanner.nextLine());
            switch (keuze) {
                case 1:
                    new RegistrerenGebruikerView().toon();
                    break;
                case 2:
                    new AanbiedenArtikelView().toon();
                    break;
                case 3:
                    System.out.println("Tot ziens!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Input niet ondersteund, probeer het nog eens!");
            }
        }
    }
}

package views;

import java.util.Scanner;

public class Hoofdmenu {

    public static void toon() {
        System.out.println("Welkom bij BDM!");

        while(true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Maak een keuze");
            System.out.println("1) Registreren");
            System.out.println("2) Artikelen aanbieden");
            System.out.println("3) Haal mij hier vandaan!");

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

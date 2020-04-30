import views.RegGebruikerView;

import java.util.Scanner;

public class MainApp {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Maak een keuze");
        System.out.println("1) Registreren");
        System.out.println("2) Haal mij hier vandaan!");

        int keuze = Integer.parseInt(scanner.nextLine());
        switch(keuze) {
            case 1:
                RegGebruikerView.show();
                break;
            default:
                System.out.println("Actie niet herkend of ondersteund, jammer!");
        }

    }
}

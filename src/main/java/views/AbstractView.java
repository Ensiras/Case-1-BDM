package views;

import java.util.List;
import java.util.Scanner;

public abstract class AbstractView {

    private final Scanner scanner;

    public AbstractView() {
        this.scanner = new Scanner(System.in);
    }

    public abstract void toon();

    public void toonBericht(String bericht) {
        System.out.println(bericht);
    }

    public String vraagInput(String bericht) {
        System.out.println(bericht);
        return scanner.nextLine().trim().toLowerCase();
    }

    public String vraagInput() {
        return scanner.nextLine().trim().toLowerCase();
    }

    public void sluitScanner() {
        scanner.close();
    }

    public void toonLijst(List<?> lijst) {
        for (int i = 0; i < lijst.size(); i++) {
            System.out.println("(" + i + ") " + lijst.get(i));
        }
    }

}

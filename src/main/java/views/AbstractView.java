package views;

import java.util.Scanner;

public abstract class AbstractView {

    private final Scanner scanner;

    public AbstractView() {
        this.scanner = new Scanner(System.in);
    }

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

}

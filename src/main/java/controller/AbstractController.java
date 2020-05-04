package controller;

public abstract class AbstractController {

    boolean checkInput(String input, String[] opties) {
        for (String optie : opties) {
            if (optie.equals(input)) {
                return true;
            }
        }
        System.out.println("Input: " + input + " werd niet herkend.");
        return false;
    }
}

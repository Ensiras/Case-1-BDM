package controller;

import dao.AbstractDao;
import views.AbstractView;

import java.math.BigDecimal;
import java.util.List;


// TODO: somehow make sure that the right dao is put into the right controller
public abstract class AbstractController<T extends AbstractDao<?>, Y extends AbstractView> {

    protected T dao;
    protected Y view;

    boolean checkInput(String input, String[] opties) {
        for (String optie : opties) {
            if (optie.equals(input)) {
                return true;
            }
        }
        System.out.println("Input: " + input + " werd niet herkend.");
        return false;
    }

    boolean checkInputNietLeeg(String input) {
        if (input.isEmpty()) {
            System.out.println("U heeft niets ingevoerd, voert u a.u.b. een waarde in.");
            return false;
        } else {
            return true;
        }
    }

    BigDecimal checkPrijsInput(String prijsInput) {
        if (!checkInputNietLeeg(prijsInput)) {
            return null;
        }

        return converteerPrijs(prijsInput);
    }

    protected BigDecimal converteerPrijs(String prijsInput) {
        prijsInput = prijsInput.replace(",", ".");
        BigDecimal prijs;
        try {
            prijs = new BigDecimal(prijsInput);
            return prijs;
        } catch (NumberFormatException e) {
            System.err.println("De ingevoerde waarde kon niet geconverteerd worden, probeer het nog eens.");
            return null;
        }
    }

    // TODO: dit stukje code implementeren in de rest van de methodes
    String[] bepaalOpties(List<?> lijst) {
        String[] opties = new String[lijst.size()];
        for (int i = 0; i < lijst.size(); i++) {
            opties[i] = Integer.toString(i);
        }
        return opties;
    }

    // TODO: dit stukje code implementeren in de rest van de methodes
    String vraagInput(String[] opties) {
        boolean valideInput = false;
        String input = "";
        while(!valideInput) {
            input = view.vraagInput();
            valideInput = checkInput(input, opties);
        }
        return input;
    }

    String vraagInput(String[] opties, String bericht) {
        boolean valideInput = false;
        String input = "";
        view.toonBericht(bericht);

        while(!valideInput) {
            input = view.vraagInput();
            valideInput = checkInput(input, opties);
        }
        return input;
    }

    String vraagInput(String bericht) {
        boolean valideInput = false;
        String input = "";

        System.out.println(bericht);
        while(!valideInput) {
            input = view.vraagInput();
            valideInput = checkInputNietLeeg(input);
        }
        return input;
    }
}

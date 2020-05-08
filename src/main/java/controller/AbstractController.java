package controller;

import dao.AbstractDao;


import views.AbstractView;

import java.math.BigDecimal;

import java.util.List;


import static java.math.BigDecimal.ZERO;


public abstract class AbstractController<Y extends AbstractView> {

    protected Y view;
    private final String[] STANDAARD_INPUT_OPTIES = {"1", "2"};

    public AbstractController(Y view) {
        this.view = view;
    }

    String vraagInput(String bericht, String[] opties) {
        boolean valideInput = false;
        String input = "";
        while (!valideInput) {
            input = view.vraagInput(bericht);
            valideInput = checkInput(input, opties);
        }
        return input;
    }

    String vraagInput(String bericht) {
        return vraagInput(bericht, STANDAARD_INPUT_OPTIES);
    }

    String vraagInput(String[] opties) {
        return vraagInput("", opties);
    }

    String vraagInputNietLeeg(String bericht) {
        boolean valideInput = false;
        String input = "";

        view.toonBericht(bericht);
        while (!valideInput) {
            input = view.vraagInput();
            valideInput = checkInputNietLeeg(input);
        }
        return input;
    }

    boolean checkInput(String input, String[] opties) {
        for (String optie : opties) {
            if (optie.equals(input.toLowerCase())) {
                return true;
            }
        }
        view.toonBericht("Input: " + input + " werd niet herkend.");
        return false;
    }

    boolean checkInputNietLeeg(String input) {
        if (input.isEmpty()) {
            view.toonBericht("U heeft niets ingevoerd, voert u a.u.b. een waarde in.");
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
            if (prijs.compareTo(ZERO) > 0) {
                return prijs;
            } else {
                return null;
            }
        } catch (NumberFormatException e) {
            view.toonBericht("De ingevoerde waarde kon niet geconverteerd worden, probeer het nog eens.");
            return null;
        }
    }

    String[] bepaalOpties(List<?> lijst) {
        String[] opties = new String[lijst.size()];
        for (int i = 0; i < lijst.size(); i++) {
            opties[i] = Integer.toString(i);
        }
        return opties;
    }


}

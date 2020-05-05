package controller;

import dao.AbstractDao;
import views.AbstractView;

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
}

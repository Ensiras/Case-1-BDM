package views;

import controller.AanbiedenArtikelController;

import java.util.Scanner;

public class AanbiedenArtikelView extends AbstractView {


    public AanbiedenArtikelView() {
        super();
    }

    public void aanbiedenArtikel() {
        new AanbiedenArtikelController(this).aanbiedenArtikel();
    }




}

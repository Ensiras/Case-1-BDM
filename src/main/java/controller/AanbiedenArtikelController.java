package controller;

import dao.ArtikelDao;
import util.DBUtil;
import views.AanbiedenArtikelView;

public class AanbiedenArtikelController {

    AanbiedenArtikelView view;
    ArtikelDao dao;

    public AanbiedenArtikelController(AanbiedenArtikelView view) {
        this.view = view;
        this.dao = new ArtikelDao(DBUtil.getEntityManager());
    }

    public void aanbiedenArtikel() {

    }
}

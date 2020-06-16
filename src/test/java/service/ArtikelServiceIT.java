package service;

import apps.App;
import dao.AbstractDao;
import dao.GebruikerDao;
import domain.AbstractArtikel;
import domain.Gebruiker;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import resources.ArtikelInput;
import resources.GebruikerInput;
import testUtil.ArquillianBase;
import util.ArtikelInputMapper;
import util.GebruikerInputMapper;

import javax.inject.Inject;
import java.io.File;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(Arquillian.class)
public class ArtikelServiceIT {

    @Inject
    ArtikelService service;

    @Inject
    GebruikerDao dao;

    @Deployment
    public static Archive<?> createDeployment() {
        WebArchive webArchive = ArquillianBase.createDeploymentBase();
        webArchive.addClass(App.class);
        webArchive.addClass(ArtikelService.class);
        webArchive.addClass(GebruikerService.class);
        webArchive.addPackage(ArtikelInput.class.getPackage());
        webArchive.addPackage(AbstractArtikel.class.getPackage());
        webArchive.addClass(ArtikelInputMapper.class);
        webArchive.addClass(GebruikerInputMapper.class);
        webArchive.addPackage(AbstractDao.class.getPackage());
        System.out.println(webArchive.toString(true));
        return webArchive;
    }


    @Test
    public void whenArtikelInputIsGivenShouldBeMappedToArtikelEntityAndPersisted() {
        ArtikelInput artikelIn = new ArtikelInput();
        artikelIn.setGebruikerId(1);
        artikelIn.setNaam("Een boek");
        artikelIn.setSoort("Product");
        artikelIn.setPrijs(34.34);
        artikelIn.setCategorie("Overige");
        artikelIn.setOmschrijving("Een omschrijving");
        artikelIn.setBezorgAfhalenMagazijn(true);
        artikelIn.setBezorgAfhalenThuis(true);
        artikelIn.setBezorgVersturenVooruit(false);
        artikelIn.setBezorgVersturenRembours(false);

        AbstractArtikel abstractArtikel = service.aanbiedenArtikel(artikelIn);

        assertThat(abstractArtikel.getId()).isNotNull();
    }
}

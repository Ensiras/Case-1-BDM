package service;

import apps.App;
import dao.AbstractDao;
import domain.AbstractArtikel;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.Test;
import org.junit.runner.RunWith;
import resources.ArtikelInput;
import util.ArtikelInputMapper;

import javax.inject.Inject;
import java.io.File;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(Arquillian.class)
public class ArtikelServiceIT {

    @Inject
    ArtikelService service;

    @Deployment
    public static Archive<?> createDeployment() {
        WebArchive webArchive = ShrinkWrap.create(WebArchive.class, "test.war")
                .addClass(App.class)
                .addClass(ArtikelService.class)
                .addPackage(ArtikelInput.class.getPackage())
                .addPackage(AbstractArtikel.class.getPackage())
                .addClass(ArtikelInputMapper.class)
                .addPackage(AbstractDao.class.getPackage())
                .addAsResource("persistence-test.xml", "META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
                .addAsLibraries(assertJ()) // create files of assertJ and hibernate libs and add to war
                .addAsLibraries(hibernate());
        System.out.println(webArchive.toString(true));
        return webArchive;
    }

    // Needed to provide hibernate & assertJ functionality to war
    private static File[] assertJ() {
        return Maven.resolver()
                .loadPomFromFile("pom.xml")
                .resolve("org.assertj:assertj-core")
                .withTransitivity()
                .asFile();
    }

    private static File[] hibernate() {
        return Maven.resolver()
                .loadPomFromFile("pom.xml")
                .resolve("org.hibernate:hibernate-entitymanager")
                .withTransitivity().asFile();
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

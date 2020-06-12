package service;

import dao.AbstractDao;
import domain.Adres;
import domain.Bezorgwijze;
import domain.Gebruiker;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.Test;
import org.junit.runner.RunWith;
import resources.GebruikerInput;
import util.GebruikerInputMapper;

import javax.inject.Inject;
import java.io.File;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(Arquillian.class)
public class GebruikerServiceIT {

    @Inject
    GebruikerService service;

    @Deployment
    public static Archive<?> createDeployment() {
        WebArchive webArchive = ShrinkWrap.create(WebArchive.class, "test.war")
                .addClass(GebruikerService.class)
                .addClass(GebruikerInput.class)
                .addClass(Gebruiker.class)
                .addClass(Adres.class)
                .addClass(Bezorgwijze.class)
                .addClass(GebruikerInputMapper.class)
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
    public void whenGebruikerInputIsGivenShouldBeMappedToGebruikerEntityAndPersisted() {
        String email = "email@valid.com";
        String straat = "Voorbeeldstraat";
        String huisnummer = "45A";
        String postcode = "3423KY";
        String stad = "Zwammerdam";

        GebruikerInput gebruikerIn = new GebruikerInput();
        gebruikerIn.setEmail(email);
        gebruikerIn.setBezorgAfhalenThuis(true);
        gebruikerIn.setBezorgAfhalenMagazijn(false);
        gebruikerIn.setBezorgVersturenRembours(false);
        gebruikerIn.setBezorgVersturenVooruit(true);
        gebruikerIn.setStraat(straat);
        gebruikerIn.setHuisnummer(huisnummer);
        gebruikerIn.setPostcode(postcode);
        gebruikerIn.setStad(stad);
        gebruikerIn.setAkkoordVoorwaarden(true);

        Gebruiker gebruiker = service.registreerGebruiker(gebruikerIn);
        System.out.println("Gebruiker id: " + gebruiker.getId() + " ------------------------");
        assertThat(gebruiker.getId()).isNotNull();
    }
}

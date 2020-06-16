package resources;

import apps.App;
import dao.AbstractDao;
import dao.GebruikerDao;
import domain.Adres;
import domain.Bezorgwijze;
import domain.Gebruiker;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import service.GebruikerService;
import util.GebruikerInputMapper;

import javax.inject.Inject;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import java.io.File;
import java.net.URL;
import java.util.List;

import static javax.ws.rs.client.ClientBuilder.*;
import static javax.ws.rs.core.MediaType.TEXT_PLAIN;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(Arquillian.class)
public class GebruikersResourceIT {

    @ArquillianResource
    private URL deploymentURL;

    @Inject
    GebruikerDao dao;

    @Deployment
    public static Archive<?> createDeployment() {
        WebArchive webArchive = ShrinkWrap.create(WebArchive.class, "test.war")
                .addClass(App.class)
                .addClass(GebruikersResource.class)
                .addClass(GebruikerService.class)
                .addClass(GebruikerInput.class)
                .addPackage(Gebruiker.class.getPackage())
                .addClass(Adres.class)
                .addPackage(AbstractDao.class.getPackage())
                .addClass(GebruikerInputMapper.class)
                .addClass(Bezorgwijze.class)
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
    public void testEndpointShouldReturnHetWerkt() {
        String message = newClient().target(deploymentURL + "gebruikers").request(TEXT_PLAIN).get(String.class);
        assertThat(message).isEqualTo("Het werkt");
    }

    @Test
    public void whenPOSTEndPointIsCalledGebruikerInputShouldBeMappedAndPersistedAndInputShouldBeReturnedAsResponseBody() {
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

        GebruikerInput result = newClient()
                .target(deploymentURL + "gebruikers/nieuw")
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.json(gebruikerIn), GebruikerInput.class);

        List<Gebruiker> gebruikers = dao.find(email);
        assertThat(result.getEmail()).isEqualTo(email);
        assertThat(gebruikers.size()).isGreaterThan(0);
    }
}

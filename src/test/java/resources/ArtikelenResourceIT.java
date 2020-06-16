package resources;

import apps.App;
import dao.ArtikelDao;
import dao.BijlageDao;
import domain.AbstractArtikel;
import domain.Bijlage;
import domain.Gebruiker;
import domain.Product;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;
import service.BijlageService;
import testUtil.ArquillianBase;
import util.ArtikelInputMapper;

import javax.inject.Inject;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.*;
import java.math.BigDecimal;
import java.net.URL;
import java.util.HashSet;

import static javax.ws.rs.client.ClientBuilder.newClient;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(Arquillian.class)
public class ArtikelenResourceIT {

    @ArquillianResource
    private URL deploymentURL;

    @Inject
    ArtikelDao artikelDao;

    @Deployment
    public static Archive<?> createDeployment() {
        WebArchive webArchive = ArquillianBase.createDeploymentBase();
        webArchive.addClass(App.class);
        webArchive.addPackage(Bijlage.class.getPackage()); // domain
        webArchive.addPackage(BijlageDao.class.getPackage()); // dao
        webArchive.addPackage(ArtikelInput.class.getPackage()); // resources
        webArchive.addPackage(ArtikelInputMapper.class.getPackage()); // util
        webArchive.addPackage(BijlageService.class.getPackage()); // service
        webArchive.addAsResource("files/ImageSmall.jpg");
        System.out.println(webArchive.toString(true));
        return webArchive;
    }


    @Test
    public void whenPOSTNieuwEndpointIsCalledInputShouldBeMappedPersistedAndReturnedWithId() {
        ArtikelInput artikelInput = new ArtikelInput();
        artikelInput.setNaam("Een product");
        artikelInput.setSoort("Product");
        artikelInput.setPrijs(10.02);
        artikelInput.setGebruikerId(1);
        artikelInput.setBezorgVersturenRembours(true);
        artikelInput.setBezorgAfhalenThuis(true);
        artikelInput.setCategorie("Overige producten");

        ArtikelInput result = newClient()
                .target(deploymentURL + "artikelen/nieuw")
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.json(artikelInput), ArtikelInput.class);
        AbstractArtikel resultEntity = artikelDao.find(1, AbstractArtikel.class);

        assertThat(resultEntity.getId()).isEqualTo(result.getId());
        assertThat(resultEntity.getNaam()).isEqualTo(artikelInput.getNaam());
    }

    @Test
    public void whenPostNieuwBijlageIsCalledInputShouldBeMappedPersistedAndReturned() throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream stream = classLoader.getResourceAsStream("files/ImageSmall.jpg");
        artikelDao.persist(new Product(new Gebruiker(), "Testartikel", BigDecimal.valueOf(45.34), new HashSet<>()));

        String result = newClient()
                .target(deploymentURL + "artikelen/nieuw/bijlage")
                .queryParam("bijlagenaam", "plaatje.jpg")
                .queryParam("bijlagetype", "image/jpg").queryParam("artikelid", "1")
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.text(stream), String.class);

        assertThat(result).contains("1", "plaatje.jpg", "image/jpg");

        stream.close();
    }
}

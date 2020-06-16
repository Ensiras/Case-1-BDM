package resources;

import apps.App;
import dao.ArtikelDao;
import dao.BijlageDao;
import domain.AbstractArtikel;
import domain.Bijlage;
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
import java.net.URL;

import static javax.ws.rs.client.ClientBuilder.newClient;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(Arquillian.class)
public class ArtikelenResourceIT {

    @ArquillianResource
    private URL deploymentURL;

    @Inject
    ArtikelDao dao;

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
        AbstractArtikel resultEntity = dao.find(1, AbstractArtikel.class);

        assertThat(resultEntity.getId()).isEqualTo(result.getId());
        assertThat(resultEntity.getNaam()).isEqualTo(artikelInput.getNaam());

    }
}

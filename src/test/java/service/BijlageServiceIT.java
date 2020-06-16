package service;

import dao.ArtikelDao;
import dao.BijlageDao;
import domain.AbstractArtikel;
import domain.Bijlage;
import domain.Gebruiker;
import domain.Product;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;
import resources.ArtikelInput;
import testUtil.ArquillianBase;
import util.ArtikelInputMapper;

import javax.inject.Inject;
import java.io.File;
import java.math.BigDecimal;
import java.util.HashSet;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(Arquillian.class)
public class BijlageServiceIT {

    @Inject
    BijlageService service;

    @Inject
    ArtikelDao artikelDao;

    @Deployment
    public static Archive<?> createDeployment() {
        WebArchive webArchive = ArquillianBase.createDeploymentBase();
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
    public void whenRawBijlageIsGivenShouldMapAndPersistsAndReturnBijlageEntity() {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("files/ImageSmall.jpg").getFile());
        artikelDao.persist(new Product(new Gebruiker(), "Testartikel", BigDecimal.valueOf(45.34), new HashSet<>()));

        Bijlage result = service.verwerkNieuweBijlage(file, "testbestand", "image/jpg", "1");
        AbstractArtikel artikelMetBijlage = artikelDao.getById(1);

        assertThat(result).isInstanceOf(Bijlage.class);
        assertThat(result.getId()).isNotNull();
        assertThat(result.getBestandsNaam()).isEqualTo("testbestand");
        assertThat(result.getType()).isEqualTo("image/jpg");
        assertThat(result.getArtikel()).isEqualTo(artikelMetBijlage);
    }


}

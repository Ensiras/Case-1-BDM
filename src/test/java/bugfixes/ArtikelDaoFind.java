package bugfixes;

import dao.AbstractDao;
import dao.ArtikelDao;
import dao.CategorieDao;
import domain.AbstractArtikel;
import domain.Gebruiker;
import domain.Product;
import domain.ProductCategorie;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import java.io.File;
import java.math.BigDecimal;
import java.util.HashSet;

// TODO: delete for final version
/* This 'test' exists to isolate a bug with AbstractCategorie causing this error:
 * rg.hibernate.PropertyAccessException: Could not set field value by reflection
 * Solved by giving that class an int id field instead of a string field */

@RunWith(Arquillian.class)
public class ArtikelDaoFind {

    @Inject
    ArtikelDao dao;

    @Inject
    CategorieDao catDao;

    @Deployment
    public static Archive<?> createDeployment() {
        WebArchive webArchive = ShrinkWrap.create(WebArchive.class, "test.war")
                .addPackage(AbstractArtikel.class.getPackage())
                .addClass(AbstractDao.class)
                .addClass(ArtikelDao.class)
                .addClass(CategorieDao.class)
                .addAsLibraries(assertJ())
                .addAsLibraries(hibernate())
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
                .addAsResource("persistence-test.xml", "META-INF/persistence.xml");
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

    // Now solved
    @Test
    public void whenGettingArtikelFromDBCausesError() {
        ProductCategorie categorie = new ProductCategorie("Muziek", "een omschrijving");
        catDao.persist(categorie);
        Product product = new Product(
                new Gebruiker(),
                "Test",
                BigDecimal.valueOf(33),
                new HashSet<>(),
                categorie);
        dao.persist(product);
        AbstractArtikel abstractArtikel = dao.find(1, AbstractArtikel.class);
    }
}

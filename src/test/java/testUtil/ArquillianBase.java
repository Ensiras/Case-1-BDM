package testUtil;

import apps.App;
import dao.AbstractDao;
import domain.AbstractArtikel;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import resources.ArtikelInput;
import service.ArtikelService;
import service.GebruikerService;
import util.ArtikelInputMapper;
import util.GebruikerInputMapper;

import java.io.File;


/* Util class providing methods for creating a base WAR for Arquillian deployment. */
public class ArquillianBase {

    public static WebArchive createDeploymentBase() {
        WebArchive webArchive = ShrinkWrap.create(WebArchive.class, "test.war")
                .addAsResource("persistence-test.xml", "META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
                .addAsLibraries(assertJ()) // create files of assertJ and hibernate libs and add to war
                .addAsLibraries(hibernate());
//        System.out.println(webArchive.toString(true));
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



}

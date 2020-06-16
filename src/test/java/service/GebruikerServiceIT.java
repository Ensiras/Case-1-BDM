package service;

import domain.Gebruiker;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;
import resources.GebruikerInput;
import testUtil.ArquillianBase;

import javax.inject.Inject;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(Arquillian.class)
public class GebruikerServiceIT {

    @Inject
    GebruikerService service;

    @Deployment
    public static Archive<?> createDeployment() {
        WebArchive webArchive = ArquillianBase.createDeploymentBase();
        System.out.println(webArchive.toString(true));
        return webArchive;
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

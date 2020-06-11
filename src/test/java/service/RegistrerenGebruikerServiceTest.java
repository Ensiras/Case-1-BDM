package service;

import domain.Gebruiker;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import resources.GebruikerInput;

import javax.persistence.EntityManager;

import static domain.Bezorgwijze.AFHALEN_THUIS;
import static domain.Bezorgwijze.VERSTUREN_VOORBET;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class RegistrerenGebruikerServiceTest {

    @Mock
    EntityManager em;

    @InjectMocks
    RegistrerenGebruikerService service = new RegistrerenGebruikerService();

    @Test
    void whenGebruikerInputIsGivenShouldReturnGebruikerEntityWithSameValues() {
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
        gebruikerIn.setStraat(straat);
        gebruikerIn.setAkkoordVoorwaarden(true);

        Gebruiker gebruikerUit = service.registreerGebruiker(gebruikerIn);

        assertAll( () -> {
            assertThat(gebruikerUit.getId()).isEqualTo(0);
            assertThat(gebruikerUit.getEmail()).isEqualTo(email);
            assertThat(gebruikerUit.getBezorgwijzen()).containsOnly(AFHALEN_THUIS, VERSTUREN_VOORBET);
            assertThat(gebruikerUit.getAdres().getStraat()).isEqualTo(straat);
            assertThat(gebruikerUit.getAdres().getHuisnummer()).isEqualTo(huisnummer);
            assertThat(gebruikerUit.getAdres().getPostcode()).isEqualTo(postcode);
            assertThat(gebruikerUit.getAdres().getStad()).isEqualTo(stad);
            assertThat(gebruikerUit.isAkkoordVoorwaarden()).isTrue();
        });
    }

    @Test
    void whenAdresIsNotSetShouldKeepAdresEmpty() {
        String email = "email@valid.com";
        GebruikerInput gebruikerIn = new GebruikerInput();
        gebruikerIn.setEmail(email);
        gebruikerIn.setAkkoordVoorwaarden(true);

        Gebruiker gebruikerUit = service.registreerGebruiker(gebruikerIn);

        assertAll( () -> {
            assertThat(gebruikerUit.getAdres().getStraat()).isNull();
            assertThat(gebruikerUit.getAdres().getHuisnummer()).isNull();
            assertThat(gebruikerUit.getAdres().getPostcode()).isNull();
            assertThat(gebruikerUit.getAdres().getStad()).isNull();
        });
    }
}

package util;

import domain.Gebruiker;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import resources.GebruikerInput;

import static domain.Bezorgwijze.AFHALEN_THUIS;
import static domain.Bezorgwijze.VERSTUREN_VOORBET;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class GebruikerInputMapperTest {

    GebruikerInputMapper mapper = new GebruikerInputMapper();

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
        gebruikerIn.setAkkoordVoorwaarden(true);

        Gebruiker gebruikerUit = mapper.mapGebruikerInputToGebruiker(gebruikerIn);

        assertAll( () -> {
            assertThat(gebruikerUit.getId()).isEqualTo(0);
            assertThat(gebruikerUit.getEmail()).isEqualTo(email);
            assertThat(gebruikerUit.getBezorgwijzen()).containsOnly(AFHALEN_THUIS, VERSTUREN_VOORBET);
            assertThat(gebruikerUit.getStraat()).isEqualTo(straat);
            assertThat(gebruikerUit.getHuisnummer()).isEqualTo(huisnummer);
            assertThat(gebruikerUit.getPostcode()).isEqualTo(postcode);
            assertThat(gebruikerUit.getStad()).isEqualTo(stad);
            assertThat(gebruikerUit.isAkkoordVoorwaarden()).isTrue();
        });
    }

    @Test
    void whenAdresIsNotSetShouldKeepAdresEmpty() {
        String email = "email@valid.com";
        GebruikerInput gebruikerIn = new GebruikerInput();
        gebruikerIn.setEmail(email);
        gebruikerIn.setAkkoordVoorwaarden(true);

        Gebruiker gebruikerUit = mapper.mapGebruikerInputToGebruiker(gebruikerIn);

        assertAll( () -> {
            assertThat(gebruikerUit.getStraat()).isNull();
            assertThat(gebruikerUit.getHuisnummer()).isNull();
            assertThat(gebruikerUit.getPostcode()).isNull();
            assertThat(gebruikerUit.getStad()).isNull();
        });
    }



}

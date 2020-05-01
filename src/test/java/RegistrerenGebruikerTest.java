import controller.RegistrerenGebruiker;
import domain.Bezorgwijze;
import domain.Gebruiker;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static domain.Bezorgwijze.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RegistrerenGebruikerTest {

    @Test
    void whenRegisterGebruikerEmailAndBezorgwijzenShouldBeSavedAdresIsEmpty() {
        Set<Bezorgwijze> bezorgwijzen = new HashSet<>();
        bezorgwijzen.add(AFHALEN_MAGAZIJN);
        bezorgwijzen.add(VERSTUREN_VOORBET);

        Gebruiker gebruiker = RegistrerenGebruiker.registreerGebruiker("Test@example.com", bezorgwijzen);

        assertThat(gebruiker.getEmail()).isEqualTo("Test@example.com");
        assertThat(gebruiker.getBezorgWijzen()).contains(AFHALEN_MAGAZIJN, VERSTUREN_VOORBET);
        assertThat(gebruiker.getAdres()).isNull();

    }

    //TODO: manier vinden om dit te testen
    @Test
    void whenBezorgWijzeAfhalenThuisIsSupportedAdresIsRequired() {
        Set<Bezorgwijze> bezorgwijzen = new HashSet<>();
        bezorgwijzen.add(AFHALEN_THUIS);
        bezorgwijzen.add(VERSTUREN_VOORBET);

        Gebruiker gebruiker = RegistrerenGebruiker.registreerGebruiker("Test@example.com", bezorgwijzen);

//        assertThat(gebruiker.getAdres()).isNotNull();
    }

    @Test
    void whenInvalidEmailIsGivenShouldReturnFalse() {
        String email1 = "invalidemail@noperiod";
        String email2 = "invalidemail.noAtSymbol";

        boolean noPeriod = RegistrerenGebruiker.checkEmail(email1);
        boolean noAtSymbol = RegistrerenGebruiker.checkEmail(email2);

        assertFalse(noPeriod && noAtSymbol);
    }

    @Test
    void whenValidEmailIsGivenShouldReturnTrue() {
        String email = "validemail@goodjob.com";

        boolean result = RegistrerenGebruiker.checkEmail(email);

        assertTrue(result);
    }

    @Test
    void whenSetContainsAfhalenThuisShouldReturnTrue() {
        Set<Bezorgwijze> bezorgwijzen = new HashSet<>();
        bezorgwijzen.add(AFHALEN_THUIS);
        bezorgwijzen.add(VERSTUREN_VOORBET);

        boolean result = RegistrerenGebruiker.checkBezorgwijzen(bezorgwijzen);

        assertTrue(result);
    }

    @Test
    void whenSetDoesNotContainAfhalenThuisShouldReturnFalse() {
        Set<Bezorgwijze> bezorgwijzen = new HashSet<>();
        bezorgwijzen.add(AFHALEN_MAGAZIJN);
        bezorgwijzen.add(VERSTUREN_VOORBET);

        boolean result = RegistrerenGebruiker.checkBezorgwijzen(bezorgwijzen);

        assertFalse(result);
    }
}
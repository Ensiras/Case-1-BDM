import controller.RegistrerenGebruiker;
import domain.Bezorgwijze;
import domain.Gebruiker;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static domain.Bezorgwijze.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class RegistrerenGebruikerTest {

    @Test
    void whenRegisterGebruikerEmailAndBezorgwijzenShouldBeSavedAdresMayBeEmpty() {
        Set<Bezorgwijze> bezorgwijzen = new HashSet<>();
        bezorgwijzen.add(AFHALEN_MAGAZIJN);
        bezorgwijzen.add(VERSTUREN_VOORBET);

        Gebruiker gebruiker = RegistrerenGebruiker.registreerGebruiker("Test@example.com", bezorgwijzen);

        assertThat(gebruiker.getEmail()).isEqualTo("Test@example.com");
        assertThat(gebruiker.getBezorgWijzen()).contains(AFHALEN_MAGAZIJN, VERSTUREN_VOORBET);
        assertNull(gebruiker.getStraat());
        assertNull(gebruiker.getHuisnummer());
        assertNull(gebruiker.getPostcode());
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

    @Test
    void whenPostcodeIsInvalidShouldReturnFalse() {
        String[] adres1 = {"Pianoweg", "34A", "1444rer"};
        String[] adres2 = {"Pianoweg", "34A", "144re"};
        String[] adres3 = {"Pianoweg", "34A", "14444re"};

        boolean result = RegistrerenGebruiker.checkAdres(adres1);
        boolean result2 = RegistrerenGebruiker.checkAdres(adres2);
        boolean result3 = RegistrerenGebruiker.checkAdres(adres3);

        assertFalse(result);
        assertFalse(result2);
        assertFalse(result3);
    }

    @Test
    void whenPostcodeIsValidShouldReturnTrue() {
        String[] adres1 = {"Pianoweg", "34A", "1444xy"};

        boolean result = RegistrerenGebruiker.checkAdres(adres1);

        assertTrue(result);
    }


}
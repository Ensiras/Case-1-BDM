import controller.RegistrerenGebruiker;
import dao.GebruikerDao;
import domain.Bezorgwijze;
import domain.Gebruiker;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Stubber;
import views.RegGebruikerView;

import java.util.HashSet;
import java.util.Set;

import static domain.Bezorgwijze.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


// TODO tests fixen

@ExtendWith(MockitoExtension.class)
class RegistrerenGebruikerTest {

    @Mock
    RegGebruikerView mockedView;

    @Mock
    GebruikerDao mockedDao = mock(GebruikerDao.class);

    @InjectMocks
    RegistrerenGebruiker regGebr = new RegistrerenGebruiker();

    @Test
    void whenValidInputIsGivenNewGebruikerShouldBeCreated() {
        when(mockedView.vraagInput(anyString())).thenReturn("email@email.com", "j", "n", "j", "n", "j");
        doNothing().when(mockedDao).insert(any(Gebruiker.class));

        boolean registratieSucces = regGebr.startRegistratie();

        assertTrue(registratieSucces);
    }

    // TODO: Test maken
    @Test
    void whenAfhalenThuisIsSupportedAddressShouldBeGiven() {
    }

    @Test
    void whenRegisterGebruikerEmailAndBezorgwijzenShouldBeSavedAdresMayBeEmpty() {
        Set<Bezorgwijze> bezorgwijzen = new HashSet<>();
        String[] adres = new String[3];

        bezorgwijzen.add(AFHALEN_MAGAZIJN);
        bezorgwijzen.add(VERSTUREN_VOORBET);

        Gebruiker gebruiker = regGebr.registreerGebruiker("Test@example.com", bezorgwijzen, adres, true);

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

        boolean noPeriod = regGebr.checkEmail(email1);
        boolean noAtSymbol = regGebr.checkEmail(email2);

        assertFalse(noPeriod && noAtSymbol);
    }

    @Test
    void whenValidEmailIsGivenShouldReturnTrue() {
        String email = "validemail@goodjob.com";

        boolean result = regGebr.checkEmail(email);

        assertTrue(result);
    }

    @Test
    void whenSetContainsAfhalenThuisShouldReturnTrue() {
        Set<Bezorgwijze> bezorgwijzen = new HashSet<>();
        bezorgwijzen.add(AFHALEN_THUIS);
        bezorgwijzen.add(VERSTUREN_VOORBET);

        boolean result = regGebr.checkBezorgwijzen(bezorgwijzen);

        assertTrue(result);
    }

    @Test
    void whenSetDoesNotContainAfhalenThuisShouldReturnFalse() {
        Set<Bezorgwijze> bezorgwijzen = new HashSet<>();
        bezorgwijzen.add(AFHALEN_MAGAZIJN);
        bezorgwijzen.add(VERSTUREN_VOORBET);

        boolean result = regGebr.checkBezorgwijzen(bezorgwijzen);

        assertFalse(result);
    }

    @Test
    void whenPostcodeIsInvalidShouldReturnFalse() {
        String[] adres1 = {"Pianoweg", "34A", "1444rer"};
        String[] adres2 = {"Pianoweg", "34A", "144re"};
        String[] adres3 = {"Pianoweg", "34A", "14444re"};

        boolean result = regGebr.checkAdres(adres1);
        boolean result2 = regGebr.checkAdres(adres2);
        boolean result3 = regGebr.checkAdres(adres3);

        assertFalse(result);
        assertFalse(result2);
        assertFalse(result3);
    }

    @Test
    void whenPostcodeIsValidShouldReturnTrue() {
        String[] adres1 = {"Pianoweg", "34A", "1444xy"};

        boolean result = regGebr.checkAdres(adres1);

        assertTrue(result);
    }

}
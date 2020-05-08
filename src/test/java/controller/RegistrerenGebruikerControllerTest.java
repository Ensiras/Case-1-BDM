package controller;

import domain.Bezorgwijze;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import views.RegistrerenGebruikerView;

import java.util.HashSet;
import java.util.Set;

import static domain.Bezorgwijze.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class RegistrerenGebruikerControllerTest {

    @Mock
    RegistrerenGebruikerView mockedView;

    @InjectMocks
    RegistrerenGebruikerController controller = new RegistrerenGebruikerController(new RegistrerenGebruikerView());

    @Test
    void whenInputIsJShouldReturnTrue() {
        when(mockedView.vraagInput(anyString())).thenReturn("j");

        boolean result = controller.vraagToestemming();

        assertTrue(result);
    }

    @Test
    void whenInputIsNShouldReturnFalse() {
        when(mockedView.vraagInput(anyString())).thenReturn("n");

        boolean result = controller.vraagToestemming();

        assertFalse(result);
    }

    @Test
    void whenInvalidEmailIsGivenShouldReturnFalse() {
        String email1 = "invalidemail@noperiod";
        String email2 = "invalidemail.noAtSymbol";
        String email3 = "invalidemail.dotbefore@com";

        boolean noPeriod = controller.checkEmail(email1);
        boolean noAtSymbol = controller.checkEmail(email2);
        boolean periodBeforeAt = controller.checkEmail(email3);

        assertFalse(noPeriod && noAtSymbol && periodBeforeAt);
    }

    @Test
    void whenValidEmailIsGivenReturnEmailString() {
        when(mockedView.vraagInput(anyString())).thenReturn("valid@email.com");

        String email = controller.vraagEmail();

        assertThat(email).isEqualTo("valid@email.com").isNotNull().isInstanceOf(String.class);
    }

    @Test
    void whenVraagBezorgwijzenIsCalledShouldOnlyReturnedSupportedBezorgwijzen() {
        when(mockedView.vraagInput(anyString())).thenReturn("n", "j", "n", "j");

        Set<Bezorgwijze> bezorgwijzen = controller.vraagBezorgwijzen();

        assertThat(bezorgwijzen).containsOnly(AFHALEN_THUIS, VERSTUREN_REMBOURS);
    }

    @Test
    void whenBezorgwijzenDoesNotContainAfhalenThuisShouldReturnFalse() {
        Set<Bezorgwijze> bezorgwijzen = new HashSet<>();

        bezorgwijzen.add(AFHALEN_MAGAZIJN);
        bezorgwijzen.add(VERSTUREN_VOORBET);

        boolean result = controller.checkBezorgwijzen(bezorgwijzen);

        assertFalse(result);
    }

    @Test
    void whenBezorgwijzenContainsAfhalenThuisShouldReturnTrue() {
        Set<Bezorgwijze> bezorgwijzen = new HashSet<>();
        bezorgwijzen.add(AFHALEN_THUIS);
        bezorgwijzen.add(VERSTUREN_VOORBET);

        boolean result = controller.checkBezorgwijzen(bezorgwijzen);

        assertTrue(result);
    }

    @Test
    void whenValidAdresIsGivenShouldReturnAdresInStringArray() {
        when(mockedView.vraagInput(anyString())).thenReturn("Teststraat", "45", "4323TY");

        String[] result = controller.vraagAdres();

        assertThat(result).containsExactly("Teststraat", "45", "4323TY");
    }

    @Test
    void whenPostcodeIsInvalidShouldReturnFalse() {
        String[] adres1 = {"Pianoweg", "34A", "1444rer"};
        String[] adres2 = {"Pianoweg", "34A", "144re"};
        String[] adres3 = {"Pianoweg", "34A", "14444re"};
        String[] adres4 = {"", "34A", "14444re"};

        boolean result = controller.checkAdres(adres1);
        boolean result2 = controller.checkAdres(adres2);
        boolean result3 = controller.checkAdres(adres3);
        boolean result4 = controller.checkAdres(adres4);

        assertFalse(result);
        assertFalse(result2);
        assertFalse(result3);
        assertFalse(result4);
    }

    @Test
    void whenPostcodeIsValidShouldReturnTrue() {
        String[] adres1 = {"Pianoweg", "34A", "1444xy"};

        boolean result = controller.checkAdres(adres1);

        assertTrue(result);
    }

}
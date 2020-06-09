package controller;

import domain.ArtikelSoort;
import domain.Bezorgwijze;
import domain.Bijlage;
import domain.Gebruiker;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import views.AanbiedenArtikelView;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

import static domain.ArtikelSoort.DIENST;
import static domain.Bezorgwijze.AFHALEN_THUIS;
import static domain.Bezorgwijze.VERSTUREN_VOORBET;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AanbiedenArtikelControllerTest {

    @Mock
    AanbiedenArtikelView mockedView;

    @InjectMocks
    AanbiedenArtikelController controller = new AanbiedenArtikelController(new AanbiedenArtikelView());

    @Test
    void whenBezorgwijzeNotSupportedShouldReturnSetWithoutIt() {
        when(mockedView.vraagInput(anyString())).thenReturn("1", "2");
        Gebruiker gebruiker = new Gebruiker();
        gebruiker.addBezorgwijze(AFHALEN_THUIS);
        gebruiker.addBezorgwijze(VERSTUREN_VOORBET);

        Set<Bezorgwijze> bezorgwijzen = controller.vraagBezorgwijzen(gebruiker);

        assertThat(bezorgwijzen)
                .isNotNull()
                .isNotEmpty()
                .containsOnly(AFHALEN_THUIS);
    }

    @Test
    void whenValidPrijsIsGivenShouldReturnItAsABigDecimal() {
        String input = "45,23";
        when(mockedView.vraagInput(anyString())).thenReturn(input);

        BigDecimal result = controller.vraagPrijs();

        assertThat(result)
                .isInstanceOf(BigDecimal.class)
                .isEqualTo(new BigDecimal("45.23"));
    }

    @Test
    void whenInputIs2ShouldReturnDIENST() {
        when(mockedView.vraagInput(anyString())).thenReturn("2");

        ArtikelSoort result = controller.vraagArtikelSoort();

        assertThat(result).isNotNull().isEqualTo(DIENST);
    }

    @Test
    void whenInputIsNShouldReturnNull() {
        when(mockedView.vraagInput(anyString())).thenReturn("2");

        List<Bijlage> result = controller.vraagBijlagen();

        assertThat(result).isNull();
    }

    @Test
    void whenGebruikerDoesntHaveBezorgwijzenAndInputIs1ReturnFalse() {
        Gebruiker gebruiker = new Gebruiker();
        when(mockedView.vraagInput(anyString())).thenReturn("1");

        boolean result = controller.checkBezorgwijzen(gebruiker);

        assertFalse(result);
    }

    @Test
    void whenGebruikerDoesHaveBezorgwijzenReturnTrue() {
        Gebruiker gebruiker = new Gebruiker();
        gebruiker.addBezorgwijze(AFHALEN_THUIS);

        boolean result = controller.checkBezorgwijzen(gebruiker);

        assertTrue(result);
    }

}

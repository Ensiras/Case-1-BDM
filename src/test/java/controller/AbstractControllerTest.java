package controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import views.AanbiedenArtikelView;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AbstractControllerTest {

    @Mock
    AanbiedenArtikelView mockedView;

    AbstractController<AanbiedenArtikelView> controller;

    @BeforeEach
    void setUp() {
        controller = new AanbiedenArtikelController(mockedView);
    }

    @Test
    void whenEmptyStringisGivenShouldReturnNull() {
        String input = "";

        BigDecimal result = controller.checkPrijsInput(input);

        assertThat(result).isNull();
    }

    @Test
    void whenInvalidNumberIsGivenReturnNull() {
        String input = "034adx";

        BigDecimal result = controller.converteerPrijs(input);

        assertThat(result).isNull();
    }

    @Test
    void whenCommaIsUsedAsDecimalPointShouldReplaceItAndReturnAsABigDecimal() {
        String input = "231,56";

        BigDecimal result = controller.converteerPrijs(input);

        assertThat(result)
                .isNotNull()
                .isEqualTo(BigDecimal.valueOf(231.56));
    }

    @Test
    void whenValidNumberIsGivenShouldReturnItAsABigDecimal() {
        String input = "45.32";

        BigDecimal result = controller.checkPrijsInput(input);

        assertThat(result)
                .isNotNull()
                .isEqualTo(BigDecimal.valueOf(45.32));

    }

    @Test
    void whenNumberSmallerOrEqualTo0IsGivenShouldReturnNull() {
        String input = "0";

        BigDecimal result = controller.checkPrijsInput(input);

        assertThat(result).isNull();
    }

    @Test
    void whenValidInputIsGivenShouldReturnInput() {
        when(mockedView.vraagInput(anyString())).thenReturn("1");

        String input = controller.vraagInput("Dit is een testbericht.");

        assertThat(input)
                .isNotNull()
                .isEqualTo("1");
    }

    @Test
    void whenAnyInputIsGivenShouldReturnInput() {
        String input = "maaktnietheelveeluit";
        when(mockedView.vraagInput()).thenReturn(input);

        String result = controller.vraagInputNietLeeg("Maakt ook niets uit");

        assertThat(result)
                .isNotNull()
                .isEqualTo(input);
    }

    @Test
    void whenNoInputIsGivenShouldAskAgainAndReturnInputIfAnyIsGiven() {
        String input1 = "";
        String input2 = "maaktnietheelveeluit";
        when(mockedView.vraagInput()).thenReturn(input1, input2);

        String result = controller.vraagInputNietLeeg("Maakt ook niets uit");

        assertThat(result)
                .isNotNull()
                .isEqualTo(input2);
    }
}
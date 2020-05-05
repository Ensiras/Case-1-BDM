package controller;

import dao.ArtikelDao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import views.AanbiedenArtikelView;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class AbstractControllerTest {

    @Mock
    AanbiedenArtikelView mockedView;

    AbstractController<ArtikelDao, AanbiedenArtikelView> controller;

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

        assertThat(result).isNotNull().isEqualTo(BigDecimal.valueOf(231.56));
    }

    @Test
    void whenValidNumberIsGivenShouldReturnItAsABigDecimal() {
        String input = "45.32";

        BigDecimal result = controller.checkPrijsInput(input);

        assertThat(result).isNotNull().isEqualTo(BigDecimal.valueOf(45.32));

    }
}
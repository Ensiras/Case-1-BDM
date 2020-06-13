package util;

import domain.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import resources.ArtikelInput;
import service.GebruikerService;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ArtikelInputMapperTest {

    @Mock
    GebruikerService service;

    @InjectMocks
    ArtikelInputMapper mapper = new ArtikelInputMapper();

    @Test
    void whenGivenArtikelInputOfSoortProductShouldReturnProductEntityWithMappedValues() {
        when(service.zoek(anyInt())).thenReturn(new Gebruiker());

        ArtikelInput artikelIn = new ArtikelInput();
        artikelIn.setGebruikerId(1);
        artikelIn.setNaam("Product");
        artikelIn.setPrijs(34.34);
        artikelIn.setCategorie("Overige");
        artikelIn.setOmschrijving("Een omschrijving");
        artikelIn.setBezorgAfhalenMagazijn(true);
        artikelIn.setBezorgAfhalenThuis(true);
        artikelIn.setBezorgVersturenVooruit(false);
        artikelIn.setBezorgVersturenRembours(false);

        Product product = mapper.mapArtikelInputToProductEntity(artikelIn);

        assertAll(() -> {
            assertThat(product.getNaam()).isEqualTo("Product");
            assertThat(product.getPrijs()).isEqualTo(BigDecimal.valueOf(34.34));
            assertThat(product.getProductCategorie()).isInstanceOf(ProductCategorie.class);
            assertThat(product.getAanbieder()).isNotNull().isInstanceOf(Gebruiker.class);
            assertThat(product.getOmschrijving()).isEqualTo("Een omschrijving");
        });
    }

    @Test
    void whenGivenArtikelInputOfSoortDienstShouldReturnDiensEntityWithMappedValues() {
        when(service.zoek(anyInt())).thenReturn(new Gebruiker());

        ArtikelInput artikelIn = new ArtikelInput();
        artikelIn.setGebruikerId(1);
        artikelIn.setNaam("Dienst");
        artikelIn.setPrijs(34.34);
        artikelIn.setCategorie("Overige");
        artikelIn.setOmschrijving("Een omschrijving");

        Dienst dienst = mapper.mapArtikelInputToDienstEntity(artikelIn);

        assertAll(() -> {
            assertThat(dienst.getAanbieder()).isNotNull().isInstanceOf(Gebruiker.class);;
            assertThat(dienst.getNaam()).isEqualTo("Dienst");
            assertThat(dienst.getPrijs()).isEqualTo(BigDecimal.valueOf(34.34));
            assertThat(dienst.getDienstCategorie()).isInstanceOf(DienstCategorie.class);
            assertThat(dienst.getOmschrijving()).isEqualTo("Een omschrijving");
        });
    }

    @Test
    void whenGivenArtikelInputShouldReturnEntityOfCorrectSoort() {
        when(service.zoek(anyInt())).thenReturn(new Gebruiker());

        ArtikelInput productIn = new ArtikelInput();
        productIn.setSoort("Product");
        productIn.setPrijs(34.23);

        ArtikelInput dienstIn = new ArtikelInput();
        dienstIn.setSoort("Dienst");
        dienstIn.setPrijs(34.23);

        Product product = mapper.mapArtikelInputToArtikelEntity(productIn);
        Dienst dienst = mapper.mapArtikelInputToArtikelEntity(dienstIn);

        assertThat(product).isInstanceOf(Product.class);
        assertThat(dienst).isInstanceOf(Dienst.class);
    }
}

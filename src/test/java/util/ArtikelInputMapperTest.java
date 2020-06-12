package util;

import domain.Gebruiker;
import domain.Product;
import domain.ProductCategorie;
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
    void whenGivenArtikelOfSoortProductInputShouldReturnProductEntityWithSameValues() {
        when(service.zoek(anyInt())).thenReturn(new Gebruiker());

        ArtikelInput artikelIn = new ArtikelInput();
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
        });
    }
}

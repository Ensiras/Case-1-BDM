package util;

import domain.AbstractArtikel;
import domain.Dienst;
import domain.Gebruiker;
import domain.Product;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import resources.ArtikelInput;
import service.GebruikerService;

import java.math.BigDecimal;
import java.util.HashSet;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ArtikelInputMapperTest {

    @Mock
    GebruikerService service;

    @Mock
    BezorgwijzenMapper bezorgwijzenMapper;

    @InjectMocks
    ArtikelInputMapper mapper = new ArtikelInputMapper();


    @Test
    void whenGivenArtikelInputOfSoortProductShouldReturnProductEntityWithMappedValues() {
        when(service.zoek(anyInt())).thenReturn(new Gebruiker());
        when(bezorgwijzenMapper.mapBezorgwijzen(any(ArtikelInput.class))).thenReturn(new HashSet<>());

        ArtikelInput artikelIn = new ArtikelInput();
        artikelIn.setGebruikerId(1);
        artikelIn.setNaam("Een product");
        artikelIn.setSoort("Product");
        artikelIn.setPrijs(34.34);
        artikelIn.setCategorie("Overige");
        artikelIn.setOmschrijving("Een omschrijving");

        AbstractArtikel product = mapper.mapFromInputToEntity(artikelIn);

        assertAll(() -> {
            assertThat(product.getNaam()).isEqualTo("Een product");
            assertThat(product).isInstanceOf(Product.class);
            assertThat(product.getPrijs()).isEqualTo(BigDecimal.valueOf(34.34));
            assertThat(product.getCategorie()).isEqualTo("Overige");
            assertThat(product.getAanbieder()).isNotNull().isInstanceOf(Gebruiker.class);
            assertThat(product.getOmschrijving()).isEqualTo("Een omschrijving");
        });
    }

    @Test
    void whenGivenArtikelInputOfSoortDienstShouldReturnDienstEntityWithMappedValues() {
        when(service.zoek(anyInt())).thenReturn(new Gebruiker());

        ArtikelInput artikelIn = new ArtikelInput();
        artikelIn.setGebruikerId(1);
        artikelIn.setNaam("Een dienst");
        artikelIn.setSoort("Dienst");
        artikelIn.setPrijs(34.34);
        artikelIn.setCategorie("Overige");
        artikelIn.setOmschrijving("Een omschrijving");

        AbstractArtikel dienst = mapper.mapFromInputToEntity(artikelIn);

        assertAll(() -> {
            assertThat(dienst.getAanbieder()).isNotNull().isInstanceOf(Gebruiker.class);
            assertThat(dienst.getNaam()).isEqualTo("Een dienst");
            assertThat(dienst).isInstanceOf(Dienst.class);
            assertThat(dienst.getPrijs()).isEqualTo(BigDecimal.valueOf(34.34));
            assertThat(dienst.getCategorie()).isEqualTo("Overige");
            assertThat(dienst.getOmschrijving()).isEqualTo("Een omschrijving");
        });
    }

    @Test
    void whenGivenArtikelInputShouldReturnEntityOfCorrectSoort() {
        when(service.zoek(anyInt())).thenReturn(new Gebruiker());
        when(bezorgwijzenMapper.mapBezorgwijzen(any(ArtikelInput.class))).thenReturn(new HashSet<>());

        ArtikelInput productIn = new ArtikelInput();
        productIn.setSoort("Product");
        productIn.setPrijs(34.23);

        ArtikelInput dienstIn = new ArtikelInput();
        dienstIn.setSoort("Dienst");
        dienstIn.setPrijs(34.23);

        AbstractArtikel product = mapper.mapFromInputToEntity(productIn);
        AbstractArtikel dienst = mapper.mapFromInputToEntity(dienstIn);

        assertThat(product).isInstanceOf(Product.class);
        assertThat(dienst).isInstanceOf(Dienst.class);
    }
}

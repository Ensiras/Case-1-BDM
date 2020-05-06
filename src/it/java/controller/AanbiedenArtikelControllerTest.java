package controller;

import dao.CategorieDao;
import domain.AbstractCategorie;
import domain.ArtikelSoort;
import domain.DienstCategorie;
import domain.ProductCategorie;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import views.AanbiedenArtikelView;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import static domain.ArtikelSoort.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AanbiedenArtikelControllerIt {

    EntityManager em;

    @Mock
    AanbiedenArtikelView mockedView;

    AanbiedenArtikelController controller;

    @BeforeEach
    void setUp() {
        em = Persistence.createEntityManagerFactory("h2").createEntityManager();
        controller = new AanbiedenArtikelController(mockedView);
    }

    // Test werkt niet vanwege het gebruik van getentitymanager methode...
    @Test
    void whenVraagCategorieIsCalledGetOptionsFromDatabaseAndReturnChosenCategorie() {
        CategorieDao catDao = new CategorieDao(em);
        ProductCategorie pc1 = new ProductCategorie("Product cat 1", "Omschrijving");
        ProductCategorie pc2 = new ProductCategorie("Product cat 2", "Omschrijving");
        DienstCategorie dc1 = new DienstCategorie("Dienst cat 1", "Omschrijving");
        when(mockedView.vraagInput()).thenReturn("1");

        AbstractCategorie result = controller.vraagCategorie(PRODUCT);

        assertThat(result)
                .isInstanceOf(ProductCategorie.class)
                .isNotNull()
                .isEqualTo(pc2);
    }
}
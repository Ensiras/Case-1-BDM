package controller;

import dao.CategorieDao;
import domain.AbstractCategorie;
import domain.Bijlage;
import domain.DienstCategorie;
import domain.ProductCategorie;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import util.DBUtil;
import views.AanbiedenArtikelView;

import javax.persistence.EntityManager;

import static domain.ArtikelSoort.PRODUCT;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static util.DBUtil.*;

@ExtendWith(MockitoExtension.class)
class AanbiedenArtikelControllerIt {

    EntityManager em;

    @Mock
    AanbiedenArtikelView mockedView;

    @InjectMocks
    AanbiedenArtikelController controller = new AanbiedenArtikelController(new AanbiedenArtikelView());

    @BeforeEach
    void setUp() {
        closeEntityManager();
        em = getEntityManager("h2");
    }

    @Test
    void whenVraagCategorieIsCalledGetOptionsFromDatabaseAndReturnChosenCategorie() {
        CategorieDao catDao = new CategorieDao(em);

        ProductCategorie pc1 = new ProductCategorie("Product cat 1", "Omschrijving");
        ProductCategorie pc2 = new ProductCategorie("Product cat 2", "Omschrijving");
        DienstCategorie dc1 = new DienstCategorie("Dienst cat 1", "Omschrijving");
        catDao.opslaan(pc1);
        catDao.opslaan(pc2);
        catDao.opslaan(dc1);

        when(mockedView.vraagInput()).thenReturn("1");

        AbstractCategorie result = controller.vraagCategorie(PRODUCT);

        assertThat(result)
                .isInstanceOf(ProductCategorie.class)
                .isNotNull()
                .isEqualTo(pc2);
    }

    @Test
    void whenValidFilePathIsGivenReturnBijlage() {
        when(mockedView.vraagInput(anyString())).thenReturn("C:\\Users\\Thomas\\IdeaProjects\\BDM\\src\\it\\resources\\testFiles\\dekameel.png");

        Bijlage bijlage = controller.toevoegenBijlage();

        assertThat(bijlage).isInstanceOf(Bijlage.class).isNotNull();
    }
}
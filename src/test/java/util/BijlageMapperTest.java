package util;

import domain.AbstractArtikel;
import domain.Bijlage;
import domain.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import resources.BijlageInput;
import service.ArtikelService;

import java.io.File;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class BijlageMapperTest {

    @Mock
    ArtikelService artikelService;

    @Mock
    FileToByteArrayUtil fileUtil;

    @InjectMocks
    BijlageInputMapper mapper;

    BijlageInput bijlageInput = new BijlageInput();

    File file;

    @BeforeEach
    void setUp() {
        file = mock(File.class);
        bijlageInput.setBijlageNaam("Een bijlage");
        bijlageInput.setBijlageType("image/jpg");
        bijlageInput.setArtikelId("1");
        bijlageInput.setDataIn(file);
    }

    @Test
    void whenBijlageInputIsGivenShouldCallOnDaoAndReturnInstanceOfAbstractArtikel() {
        when(artikelService.zoek(anyInt())).thenReturn(new Product());

        AbstractArtikel abstractArtikel = mapper.setBijbehorendArtikel(bijlageInput);

        assertThat(abstractArtikel).isInstanceOf(AbstractArtikel.class);
        verify(artikelService).zoek(anyInt());
    }

    @Test
    void whenBijlageInputIsGivenShouldMapItsValuesToBijlageEntityAndReturnIt() {
        when(artikelService.zoek(anyInt())).thenReturn(new Product());
        when(fileUtil.readData(file)).thenReturn(new byte[1]);

        Bijlage bijlage = mapper.mapFromInputToEntity(bijlageInput);

        assertAll(() -> {
            assertThat(bijlage.getArtikel()).isNotNull();
            assertThat(bijlage.getBestandsNaam()).isEqualTo("Een bijlage");
            assertThat(bijlage.getType()).isEqualTo("image/jpg");
            assertThat(bijlage.getData()).isNotEmpty();
        });

        verify(artikelService).zoek(anyInt());
        verify(fileUtil).readData(file);
    }
}

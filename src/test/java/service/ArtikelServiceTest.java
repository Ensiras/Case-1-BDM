package service;

import dao.ArtikelDao;
import domain.AbstractArtikel;
import domain.Product;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import resources.ArtikelInput;
import util.InputMapper;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ArtikelServiceTest {

    @Mock
    ArtikelDao dao;

    @Mock
    InputMapper<ArtikelInput, AbstractArtikel> artikelMapper;

    @InjectMocks
    ArtikelService service = new ArtikelService();

    @Test
    void whenAanbiedenArtikelIsCalledShouldCallMapperAndDao() {
        when(artikelMapper.mapFromInputToEntity(any(ArtikelInput.class))).thenReturn(new Product());
        doNothing().when(dao).persist(any(AbstractArtikel.class));
        InOrder order = inOrder(artikelMapper, dao);

        service.aanbiedenArtikel(new ArtikelInput());

        order.verify(artikelMapper).mapFromInputToEntity(any(ArtikelInput.class));
        order.verify(dao).persist(any(AbstractArtikel.class));

    }

}

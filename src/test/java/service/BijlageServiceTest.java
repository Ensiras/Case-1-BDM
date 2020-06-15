package service;

import dao.BijlageDao;
import domain.Bijlage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.File;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BijlageServiceTest {

    @Mock
    BijlageDao dao;

    @InjectMocks
    BijlageService service = new BijlageService();

    @Test
    void whenBijlageFromResourceIsGivenShouldCallPersistOnDao() {
        File file = mock(File.class);
        doNothing().when(dao).persist(any());
        Bijlage bijlage = service.verwerkNieuweBijlage(file, "testimage.png", "image/png", "1");
        verify(dao).persist(any());
    }
}

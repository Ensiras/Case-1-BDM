package service;

import dao.BijlageDao;
import domain.Bijlage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import resources.BijlageInput;
import util.InputMapper;

import java.io.File;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BijlageServiceTest {

    @Mock
    BijlageDao dao;

    @Mock
    InputMapper<BijlageInput, Bijlage> inputMapper;

    @InjectMocks
    BijlageService service = new BijlageService();


    @Test
    void whenBijlageFromResourceIsGivenShouldCallPersistOnDao() {
        when(inputMapper.mapFromInputToEntity(any(BijlageInput.class))).thenReturn(new Bijlage());
        doNothing().when(dao).persist(any());
        File file = mock(File.class);

        Bijlage bijlage = service.verwerkNieuweBijlage(file, "testimage.png", "image/png", "1");

        verify(inputMapper).mapFromInputToEntity(any(BijlageInput.class));
        verify(dao).persist(any());
    }

}

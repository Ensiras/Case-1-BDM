package service;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityManager;

@ExtendWith(MockitoExtension.class)
class RegistrerenGebruikerServiceTest {

    @Mock
    EntityManager em;

    @InjectMocks
    RegistrerenGebruikerService service = new RegistrerenGebruikerService();

}

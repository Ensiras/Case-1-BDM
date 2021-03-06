package service;

import dao.GebruikerDao;
import domain.Gebruiker;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import resources.GebruikerInput;
import util.GebruikerInputMapper;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GebruikerServiceTest {

    @Mock
    GebruikerDao dao;

    @Mock
    GebruikerInputMapper mapper;

    @InjectMocks
    GebruikerService service = new GebruikerService();

    @Test
    void whenRegisterenGebruikerIsCalledShouldCallMapperAndDaoMethods() {
        GebruikerInput gebruikerInput = new GebruikerInput();
        Gebruiker gebruiker = new Gebruiker();

        when(mapper.mapFromInputToEntity(gebruikerInput)).thenReturn(gebruiker);
        doNothing().when(dao).persist(gebruiker);
        InOrder order = inOrder(mapper, dao);

        service.registreerGebruiker(gebruikerInput);

        order.verify(mapper).mapFromInputToEntity(gebruikerInput);
        order.verify(dao).persist(gebruiker);

    }
}

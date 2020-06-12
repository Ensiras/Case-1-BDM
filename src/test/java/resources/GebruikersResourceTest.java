package resources;


import domain.Gebruiker;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import service.GebruikerService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GebruikersResourceTest {

    @Mock
    GebruikerService service;

    @InjectMocks
    GebruikersResource resource = new GebruikersResource();

    @Test
    void whenRegistreerGebruikerIsCalledShouldCallServiceAndReturnInput() {
        GebruikerInput gebruikerIn = new GebruikerInput();
        when(service.registreerGebruiker(gebruikerIn)).thenReturn(new Gebruiker());

        GebruikerInput gebruikerResult = resource.registrerenGebruiker(gebruikerIn);

        verify(service, atMostOnce()).registreerGebruiker(gebruikerIn);
        assertThat(gebruikerResult).isEqualTo(gebruikerIn);
    }
}

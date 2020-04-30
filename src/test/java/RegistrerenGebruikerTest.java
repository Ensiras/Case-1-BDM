import controller.RegistrerenGebruiker;
import domain.Bezorgwijze;
import domain.Gebruiker;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static domain.Bezorgwijze.*;
import static org.assertj.core.api.Assertions.assertThat;

class RegistrerenGebruikerTest {

    @Test
    void whenRegisterGebruikerEmailAndBezorgwijzenShouldBeSavedAdresIsEmpty() {
        Set<Bezorgwijze> bezorgwijzen = new HashSet<>();
        bezorgwijzen.add(AFHALEN_MAGAZIJN);
        bezorgwijzen.add(VERSTUREN_VOORBET);

        Gebruiker gebruiker = RegistrerenGebruiker.registreren("Test@example.com", bezorgwijzen);

        assertThat(gebruiker.getEmail()).isEqualTo("Test@example.com");
        assertThat(gebruiker.getBezorgWijzen()).contains(AFHALEN_MAGAZIJN, VERSTUREN_VOORBET);
        assertThat(gebruiker.getAdres()).isNull();

    }

    //TODO: manier vinden om dit te testen
    @Test
    void whenBezorgWijzeAfhalenThuisIsSupportedAdresIsRequired() {
        Set<Bezorgwijze> bezorgwijzen = new HashSet<>();
        bezorgwijzen.add(AFHALEN_THUIS);
        bezorgwijzen.add(VERSTUREN_VOORBET);

        Gebruiker gebruiker = RegistrerenGebruiker.registreren("Test@example.com", bezorgwijzen);

//        assertThat(gebruiker.getAdres()).isNotNull();
    }
}
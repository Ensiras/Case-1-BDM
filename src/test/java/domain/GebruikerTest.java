package domain;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class GebruikerTest {

    @Test
    void whenGebruikerIsInstantiatedAddressFieldsShouldBeFilledFromStringArray() {
        String email = "email@test.com";
        Set<Bezorgwijze> bezorgwijzen = new HashSet<>();
        bezorgwijzen.add(Bezorgwijze.AFHALEN_THUIS);
        String[] adres = {"Die ene straat", "34", "3456TY"};

        Gebruiker gebruiker = new Gebruiker(email, bezorgwijzen, adres, true);

        assertThat(gebruiker.getAdres()).contains("Die ene straat", "34", "3456TY");
    }
}
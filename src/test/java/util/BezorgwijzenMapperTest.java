package util;

import domain.Bezorgwijze;
import org.junit.jupiter.api.Test;
import resources.ArtikelInput;
import resources.GebruikerInput;

import java.util.Set;

import static domain.Bezorgwijze.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatObject;
import static org.junit.jupiter.api.Assertions.*;

class BezorgwijzenMapperTest {

    BezorgwijzenMapper mapper = new BezorgwijzenMapper();

    @Test
    void whenGebruikerInputIsGivenWithBezorgwijzenShouldReturnThoseInSet() {
        GebruikerInput gebruikerInput = new GebruikerInput();
        gebruikerInput.setBezorgAfhalenMagazijn(true);
        gebruikerInput.setBezorgVersturenRembours(true);
        gebruikerInput.setBezorgAfhalenThuis(false);
        gebruikerInput.setBezorgVersturenVooruit(false);

        Set<Bezorgwijze> bezorgwijzen = mapper.mapBezorgwijzen(gebruikerInput);

        assertThat(bezorgwijzen).containsOnly(Bezorgwijze.AFHALEN_MAGAZIJN, VERSTUREN_REMBOURS);
    }

    @Test
    void whenGebruikerInputIsGivenWithoutBezorgwijzenShouldReturnEmptyHashSet() {
        GebruikerInput gebruikerInput = new GebruikerInput();

        Set<Bezorgwijze> bezorgwijzen = mapper.mapBezorgwijzen(gebruikerInput);

        assertThat(bezorgwijzen).isEmpty();
    }

    @Test
    void whenArtikelInputIsGivenWithBezorgwijzenShouldReturnThoseInSet() {
        ArtikelInput artikelInput = new ArtikelInput();
        artikelInput.setBezorgAfhalenThuis(true);
        artikelInput.setBezorgVersturenRembours(true);

        Set<Bezorgwijze> bezorgwijzen = mapper.mapBezorgwijzen(artikelInput);

        assertThat(bezorgwijzen).containsOnly(AFHALEN_THUIS, VERSTUREN_REMBOURS);
    }
}

package domain;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class AbstractArtikelTest {

    @Test
    void whenListOfBijlagenIsAddedShouldBeAddedToObject() {
        AbstractArtikel artikel = new Product();
        List<Bijlage> bijlageList = new ArrayList<>();
        Bijlage bijlage1 = new Bijlage();
        Bijlage bijlage2 = new Bijlage();
        Bijlage bijlage3 = new Bijlage();
        bijlageList.add(bijlage1);
        bijlageList.add(bijlage2);
        bijlageList.add(bijlage3);

        artikel.addBijlagen(bijlageList);

        assertThat(bijlageList).containsExactly(bijlage1, bijlage2, bijlage3);

    }
}
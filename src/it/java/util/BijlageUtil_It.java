package util;

import domain.Bijlage;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BijlageUtil_It {

    @Test
    void whenValidFileIsGivenShouldReturnNewBijlage() {
        String filePath = "C:\\Users\\Thomas\\IdeaProjects\\BDM\\src\\it\\resources\\testFiles\\dekameel.png";

        Bijlage result = BijlageUtil.maakBijlage(filePath);

        assertThat(result).isNotNull().isInstanceOf(Bijlage.class);
    }

    @Test
    void whenInvalidFileIsGivenShouldReturnNull() {
        String filePath = "C:\\Users\\Thomas\\IdeaProjects\\BDM\\src\\it\\resources\\testFiles\\doetniets.exe";

        Bijlage result = BijlageUtil.maakBijlage(filePath);

        assertThat(result).isNull();
    }

    @Test
    void whenInvalidFilePathIsGivenShouldReturnNull() {
        String filePath = "C:\\Users\\Thomas\\IdeaProjects\\BDM\\src\\it\\resources\\testFiles\\bestaatniet.nee";

        Bijlage result = BijlageUtil.maakBijlage(filePath);

        assertThat(result).isNull();
    }

}
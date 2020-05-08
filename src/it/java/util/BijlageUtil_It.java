package util;

import domain.Bijlage;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static util.BijlageUtil.*;

class BijlageUtil_It {

    @Test
    void whenValidFileIsGivenShouldReturnNewBijlage() {
        String filePath = "C:\\Users\\Thomas\\IdeaProjects\\BDM\\src\\it\\resources\\testFiles\\dekameel.png";

        Bijlage result = maakBijlage(filePath);

        assertThat(result).isNotNull().isInstanceOf(Bijlage.class);
    }

    @Test
    void whenInvalidFileIsGivenShouldReturnNull() {
        String filePath = "C:\\Users\\Thomas\\IdeaProjects\\BDM\\src\\it\\resources\\testFiles\\doetniets.exe";

        Bijlage result = maakBijlage(filePath);

        assertThat(result).isNull();
    }

    @Test
    void whenInvalidFilePathIsGivenShouldReturnNull() {
        String filePath = "C:\\Users\\Thomas\\IdeaProjects\\BDM\\src\\it\\resources\\testFiles\\bestaatniet.nee";

        Bijlage result = maakBijlage(filePath);

        assertThat(result).isNull();
    }

    @Test
    void whenFileSizeIsTooBigShouldReturnNull() {
        String filePath = "C:\\Users\\Thomas\\IdeaProjects\\BDM\\src\\it\\resources\\testFiles\\video.mp4";

        Bijlage result = maakBijlage(filePath);

        assertAll(
                () -> assertThat(result).isNull(),
                () -> assertThat(getErrorMessage()).isEqualTo("Bestand is te groot."));
    }
}
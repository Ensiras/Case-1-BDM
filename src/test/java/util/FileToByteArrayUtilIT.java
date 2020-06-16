package util;

import org.junit.jupiter.api.Test;

import java.io.File;

import static org.assertj.core.api.Assertions.assertThat;

class FileToByteArrayUtilIT {

    FileToByteArrayUtil fileUtil = new FileToByteArrayUtil();

    @Test
    void whenFileIsGivenShouldReadAndReturnItAsByteArray() {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("files/ImageSmall.jpg").getFile());

        byte[] result = fileUtil.readData(file);

        assertThat(result).isNotEmpty();
        assertThat(result.length).isEqualTo(file.length());
    }
}

package util;

import domain.BijlageType;
import org.junit.jupiter.api.Test;

import java.io.File;


import static domain.BijlageType.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static util.BijlageUtil.*;
import static util.BijlageUtil.MAX_GROOTTE;

class BijlageUtilTest {

    File file = mock(File.class);


    @Test
    void whenFileIsTooBigShouldReturnFalse() {
        when(file.length()).thenReturn(MAX_GROOTTE + 1L);

        boolean result = checkGrootte(file);

        assertFalse(result);
    }

    @Test
    void whenFileIsWithinSizeLimitsShouldReturnTrue() {
        when(file.length()).thenReturn((long) MAX_GROOTTE);

        boolean result = checkGrootte(file);

        assertTrue(result);

    }

    @Test
    void whenFileTypeIsSupportedShouldReturnFileType() {
        String[] supportedTypes = {"image", "video", "audio"};
        BijlageType[] results = new BijlageType[3];

        for (int i = 0; i < 3; i++) {
            results[i] = checkType(supportedTypes[i]);
        }

        assertThat(results).hasSize(3).contains(AUDIO, VIDEO, IMAGE);

    }
}
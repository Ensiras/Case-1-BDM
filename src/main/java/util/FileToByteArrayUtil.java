package util;

import javax.ejb.Stateless;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

@Stateless
public class FileToByteArrayUtil {

    public FileToByteArrayUtil() {
    }

    public byte[] readData(File dataIn) {
        byte[] dataUit = new byte[(int) dataIn.length()];
        try (FileInputStream stream = new FileInputStream(dataIn)) {
            stream.read(dataUit);
        } catch(FileNotFoundException e){
            System.err.println("Bestand niet gevonden door InputStream " + e.getMessage());
        } catch(IOException e){
            System.err.println("Lezen data bijlage mislukt " + e.getMessage());
        }
        return dataUit;
    }
}

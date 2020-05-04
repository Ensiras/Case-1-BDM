package domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Entity
public class Bijlage {

    public Bijlage() {
    }

    @Id @GeneratedValue
    private int id;
    private String naam;

    @Lob
    private byte[] data;

    public void setBijlage() {

        try {

            String fileType = Files.probeContentType(Paths.get("D:\\Documents\\Werk\\Java Training\\Casus\\img\\dekameel.png"));
            String fileType2 = Files.probeContentType(Paths.get("D:\\Documents\\Werk\\Java Training\\Casus\\img\\01 - Bilateral.mp3"));
            String fileType3 = Files.probeContentType(Paths.get("D:\\Documents\\Werk\\Java Training\\Casus\\img\\doetniets.exe"));

            System.out.println(fileType);
            System.out.println(fileType2);
            System.out.println(fileType3);
            File fileInput = new File("D:\\Documents\\Werk\\Java Training\\Casus\\img\\dekameel.png");
            data = new byte[(int) fileInput.length()];
            FileInputStream fileInputStream = new FileInputStream(fileInput);
            fileInputStream.read(data);
            fileInputStream.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.err.println("Oops" + e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

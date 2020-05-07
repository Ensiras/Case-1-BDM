package domain;

import javax.persistence.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static javax.persistence.EnumType.STRING;


@Entity
public class Bijlage {

    @Id
    @GeneratedValue
    private int id;
    private String bestandsNaam;

    @Enumerated(STRING)
    private BijlageType type;

    @ManyToOne
    private AbstractArtikel artikel;

    @Lob
    private byte[] data;


    public Bijlage() {
    }

    public Bijlage(String bestandsNaam, BijlageType type, byte[] data) {
        this.bestandsNaam = bestandsNaam;
        this.type = type;
        this.data = data;
    }

    public void setArtikel(AbstractArtikel artikel) {
        this.artikel = artikel;
    }


/*    public Bijlage(String pad) {
        boolean typeCheck = false;
        File fileInput = new File(pad);
        try (FileInputStream stream = new FileInputStream(fileInput)) {
            this.data = new byte[(int) fileInput.length()];
            this.bestandsNaam = fileInput.getName();
            stream.read(data);

        } catch (FileNotFoundException e) {
            System.err.println("Bestand kon niet gevonden worden." + e.getMessage());
        } catch (IOException e) {
            System.err.println("Bestand kon niet gelezen worden: " + e.getMessage());
        }
    }*/


/*    private boolean setType(String pad) throws IOException {
        String fileType = Files.probeContentType(Paths.get(pad));
        for (BijlageType type : BijlageType.values()) {
            if (fileType.contains(type.toString().toLowerCase())) {
                this.type = type;
                return true;
            }
        }

        return false;
    }

    private boolean checkGrootte(String pad, File fileInput) {
        return fileInput.length() <= MAX_GROOTTE;
    }*/

    public void setBijlage() {

        try {

            String fileType = Files.probeContentType(Paths.get("D:\\Documents\\Werk\\Java Training\\Casus\\img\\dekameel.png"));
            String fileType2 = Files.probeContentType(Paths.get("D:\\Documents\\Werk\\Java Training\\Casus\\img\\01 - Bilateral.mp3"));
            String fileType3 = Files.probeContentType(Paths.get("D:\\Documents\\Werk\\Java Training\\Casus\\img\\doetniets.exe"));

            System.out.println(fileType);
            System.out.println(fileType2);
            System.out.println(fileType3);
            File fileInput = new File("D:\\Documents\\Werk\\Java Training\\Casus\\img\\01 - Bilateral.mp3");
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

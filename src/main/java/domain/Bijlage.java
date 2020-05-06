package domain;

import javax.persistence.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


@Entity
public class Bijlage {

    @Id
    @GeneratedValue
    private int id;
    private String bestandsNaam;
    private BijlageType type;

    @ManyToOne
    private AbstractArtikel artikel;

    @Lob
    private byte[] data;

    public Bijlage() {
    }

    public Bijlage(String pad) {
        File fileInput = new File(pad);
        try(FileInputStream stream = new FileInputStream(fileInput)) {
            this.data = new byte[(int) fileInput.length()];
            this.bestandsNaam = fileInput.getName();

            stream.read(data);

        } catch (FileNotFoundException e) {
            System.err.println("Bestand kon niet gevonden worden." + e.getMessage());
        } catch (IOException e) {
            System.err.println("Bestand kon niet gelezen worden: " + e.getMessage());
        }
    }

    // TODO: Implementeren dat alleen videos, afbeeldingen en audiobestanden toegevoegd kunnen worden. Evt. max grootte.
    // TODO: ook bijlagetype setten
    private boolean checkBijlage(String pad) {
        return false;
    }

    public void setArtikel(AbstractArtikel artikel) {
        this.artikel = artikel;
    }

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

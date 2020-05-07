package util;

import domain.Bijlage;
import domain.BijlageType;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


// Utility klasse voor het maken van een nieuwe bijlage vanaf een opgegeven pad
public class BijlageUtil {

    private static final int MAX_GROOTTE = 10485760;
    public static String ERROR_MESSAGE;

    public static Bijlage maakBijlage(String pad) {
        String bestandsnaam = "";
        BijlageType type = null;
        byte[] data = null;

        if (!Files.exists(Paths.get(pad))) {
            ERROR_MESSAGE = "Bestand kon niet gevonden worden.";
            return null;
        }
        try {
            type = setType(pad); // check en set het type
            if (type == null) {
                return null;
            }

            File fileInput = new File(pad);
            if (!checkGrootte(fileInput)) { // als bestand te groot is dan...
                return null;
            }

            try (FileInputStream stream = new FileInputStream(fileInput)) {
                data = getData(fileInput, stream); // Lees de data van het bestand en sla op in byte[]
                bestandsnaam = fileInput.getName(); // zet de bestandsnaam
            } catch (FileNotFoundException e) {
                ERROR_MESSAGE = "Bestand kon niet gevonden worden.";
                System.err.println(e.getMessage());
                return null;
            }

        } catch (IOException e) {
            ERROR_MESSAGE = "Bestand kon niet gelezen worden.";
            System.err.println(e.getMessage());
            return null;
        }
        return new Bijlage(bestandsnaam, type, data);
    }

    private static boolean checkGrootte(File fileInput) {
        if (fileInput.length() <= MAX_GROOTTE) {
            return true;
        } else {
            ERROR_MESSAGE = "Bestand is te groot.";
            return false;
        }
    }

    private static BijlageType setType(String pad) throws IOException {
        String fileType = Files.probeContentType(Paths.get(pad));
        for (BijlageType type : BijlageType.values()) {
            if (fileType.contains(type.toString().toLowerCase())) {
                return type;
            }
        }
        ERROR_MESSAGE = "Bestandstype " + fileType + " wordt niet ondersteund.";
        return null;
    }

    private static byte[] getData(File fileInput, FileInputStream stream) throws IOException {
        byte[] data = new byte[(int) fileInput.length()];
        stream.read(data);
        return data;
    }

}
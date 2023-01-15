package it.uniroma2.dicii.ispw.progetto.lupini.dao.filesystem;


import it.uniroma2.dicii.ispw.progetto.lupini.exceptions.PersistanceLayerNotAvailable;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BannedWordsDAOCSV {

    private static final String CSV_FILE_NAME = "src/main/resources/it/uniroma2/dicii/ispw/progetto/lupini/view/bannedWords.csv";

    public static List<String> retrieveBannedWords() throws PersistanceLayerNotAvailable {
        File fd = new File(CSV_FILE_NAME);
        List<String> list = new ArrayList<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(fd));
            String line;

            while ((line = reader.readLine()) != null) {

                list.add(line);
            }

        }catch (IOException e) {
            throw new PersistanceLayerNotAvailable("Error in opening or reading file");
        }

        return list;
    }
}

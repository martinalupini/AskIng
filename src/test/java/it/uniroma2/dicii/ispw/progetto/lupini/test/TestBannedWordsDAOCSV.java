package it.uniroma2.dicii.ispw.progetto.lupini.test;

import it.uniroma2.dicii.ispw.progetto.lupini.dao.filesystem.BannedWordsDAOCSV;
import it.uniroma2.dicii.ispw.progetto.lupini.exceptions.PersistanceLayerNotAvailable;
import org.junit.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

/*
    Testo che le parole bannate siano state recuperate correttamente verificando che una parola che so per certo essere contenuta nella
    lista si trivi effettivamente nella lista di parole recuperate
 */
public class TestBannedWordsDAOCSV {

    @Test
    public void testRetrieveBannedWords(){
        boolean found = false;
        try {
            List<String> listBannedWords = BannedWordsDAOCSV.retrieveBannedWords();
            for(String s: listBannedWords){
                if(s.equalsIgnoreCase("stupida")){
                    found = true;
                }
            }
        } catch (PersistanceLayerNotAvailable e) {
            throw new RuntimeException(e);
        }

        assertEquals(found, true);
    }
}

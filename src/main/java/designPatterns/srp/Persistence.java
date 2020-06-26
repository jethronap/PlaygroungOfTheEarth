package designPatterns.srp;

import java.io.File;
import java.io.PrintStream;

/**
 * handles the single responsibility of persisting objects
 */
public class Persistence {

    public void saveToFile (Diary diary,
                            String filename,
                            boolean overwrite) throws Exception {

        if (overwrite || new File(filename).exists()) {
            try (PrintStream out = new PrintStream(filename)) {
                out.println(diary.toString());
            }
        }
    }
}

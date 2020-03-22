package sentinel;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;

import java.io.File;
import java.io.IOException;

public class LargeFileReader {

    public void readFile() throws IOException {

        final String path = "src/main/resources/testData/sentinelFile.txt";

        try(final LineIterator it = FileUtils.lineIterator(new File(path), "UTF-8");) {
            while (it.hasNext()) {

                final String line = it.nextLine();
                System.out.println(line);
            }
        }
    }
}

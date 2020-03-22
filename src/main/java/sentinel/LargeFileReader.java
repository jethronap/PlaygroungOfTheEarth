package sentinel;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LargeFileReader {

    /**
     * Using Commons io LineIterator
     * @throws IOException
     */
    public void readLines() throws IOException {

        final String path = "src/main/resources/testData/sentinelFile.txt";

        try (final LineIterator it = FileUtils.lineIterator(new File(path), "UTF-8");) {
            while (it.hasNext()) {

                final String line = it.nextLine();
                System.out.println(line);
            }
        }
    }

    /**
     * Usign Java Nio
     * @throws IOException
     */
    public void readFile () throws IOException {
        Path path = Paths.get("src/main/resources/testData/sentinelFile.txt");

        List<String> listOfData = new ArrayList<>();


        Stream<String> lines = Files.lines(path);
        String data = lines.collect(Collectors.joining("\n"));
        listOfData.add(data);
        System.out.println(listOfData.get(0));
        lines.close();
    }

}

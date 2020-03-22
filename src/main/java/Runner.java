import sentinel.LargeFileReader;

import java.io.IOException;
import java.net.URISyntaxException;

public class Runner {

    public static void main(String[] args) throws IOException, URISyntaxException {

        LargeFileReader reader = new LargeFileReader();
        //reader.readLines();
        reader.readFile();
    }
}

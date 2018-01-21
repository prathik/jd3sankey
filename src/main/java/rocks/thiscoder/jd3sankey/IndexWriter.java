package rocks.thiscoder.jd3sankey;

import org.apache.commons.io.IOUtils;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.URISyntaxException;


class IndexWriter {
    void writeIndex() throws URISyntaxException, IOException {
        IOUtils.copy(getClass().getResourceAsStream("/index.html"), new FileOutputStream("./index.html"));
    }

    String indexToString() throws IOException {
        return IOUtils.toString(getClass().getResourceAsStream("/index.html"), "UTF-8");
    }

    void writeJson(String json) throws FileNotFoundException {
        try (PrintStream out = new PrintStream(new FileOutputStream("./energy.json"))) {
            out.print(json);
        }
    }
}

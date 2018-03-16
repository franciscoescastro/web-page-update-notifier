package tests;

import edu.sdsu.file.Reader;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ReaderTest {
    @Test
    public void testReadFile() {
        String filePath = "src/tests/input/document";
        Reader reader = new Reader();
        reader.readFile(filePath);

        StringBuilder textBuilder = new StringBuilder();
        textBuilder.append("http://www.eli.sdsu.edu/courses/spring15/cs635/notes/index.html transcript\n");
        textBuilder.append("http://www.eli.sdsu.edu/index.html mail transcript\n");
        textBuilder.append("http://bismarck.sdsu.edu/CS635/recent mail\n");
        textBuilder.append("http://bismarck.sdsu.edu/CS635/8 transcript mail");

        assertEquals(textBuilder.toString(), reader.getText());
    }
}
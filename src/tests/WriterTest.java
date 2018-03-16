package tests;

import edu.sdsu.file.Reader;
import edu.sdsu.file.Writer;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertEquals;

public class WriterTest {
    @Test
    public void testWriteFile() {
        String path = "test.txt";
        String text = "Text to file";
        Writer writer = new Writer(path, text);
        writer.writeFile();
        Reader reader = new Reader();
        reader.readFile(path);

        assertEquals(text, reader.getText());

        deleteFile(path);
    }

    private void deleteFile(String path) {
        File file = new File(path);
        file.delete();
    }
}
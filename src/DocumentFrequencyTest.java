import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class DocumentFrequencyTest {

    @Test
    public void numDocuments() throws IOException {
        DocumentFrequency test = new DocumentFrequency("./src/files/test.txt");
        System.out.println(test.numDocuments());
    }

    @Test
    public void query() throws IOException {
        DocumentFrequency test = new DocumentFrequency("./src/files/test.txt");
        assertEquals(5,test.query("quick"));
        assertEquals(2,test.query("dog"));
        assertEquals(0,test.query("DSC30"));
    }
}
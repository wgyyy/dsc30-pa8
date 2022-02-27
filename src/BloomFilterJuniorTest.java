import org.junit.Test;

import static org.junit.Assert.*;

public class BloomFilterJuniorTest {

    @Test
    public void insert() {
    }

    @Test
    public void lookup() {
        BloomFilterJunior test = new BloomFilterJunior(100);
        test.insert("DSC30");
        assertEquals(true, test.lookup("DSC30"));
    }
}
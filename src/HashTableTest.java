import org.junit.Test;

import java.util.HashSet;

import static org.junit.Assert.*;

public class HashTableTest {

    @Test (expected = IllegalArgumentException.class)
    public void HashTableIAE() {
        HashTable test = new HashTable(1);
    }

    @org.junit.Test
    public void insert() {
        HashTable test = new HashTable();
        test.insert("Hello");
        assertEquals(true, test.lookup("Hello"));
        test.insert("World");
        assertEquals(true, test.lookup("World"));
        assertEquals(2, test.size());
        test.insert("!");
        assertEquals(true, test.lookup("!"));
        assertEquals(3, test.size());
    }

    @Test (expected = NullPointerException.class)
    public void insertNPE() {
        HashTable test = new HashTable();
        test.insert(null);
    }

    @org.junit.Test
    public void delete() {
        HashTable test =new HashTable();
        test.insert("Hello");
        test.insert("World");
        test.insert("!");
        assertEquals(true, test.delete("!"));
        assertEquals(false, test.delete("DSC30"));
        assertEquals(true, test.delete("Hello"));
        assertEquals(1,test.size());
    }

    @Test (expected = NullPointerException.class)
    public void deleteNPE() {
        HashTable test = new HashTable();
        test.delete(null);
    }

    @org.junit.Test
    public void lookup() {
        HashTable test =new HashTable();
        test.insert("Hello");
        test.insert("World");
        test.insert("!");
        assertEquals(true, test.lookup("Hello"));
        assertEquals(false, test.lookup("DSC30"));
        assertEquals(false, test.lookup("hello"));
    }

    @Test (expected = NullPointerException.class)
    public void lookupNPE() {
        HashTable test = new HashTable();
        test.lookup(null);
    }

    @org.junit.Test
    public void size() {
        HashTable test =new HashTable();
        test.insert("Hello");
        test.insert("World");
        test.insert("!");
        assertEquals(3,test.size());
        test.delete("!");
        assertEquals(2,test.size());
        test.delete("hello");
        assertEquals(2, test.size());
    }

    @org.junit.Test
    public void capacity() {
        HashTable test =new HashTable(5);
        assertEquals(5, test.capacity());
        test.insert("Hello");
        test.insert("World");
        test.insert("!");
        test.insert("I");
        assertEquals(10,test.capacity());
        test.insert("am");
        test.insert("a");
        test.insert("UCSD");
        test.insert("Student.");
        assertEquals(20,test.capacity());
    }

    @org.junit.Test
    public void getStatsLog() {
        HashTable test =new HashTable(5);
        assertEquals("", test.getStatsLog());
        test.insert("Hello");
        test.insert("World");
        test.insert("!");
        test.insert("I");
        test.insert("am");
        assertEquals("Before rehash # 1: load factor 0.6, 1 collision(s).\n"
                , test.getStatsLog());
        test.insert("a");
        test.insert("UCSD");
        assertEquals("Before rehash # 1: load factor 0.6, 1 collision(s).\n" +
                "Before rehash # 2: load factor 0.6, 2 collision(s).\n", test.getStatsLog());
        test.insert("Student.");
    }

    @org.junit.Test
    public void testToString() {
        HashTable test =new HashTable(5);
        assertEquals("[null, null, null, null, null]", test.toString());
        test.insert("Hello");
        test.insert("World");
        test.insert("!");
        test.insert("I");
        assertEquals("[null, null, World, Hello, !, I, null, null, null, null]",
                test.toString());
        test.insert("am");
        test.insert("a");
        test.insert("UCSD");
        assertEquals("[null, null, World, Hello, !, I, null, a, UCSD, am, null, null," +
                " null, null, null, null, null, null, null, null]", test.toString());
        test.insert("Student.");

    }
}
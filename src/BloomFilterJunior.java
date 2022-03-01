/*
 * NAME: Gaoying Wang
 * PID: A16131629
 */

/**
 * This class insert the give value by changing the boolean value to true.
 * And this class can also use the three bollean values to search whether
 * the given value exists.
 *
 * @author Gaoying Wang
 * @since ${2022-02-26}
 */
public class BloomFilterJunior {

    /* Constants */
    private static final int MIN_INIT_CAPACITY = 50;
    private static final int BASE256_LEFT_SHIFT = 8;
    private static final int HORNERS_BASE = 27;

    /* Instance variables */
    private boolean[] table;

    public BloomFilterJunior(int capacity) {
        if (capacity < 50){
            throw new IllegalArgumentException();
        }
        table = new boolean[capacity];
    }

    public void insert(String value) {
        if (value == null) {
            throw new NullPointerException();
        }
        if (this.table[hashBase256(value)] == false){
            this.table[hashBase256(value)] = true;
        }
        if (this.table[hashCRC(value)] == false){
            this.table[hashCRC(value)] = true;
        }
        if (this.table[hashHorners(value)] == false){
            this.table[hashHorners(value)] = true;
        }
    }

    public boolean lookup(String value) {
        if (value == null) {
            throw new NullPointerException();
        }
        if (this.table[hashBase256(value)] == false){
            return false;
        }
        if (this.table[hashCRC(value)] == false){
            return false;
        }
        if (this.table[hashHorners(value)] == false){
            return false;
        }
        return true;
    }

    /**
     * Base-256 hash function.
     *
     * @param value string to hash
     * @return hash value
     */
    private int hashBase256(String value) {
        int hash = 0;
        for (char c : value.toCharArray()) {
            hash = ((hash << BASE256_LEFT_SHIFT) + c) % table.length;
        }
        return Math.abs(hash % table.length);
    }

    /**
     * Simplified CRC hash function.
     *
     * @param value string to hash
     * @return hash value
     */
    private int hashCRC(String value) {
        int hashvalue = 0;
        for (int i = 0; i < value.length(); i++) {
            int leftShiftedValue = hashvalue << 5;
            int rightShiftedValue = hashvalue >>> 27;
            hashvalue = (leftShiftedValue | rightShiftedValue) ^ (value.charAt(i));
        }
        return Math.abs(hashvalue) % this.table.length;
    }

    /**
     * Horner's hash function.
     *
     * @param value string to hash
     * @return hash value
     */
    private int hashHorners(String value) {
        int hash = 0;
        for (char c : value.toCharArray()) {
            hash = (hash * HORNERS_BASE + c) % table.length;
        }
        return Math.abs(hash % table.length);
    }
}

/*
 * Name: Gaoying Wang
 * PID: A16131629
 */

import java.util.Arrays;

/**
 * This HashTable inserts the element to certain index in the array by
 * using the given function.
 * 
 * @author Gaoying Wang
 * @since ${2022-02-24}
 */
public class HashTable implements IHashTable {

    /* the bridge for lazy deletion */
    private static final String BRIDGE = new String("[BRIDGE]".toCharArray());

    /* instance variables */
    private int size; // number of elements stored
    private String[] table; // data table
    private int rehash_count = 1;
    private int num_collision = 0;
    private String StatString = "";

    public HashTable() {
        this.size= 0;
        this.table = new String[15];
    }

    public HashTable(int capacity) {
        this();
        if (capacity < 5){
            throw new IllegalArgumentException();
        }
        this.table = new String[capacity];
    }

    @Override
    public boolean insert(String value) {
        if (value == null) {
            throw new NullPointerException();
        }
        if (this.lookup(value) == true) {
            return false;
        }
        double loadfactor = (double) this.size()/this.capacity();
        if (loadfactor > 0.55) {
            this.rehash();
        }
        int count = 0;
        while (this.table[(hashString(value) + count ) % this.capacity()]
                != null) {
            if (this.table[(hashString(value) + count ) % this.capacity()]
                    == BRIDGE){
                break;
            }
            count++;
            num_collision++;
        }
        this.table[(hashString(value) + count) % this.capacity()] = value;
        this.size++;
        return true;
    }

    @Override
    public boolean delete(String value) {
        if (value == null) {
            throw new NullPointerException();
        }
        if (this.lookup(value) == false){
            return false;
        }
        int count = 0;
        while (this.table[(hashString(value) + count ) % this.capacity()]
                != null) {
            count++;
        }
        this.table[(hashString(value) + count) % this.capacity()] = BRIDGE;
        this.size--;
        return true;
    }

    @Override
    public boolean lookup(String value) {
        if (value == null) {
            throw new NullPointerException();
        }
        int count = 0;
        while (this.table[(hashString(value) + count ) % this.capacity()]
                != null) {
            if (this.table[(hashString(value) + count ) % this.capacity()]
                    .equals(value)) {
                return true;
            } else {
                count++;
            }
        }
        return false;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public int capacity() {
        return this.table.length;
    }

    public String getStatsLog() {
        return StatString;
    }

    private void rehash() {
        double load_factor = (double) this.size()/this.capacity();
        StatString = StatString + "Before rehash # " + rehash_count + ": load factor " +
                load_factor + ", " + num_collision +
                " collision(s).\n";
        this.num_collision = 0;
        String[] new_array = new String[this.table.length*2];
        String[] old_array = new String[this.table.length];
        for (int i = 0; i < this.table.length; i++) {
            old_array[i] = this.table[i];
        }
        this.table = new_array;
        for (int i = 0; i < old_array.length; i++) {
            if (old_array[i] != null && old_array[i] != BRIDGE) {
                this.insert(old_array[i]);
                this.size--;
            }
        }
        rehash_count++;
    }

    private int hashString(String value) {
        int hashvalue = 0;
        for (int i = 0; i < value.length(); i++) {
            int leftShiftedValue = hashvalue << 5;
            int rightShiftedValue = hashvalue >>> 27;
            hashvalue = (leftShiftedValue | rightShiftedValue) ^ (value.charAt(i));
        }
        return Math.abs(hashvalue) % this.capacity();
    }

    /**
     * Returns the string representation of the hash table.
     * This method internally uses the string representation of the table array.
     * DO NOT MODIFY. You can use it to test your code.
     *
     * @return string representation
     */
    @Override
    public String toString() {
        return Arrays.toString(table);
    }
}

/*
 * NAME: Gaoying Wang
 * PID: A16131629
 */

import java.io.*;
import java.util.Objects;
import java.util.Scanner;

/**
 * This uses the hash table to find the frequency of a given word.
 *
 * @author Gaoying Wang
 * @since ${2022-02-25}
 */
public class DocumentFrequency {
    private File file;
    private HashTable[] HashTableList;
    private String[] record_list;

    public DocumentFrequency(String path) throws IOException {
        this.file = new File(path);
        int numDoc = 0;
        HashTable length = new HashTable();
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                length.insert(scanner.nextLine().trim().toLowerCase());
            }
            scanner.close();
        } catch (FileNotFoundException e) {
                throw new IOException();
            }
        numDoc = length.size();
        HashTableList = new HashTable[numDoc];
        Scanner scanner = new Scanner(file);
        int count = 0;
        while (scanner.hasNextLine()) {
            HashTable record = new HashTable();
            record_list = scanner.nextLine().trim().toLowerCase().split(" ");
            for (int i = 0; i < record_list.length; i++) {
                record.insert(record_list[i]);
                record.insert("quick");
            }
            HashTableList[count]=record;
            count++;
        }
        scanner.close();
    }

    public int numDocuments() {
        return HashTableList.length;
    }

    public int query(String word) {
        int count = 0;
        for (int i = 0; i < HashTableList.length; i++) {
            if (HashTableList[i].lookup(word)) {
                count++;
            }
        }
        return count;
    }

}

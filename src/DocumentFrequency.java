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
    private HashTable record;
    private String[][] record_list;

    public DocumentFrequency(String path) throws IOException {
        file = new File(path);
        record=new HashTable();
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                record.insert(scanner.nextLine().trim().toLowerCase());
            }
            scanner.close();
        } catch (FileNotFoundException e) {
                throw new IOException();
            }
        record_list = new String[record.size()][];
        Scanner scanner = new Scanner(file);
        for (int i = 0; i < record_list.length; i++) {
            record_list[i] = scanner.nextLine().trim().toLowerCase().split(" ");
        }
        scanner.close();
    }

    public int numDocuments() {
        return record.size();
    }

    public int query(String word) {
        int count = 0;
        for (int i = 0; i < record_list.length; i++) {
            for (int j = 0; j < record_list[i].length; j++){
                if (Objects.equals(record_list[i][j], word.toLowerCase())) {
                    count++;
                    break;
                }
            }
        }
        return count;
    }

}

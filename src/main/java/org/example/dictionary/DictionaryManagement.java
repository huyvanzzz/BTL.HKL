package org.example.dictionary;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.Scanner;

public class DictionaryManagement extends Dictionary {
    public static void insertFromCommandline() {
        Scanner sc = new Scanner(System.in);
        System.out.println("số lượng từ vựng: ");
        int Quantity = sc.nextInt();
        sc.nextLine();
        for (int i = 0; i < Quantity; i++) {
            System.out.println("Nhập từ tiếng Anh: ");
            String wordE = sc.nextLine();
            System.out.println("Nhập giải thích bằng tiếng Việt: ");
            String def = sc.nextLine();
            Word word = new Word(wordE, def);
            dictionary.wordArrayList.add(word);
        }
        sc.close();
    }

    public static void insertFromFile(){
        dictionary.addWord();

    }

    public static void dictionaryLookup(String vocabulary) {
        for (Word word : dictionary.wordArrayList) {
            if (vocabulary.equalsIgnoreCase(word.getWord_target())) {
                System.out.println(word.getWord_explain());
            } else {
                System.out.println("Invalid word");
            }
        }
    }

    public static void addword(Word a) {
        dictionary.wordArrayList.add(a);
    }

    public static void fix(Word a) {
        Iterator<Word> iterator = dictionary.wordArrayList.iterator();
        while (iterator.hasNext()) {
            // iterator.next() là 1 giá trị word trong mảng
            if (a.getWord_target().equals(iterator.next().getWord_target())) {
                iterator.next().setWord_explain(a.getWord_explain());
            } else {
                System.out.println("Invalid word");
            }
        }
    }

    public static void delete(String a) {
        String z = a.toLowerCase();
        boolean delete = dictionary.wordArrayList.removeIf(word -> {
            if (word.getWord_target().equalsIgnoreCase(z)) {
                return true;
            } else {
                return false;
            }
        });
        if (!(delete)) {
            System.out.println("Invalid work");
        }
    }

    public static void dictionaryExportToFile() {
        try {
            FileWriter fileWriter = new FileWriter("data/dictionaryExportToFile.txt");
            for (Word word : dictionary.wordArrayList) {
                fileWriter.write(word.getWord_target() + "\t" + word.getWord_explain());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

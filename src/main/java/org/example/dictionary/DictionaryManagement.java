package org.example.dictionary;

import javafx.collections.ObservableList;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Scanner;

public class DictionaryManagement extends Dictionary {
    protected static Trie trie = new Trie();
    public static void sortList() {
        dictionary.wordArrayList.sort(new Comparator<Word>() {
            @Override
            public int compare(Word o1, Word o2) {
                return o1.getWord_target().compareToIgnoreCase(o2.getWord_target());
            }
        });
    }

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

    public static void insertFromFile() {
        dictionary.addWord();
    }

    public static void addWordToTrie() {
        for (Word word : dictionary.wordArrayList) {
            trie.insert(word.getWord_target());
        }
    }

    public static boolean dictionaryLookup(String vocabulary) {
        if (vocabulary == null) {
            return false;
        }
        for (Word word : dictionary.wordArrayList) {
            if (vocabulary.equalsIgnoreCase(word.getWord_target())) {
                return true;
            }
        }
        return false;
    }

    public static void addword(Word a) {
        a.setWord_target(a.getWord_target().toLowerCase());
        dictionary.wordArrayList.add(a);
        trie.insert(a.getWord_target());
    }

    public static void fix(String wordTarget, String wordExplain) {
        Word newWord = new Word(wordTarget.toLowerCase(), wordExplain);
        int index = dictionary.findWord(newWord);
        if (index >= 0) {
            dictionary.wordArrayList.set(index, newWord);
        } else {
            System.out.println("Từ cần sửa không tồn tại trong từ điển.");
        }
    }
    public static void delete(String a) {
        int index = dictionary.findWord(new Word(a.toLowerCase(), null));
        if (index >= 0) {
            dictionary.wordArrayList.remove(index);
            trie.delete(a);
        } else {
            System.out.println("Từ không tồn tại trong từ điển.");
        }
    }

    public static void dictionaryExportToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Word word : dictionary.wordArrayList) {
                writer.write(word.getWord_target() + "\t" + word.getWord_explain() + "\n");
            }
            System.out.println("Data has been exported to file successfully!");
        } catch (IOException e) {
            System.err.println("Error occurred while exporting data to file: " + e.getMessage());
        }
    }

}

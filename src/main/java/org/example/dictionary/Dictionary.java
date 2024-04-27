package org.example.dictionary;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;


public class Dictionary extends Word {
    public ArrayList<Word> wordArrayList = new ArrayList<>();
    public static Dictionary dictionary = new Dictionary();
    public static final String fileName = "dictionaryAdvanced.txt";
    public static final String tab = "\t";

    public static Dictionary getDictionary() {
        return dictionary;
    }

    public void addWord() {
        try {
            FileReader fileReader = new FileReader(fileName);
            Scanner sc = new Scanner(fileReader);
            while (sc.hasNextLine()) {
                String x = sc.nextLine();
                String word = x.split(tab)[0];
                String def = x.split(tab)[1];
                Word word1 = new Word(word, def);
                wordArrayList.add(word1);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int findWord(Word word) {
        return Collections.binarySearch(
                wordArrayList,
                word,
                new Compare()
        );
    }


}

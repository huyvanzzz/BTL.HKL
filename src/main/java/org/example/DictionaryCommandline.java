package org.example;

import java.util.Collections;
import java.util.Comparator;

public class DictionaryCommandline extends DictionaryManagement{
    public static void showAllWords(){
        Collections.sort(dictionary.wordArrayList, new Comparator<Word>() {
            @Override
            public int compare(Word o1, Word o2) {
                return o1.getWord_target().compareToIgnoreCase(o2.getWord_target());
            }
        });
        for(Word word : dictionary.wordArrayList){
            System.out.println(word);
        }
    }
    public static void dictionaryBasic(){
        insertFromCommandline();
        showAllWords();
    }

}

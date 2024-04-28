package org.example.dictionary;

import java.util.Comparator;

public class Compare implements Comparator<Word> {
    @Override
    public int compare(Word w1, Word w2) {
        return w1.getWord_target().compareToIgnoreCase(w2.getWord_target());
    }
}
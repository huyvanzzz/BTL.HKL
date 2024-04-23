package org.example.dictionary;

import java.util.Comparator;

public class comparator implements Comparator<Word> {
    @Override
    public int compare(Word w1, Word w2) {
        return w1.getWord_target().compareTo(w2.getWord_target());
    }
}
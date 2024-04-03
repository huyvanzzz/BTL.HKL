package org.example;

import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class DictionaryCommandline extends DictionaryManagement {
    public static void showAllWords() {
        Collections.sort(dictionary.wordArrayList, new Comparator<Word>() {
            @Override
            public int compare(Word o1, Word o2) {
                return o1.getWord_target().compareToIgnoreCase(o2.getWord_target());
            }
        });
        for (Word word : dictionary.wordArrayList) {
            System.out.println(word);
        }
    }

    public static void dictionaryBasic() {
        insertFromCommandline();
        showAllWords();
    }

    public static void dictionarySearcher(String x) {
        for (Word word: dictionary.wordArrayList){
            if(word.getWord_target().contains(x)){
                System.out.println(word);
            }
        }
    }

    /**
     * Welcome to My Application!
     * [0] Exit
     * [1] Add
     * [2] Remove
     * [3] Update
     * [4] Display
     * [5] Lookup
     * [6] Search
     * [7] Game
     * [8] Import from file
     * 2
     * [9] Export to file
     * Your action:
     */
    public static void dictionaryAdvanced() throws Exception {
        boolean check = true;
        while (check == true) {
            System.out.println("Welcome to My Application!");
            System.out.println("[0] Exit");
            System.out.println("[1] Add");
            System.out.println("[2] Remove");
            System.out.println("[3] Update");
            System.out.println("[4] Display");
            System.out.println("[5] Lookup");
            System.out.println("[6] Search");
            System.out.println("[7] Game");
            System.out.println("[8] Import from file");
            System.out.println("[9] Export to file");
            System.out.println("Your action: ");
            Scanner sc = new Scanner(System.in);
            int a = sc.nextInt();
            sc.nextLine();
            String x;
            String y;
            if (a == 0) {
                System.exit(0);
            }
            if (a == 1) {
                System.out.println("Add Word: ");
                x = sc.nextLine();
                y = sc.nextLine();
                Word z = new Word(x, y);
                DictionaryManagement.addword(z);
            }
            if (a == 2) {
                System.out.println("Delete Word: ");
                x = sc.nextLine();
                DictionaryManagement.delete(x);
            }
            if (a == 3) {
                System.out.println("Fix word: ");
                x = sc.nextLine();
                y = sc.nextLine();
                Word word = new Word(x, y);
                DictionaryManagement.fix(word);
            }
            if (a == 4) {
                System.out.println("Display ListWord: ");
                showAllWords();
            }
            if (a == 5) {
                System.out.println("Lookup: ");
                x = sc.nextLine();
                DictionaryManagement.dictionaryLookup(x);
            }
            if (a == 6) {
                System.out.println("Search: ");
                x = sc.nextLine();
                DictionaryCommandline.dictionarySearcher(x);
            }
            if (a == 7) {
                System.out.println("Start Game: ");
                int h = sc.nextInt();
                sc.nextLine();
                if(h==1){
                    ChooseTheRightWordGame.ChooseTheRightWord();
                }
                if(h==2){

                }
                if(h==3){

                }
            }
            if (a == 8) {
                System.out.println("Import from file: ");

            }
            if (a == 9) {
                System.out.println("Export to file: ");
                DictionaryManagement.dictionaryExportToFile();
            }
            if (a < 0 || a > 9) {
                System.out.println("Action not supported");
            }
            sc.close();
        }
    }
}

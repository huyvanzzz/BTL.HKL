package org.example;

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
    }

    public static void insertFromFile() throws Exception {
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
    public static void addword(Word a){
      dictionary.wordArrayList.add(a);
    }
    public static void fix(Word a){

    }
    public static void delete(Word a){
        dictionary.wordArrayList.remove(a);
    }
}

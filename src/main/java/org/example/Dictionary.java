package org.example;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;


public class Dictionary extends Word{
    public ArrayList<Word> wordArrayList = new ArrayList<>();
    public static Dictionary dictionary = new Dictionary();
    public static final String fileName = "data/dictionaryAdvanced.txt";
    public static final String tab = "\t";
    public void addWord() throws Exception{

        FileReader fileReader = new FileReader(fileName);
        Scanner sc = new Scanner(fileReader);
        while (sc.hasNextLine()){
            String x= sc.nextLine();
            String word = x.split(tab)[0];
            String def = x.split(tab)[1];
            Word word1 = new Word(word,def);
            wordArrayList.add(word1);
        }
        sc.close();
    }

}

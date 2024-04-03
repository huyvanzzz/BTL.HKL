package org.example;

import java.io.FileReader;
import java.util.Scanner;

public class ChooseTheRightWordGame {
    static String A, B, C, D, E;

    public static boolean check(String a, String b) {
        if (a.equals(b)) {
            return true;
        }
        return false;
    }

    public static void ChooseTheRightWord() throws Exception {
        FileReader fileReader = new FileReader("data/DapAn.txt");
        Scanner sc = new Scanner(fileReader);
        while (sc.hasNextLine()) {
            String x = sc.nextLine();
            String[] y = x.split("\t");
            A = y[1];
            B = y[2];
            C = y[3];
            D = y[4];
            E = y[5];
            System.out.println(y[0]);
        }
    }
}

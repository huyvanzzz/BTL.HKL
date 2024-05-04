package org.example.dictionary;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class ChooseTheRightWordGame {
    private ArrayList<String> ques = new ArrayList<>();
    private ArrayList<String> ans = new ArrayList<>();
    private ArrayList<ArrayList<String>> options = new ArrayList<>();

    private static final String Path_ques = "data/ques.txt";
    private static final String Path_ans = "data/ans.txt";
    private static final String Path_option = "data/option.txt";
    private static Scanner sc;

    public ArrayList<String> getQues() {
        return ques;
    }

    public ArrayList<String> getAns() {
        return ans;
    }

    public ArrayList<ArrayList<String>> getOptions() {
        return options;
    }

    public void readQuestion() {
        try {
            FileReader fileReader = new FileReader(Path_ques);
            sc = new Scanner(fileReader);
            while (sc.hasNextLine()) {
                ques.add(sc.nextLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
            // Xử lý ngoại lệ khi không tìm thấy tệp
        }
    }

    public void readOption() {
        try {
            FileReader fileReader = new FileReader(Path_option);
            sc = new Scanner(fileReader);
            while (sc.hasNextLine()) {
                ArrayList<String> arrayList = new ArrayList<>(Arrays.asList(sc.nextLine().split("/")));
                options.add(arrayList);
            }
        } catch (IOException e) {
            e.printStackTrace();
            // Xử lý ngoại lệ khi không tìm thấy tệp
        }
    }

    public void readAnswer() {
        try {
            FileReader fileReader = new FileReader(Path_ans);
            sc = new Scanner(fileReader);
            while (sc.hasNextLine()) {
                String a = sc.nextLine();
                ans.add(a);
            }
        } catch (IOException e) {
            e.printStackTrace();
            // Xử lý ngoại lệ khi không tìm thấy tệp
        }
    }

    public int Random(int a) {
        Random random = new Random();
        return random.nextInt(a); // trả về giá trị từ 0  đến a -1;
    }
}

package org.example.dictionary;

import java.util.HashMap;
import java.util.Map;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Trie {
    // Map lưu trữ các ký tự con và Trie tương ứng
    private HashMap<Character, Trie> children;
    // Giá trị của nút Trie
    private String value;
    // Cờ cho biết liệu từ đã được thêm vào Trie hay chưa
    private boolean isAdded = false;

    // Constructor mặc định
    public Trie() {
        this(null);
    }

    // Constructor với tham số value
    private Trie(String value) {
        this.value = value;
        children = new HashMap<>();
    }

    // Phương thức thêm một nút con
    private void addChild(char character) {
        String str;
        if (this.value == null) {
            str = Character.toString(Character.toLowerCase(character));
        } else {
            str = this.value + Character.toString(Character.toLowerCase(character));
        }
        children.put(Character.toLowerCase(character), new Trie(str));
    }

    // Phương thức chèn một từ vào Trie
    public void insert(String wordToInsert) {
        if (wordToInsert == null) {
            return;
        }
        Trie currentNode = this;
        for (char c : wordToInsert.toCharArray()) {
            char lowerCaseChar = Character.toLowerCase(c);
            if (!currentNode.children.containsKey(lowerCaseChar)) {
                currentNode.addChild(lowerCaseChar);
            }
            currentNode = currentNode.children.get(lowerCaseChar);
        }
        currentNode.isAdded = true;
    }

    // Phương thức tìm kiếm các từ có tiền tố là prefix
    public ObservableList<String> autoSearch(String prefix) {
        Trie currentNode = this;
        for (char c : prefix.toCharArray()) {
            char lowerCaseChar = Character.toLowerCase(c);
            if (!currentNode.children.containsKey(lowerCaseChar)) {
                return null;
            }
            currentNode = currentNode.children.get(lowerCaseChar);
        }
        return currentNode.getAllWords();
    }

    // Phương thức đệ quy trả về tất cả các từ dưới dạng danh sách quan sát được
    private ObservableList<String> getAllWords() {
        ObservableList<String> wordList = FXCollections.observableArrayList();
        if (this.isAdded) {
            wordList.add(this.value);
        }
        for (Map.Entry<Character, Trie> entry : children.entrySet()) {
            Trie childNode = entry.getValue();
            ObservableList<String> words = childNode.getAllWords();
            wordList.addAll(words);
        }
        return wordList;
    }

    // Phương thức xóa một từ khỏi Trie
    public void delete(String wordToDelete) {
        Trie currentNode = this;
        for (char c : wordToDelete.toCharArray()) {
            char lowerCaseChar = Character.toLowerCase(c);
            if (!currentNode.children.containsKey(lowerCaseChar)) {
                return;
            }
            currentNode = currentNode.children.get(lowerCaseChar);
        }
        currentNode.isAdded = false;
    }
}
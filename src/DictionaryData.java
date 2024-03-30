import javafx.scene.web.WebView;

import javax.swing.text.html.ListView;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class DictionaryData {
  private Map<String,Word> dictionary = new TreeMap<>();
  private static final String html = "<html>";
  private static final String file = "data/E_v.txt";
  private WebView
  public void addWord() throws Exception{
      FileReader fileReader = new FileReader(file);
      BufferedReader bufferedReader = new BufferedReader(fileReader);
      String line;
      while ((line = bufferedReader.readLine())!= null){
          String[] past = line.split(html);
          String worD = past[0];
          Word word = new Word(worD,html+past[1]);
          dictionary.put(worD,word);
      }
  }

}

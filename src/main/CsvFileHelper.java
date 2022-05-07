package main;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CsvFileHelper {
    public static String COMMA_DELIMITER = ",";

    public static List<List<String>> getCSV(){ // Nous utilisions un fichier CSV a la base. Etant sur vscode, cela causais des soucis lors des tests
        List<List<String>> records = new ArrayList<>();
        String input= "Edouard,1,6,0\nCaroline,1,8,4\nDelphine,1,9,1\nAlexandre,1,11,1\nFabrice,1,12,3\nBernard,1,14,0\nWilliam,3,15,13\nXavier,3,14,18\nVincent,3,12,10\nZoé,2,16,17\nYlann,2,12,13\nFictif1,2,1,0";
        String[] lines = input.split("\n");
        for(int i = 0;i<lines.length;i++){
            String[] values = lines[i].split(COMMA_DELIMITER);
            records.add(Arrays.asList(values));
        }
        return records;
    }
}
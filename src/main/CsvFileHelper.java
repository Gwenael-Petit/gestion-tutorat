package main;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CsvFileHelper {
    public static String COMMA_DELIMITER = ",";

    public static List<List<String>> getCSV(){ // Nous utilisions un fichier CSV a la base. Etant sur vscode, cela causais des soucis lors des tests
        List<List<String>> records = new ArrayList<>();
        String input= "Edouard,1,6,0,0\nCaroline,1,8,0,0\nDelphine,1,9,0,0\nAlexandre,1,11,0,0\nFabrice,1,12,0,0\nBernard,1,14,0,0\nWilliam,3,15,3,0\nXavier,3,14,3,0\nVincent,3,12,3,0\nZoé,2,16,0,0\nYlann,2,12,0,0\nFictif1,2,1,0,0\n";
        String[] lines = input.split("\n");
        for(int i = 0;i<lines.length;i++){
            String[] values = lines[i].split(COMMA_DELIMITER);
            records.add(Arrays.asList(values));
        }
        return records;
    }
}
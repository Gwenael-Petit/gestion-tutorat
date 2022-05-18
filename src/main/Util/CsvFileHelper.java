package main.Util;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CsvFileHelper {
    public static String COMMA_DELIMITER = ",";

    public static List<List<String>> getCSV(){ // Nous utilisions un fichier CSV a la base. Etant sur vscode, cela causais des soucis lors des tests
        List<List<String>> records = new ArrayList<>();
        // Entête du CSV : Prenom,Année,Moyenne,Modifier
        // En premiére année modifier = absence
        // En deuxiéme et troisiéme année modifier = moyenne premiére année
        String input= "Edouard,1,6,0\nCaroline,1,8,4\nDelphine,1,9,1\nAlexandre,1,11,1\nFabrice,1,12,3\nBernard,1,14,0\nWilliam,3,15,13\nXavier,3,14,18\nVincent,3,12,10\nZoé,2,16,17\nYlann,2,12,13\nFictif1,2,1,0";
        String[] lines = input.split("\n");
        for(int i = 0;i<lines.length;i++){
            String[] values = lines[i].split(COMMA_DELIMITER);
            records.add(Arrays.asList(values));
        }
        return records;
    }

    public static List<List<String>> getCSV(String delimiter,String path) throws FileNotFoundException, IOException{
        List<List<String>> records = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(path));
        ArrayList<String> lines = new ArrayList<String>();
        while(br.ready()){
            lines.add(br.readLine());
        }
        for(int i = 0;i<lines.size();i++){
            String[] values = lines.get(i).split(COMMA_DELIMITER);
            records.add(Arrays.asList(values));
        }
        br.close();
        return records;
    }
}
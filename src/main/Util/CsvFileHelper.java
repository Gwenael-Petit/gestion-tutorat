package main.Util;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import main.Subject;
import main.Users.Person;
import main.Users.Student;
import main.Users.Teacher;
import main.Users.Tutor;
import main.Users.Tutored;

public abstract class CsvFileHelper {
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

    public static List<List<String>> getCSV(String path, String delimiter) throws IOException {
        List<List<String>> records = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(delimiter);
                List<String> list = new LinkedList<>(Arrays.asList(values));
                records.add(list);
            }
        }
        return records;
    }

    public static void writeCSV(List<List<String>> res, String fileName) throws IOException {
        FileWriter writer = new FileWriter(fileName);
        writer.write(res.toString().replace("], ", "\n").replace("[", "").replace("]", ""));
        writer.close();
    }

    public static ArrayList<Person> csvToList(String path, String delimiter, ArrayList<Subject> subjects) throws IOException{
        List<List<String>> csv = getCSV(path,delimiter);
        ArrayList<Person> res = new ArrayList<>();
        for (int i = 0; i < csv.size(); i++) {
            List<String> line = csv.get(i);
            if(line.get(line.size()-1).equals("-1")){
                ArrayList<Subject> list = new ArrayList<>();
                int idx = 3;
                while(idx<8){
                    for (int j = 0; j < subjects.size(); j++) {
                        if((subjects.get(j).getId()+"").equals(line.get(idx))){
                            list.add(subjects.get(j));
                        }
                    }
                    idx++;
                }
                Teacher tmp = new Teacher(line.get(1),line.get(0),line.get(0)+"."+line.get(1),line.get(2),list);
                res.add(tmp);
            }else{
                Student tmp;
                double[] tab=new double[5];
                for (int j = 0; j < 5; j++) {
                    tab[j] = Double.parseDouble(line.get(4+j));
                }
                if(line.get(line.size()-1) == "1"){
                    tmp = new Tutored(line.get(1), line.get(0), line.get(2), tab, line.get(line.size()-1));
                }else{
                    tmp = new Tutor(line.get(1),line.get(0),line.get(2),tab,line.get(line.size()-1));
                }
                res.add(tmp);
            }
        }
        return res;
    }
}
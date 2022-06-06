package main.Util;

import java.io.BufferedReader;
import java.io.FileReader;
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

    public static ArrayList<Person> csvToList(String path, String delimiter, ArrayList<Subject> subjects) throws IOException {
        List<List<String>> csv = getCSV(path, delimiter);
        ArrayList<Person> res = new ArrayList<>();
        for (int i = 0; i < csv.size(); i++) {
            List<String> line = csv.get(i);
            if (line.get(line.size() - 1).equals("-1")) {
                ArrayList<Subject> list = new ArrayList<>();
                int idx = 3;
                while (idx < 8) {
                    for (int j = 0; j < subjects.size(); j++) {
                        if ((subjects.get(j).getId() + "").equals(line.get(idx))) {
                            list.add(subjects.get(j));
                        }
                    }
                    idx++;
                }
                Teacher tmp = new Teacher(line.get(1), line.get(0), line.get(0) + "." + line.get(1), line.get(2), list);
                res.add(tmp);
            } else {
                Student tmp;
                double[] tab = new double[5];
                for (int j = 0; j < 5; j++) {
                    tab[j] = Double.parseDouble(line.get(4 + j));
                }
                if (line.get(3).equals("1")) {
                    tmp = new Tutored(line.get(1), line.get(0), line.get(2), tab, line.get(3), line.get(9));
                } else {
                    tmp = new Tutor(line.get(1), line.get(0), line.get(2), tab, line.get(3), line.get(9));
                }
                res.add(tmp);
            }
        }
        return res;
    }
}
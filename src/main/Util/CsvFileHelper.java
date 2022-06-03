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

/**
 * Classe qui gère les CSV contenants les donnees dur les etudiants et professeurs
 */
public abstract class CsvFileHelper {
	/*
	 * Delimiter entre chaques donnees du fichier CSV
	 */
    public static String COMMA_DELIMITER = ",";

    /**
     * Fonction qui retourne une ArrayList de donnees sur des etudiants a partir de donnees transmises
     * @return records : ArrayList qui contient les donnees de plusieurs etudiants tuteurs et tutores avec certaines donnes qui leurs sont propres (annee, moyenne, ...) 
     */
    public static List<List<String>> getCSV(){ // Nous utilisions un fichier CSV a la base. Sur vscode, cela cause des soucis lors des tests
        List<List<String>> records = new ArrayList<>();
        // Entete du CSV : Prenom,Annee,Moyenne,Modifier
        // En premiere annee modifier = absence
        // En deuxieme et troisieme annee modifier = moyenne premiere annee
        String input= "Edouard,1,6,0\nCaroline,1,8,4\nDelphine,1,9,1\nAlexandre,1,11,1\nFabrice,1,12,3\nBernard,1,14,0\nWilliam,3,15,13\nXavier,3,14,18\nVincent,3,12,10\nZoe,2,16,17\nYlann,2,12,13\nFictif1,2,1,0";
        String[] lines = input.split("\n");
        for(int i = 0;i<lines.length;i++){
            String[] values = lines[i].split(COMMA_DELIMITER);
            records.add(Arrays.asList(values));
        }
        return records;
    }

    /**
     * Fonction qui retourne une ArrayList de donnees sur des etudiants a partir d'un fichier CSV les contenants
     * @param path : chemin menant au CSV
     * @param delimiter : delimiter choisi pour passer d'une donnee a une autre
     * @return records : une ArrayList contenant des donnees sur les etudiants (tuteurs comme tutores)
     * @throws IOException
     */
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

    /**
     * Ecrit des données, données dans un chaine en parametres, dans un fichier
     * @param res : chaine contenant les donnees qui vont etre ecrites dans le fichier
     * @param fileName : nom du fichier CSV dans lequel on va ecrire les donnees
     * @throws IOException
     */
    public static void writeCSV(List<List<String>> res, String fileName) throws IOException {
        FileWriter writer = new FileWriter(fileName);
        writer.write(res.toString().replace("], ", "\n").replace("[", "").replace("]", ""));
        writer.close();
    }

    /**
     * Fonction qui recupere les donnees d'un fichier et en retire les informations sur les personnes pour les ajouter dans une ArrayList de Person
     * @param path : le chemin ou se trouve le fichier qui contient les donnees
     * @param delimiter : delimiter choisi pour passer d'une donnee a une autre
     * @param subjects : ArrayList des matieres disponibles pour le tutorat
     * @return res : une ArrayList de Person contenant des Person et leurs donnees
     * @throws IOException
     */
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
                if(line.get(3).equals("1")){
                    tmp = new Tutored(line.get(1), line.get(0), line.get(2), tab, line.get(3));
                }else{
                    tmp = new Tutor(line.get(1),line.get(0),line.get(2),tab,line.get(3));
                }
                res.add(tmp);
            }
        }
        return res;
    }
}
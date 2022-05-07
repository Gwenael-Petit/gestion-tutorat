package main;
public class Student implements Comparable<Student> {
    String nom;
    String prenom;
    double moyenne;
    public int annee;
    boolean fixed;

    public Student(String nom, String prenom, String moyenne, String annee) {
        int add =0;
        this.nom = nom;
        this.prenom = prenom;
        if (annee.equals("3")) {
            this.annee = 3;
            add = 10;
        } else if (annee.equals("2")) {
            this.annee = 2;
        } else {
            this.annee = 1;
        }
        this.moyenne = Double.parseDouble(moyenne) + add;
        fixed=false;
    }

    public Student(String nom, String prenom, String moyenne, String annee,String modifier) {   // Le modifier est le nombre d'absence en premiére année, dans les autres promotion c'est la moyenne de la premiére année de l'étudiant
        Double add =0.0;
        this.nom = nom;
        this.prenom = prenom;
        if (annee.equals("3")) {                            // Si troisiéme année on augmente artificiellement les moyenne pour prioriser l'affecation de troisiéme année
            this.annee = 3;
            add = 10.0+Double.parseDouble(modifier)*0.1;  // J'ajoute en bonus sa moyenne de premiére année. Si il avais des bonnes notes il devrais pouvoir aider plus facilement.                                 
        } else if (annee.equals("2")) {
            this.annee = 2;
            add=+Double.parseDouble(modifier)*0.1;
        } else {
            this.annee = 1;
            add = add + 0.1*Double.parseDouble(modifier);   // Si premiére année on augmente artificiellement la moyenne en fonction des absence/10 pour penaliser les absences
        }
        this.moyenne = Double.parseDouble(moyenne) + add;
        fixed=false;
    }

    public String toString() {
        return "[" + prenom + ";" + moyenne + ";" + annee + "]";
    }

    public int compareTo(Student d) {
        if(d.annee == 1){
            return (int) (this.moyenne - d.moyenne);
        }else{
            if(d.moyenne>=5000 || moyenne>=5000){
                return (int) -(this.moyenne - d.moyenne);
            }
            if (annee == 3 && d.annee != 3) {
                return -1;
            } else if (d.annee == 3 && annee != 3) {
                return 1;
            } else {
                return (int) -(this.moyenne - d.moyenne);
            }
        }
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Student)){
            return false;
        }
        Student tmp = (Student) obj;
         
        return this.annee == tmp.annee && this.moyenne == tmp.moyenne && this.nom == tmp.nom && this.prenom == tmp.prenom;
    }
}

package main;
public class Student implements Comparable<Student> {
    String nom;
    String prenom;
    double moyenne;
    public int annee;

    public Student(String nom, String prenom, String moyenne, String annee) {
        this.nom = nom;
        this.prenom = prenom;
        if (annee.equals("3")) {
            this.annee = 3;
        } else if (annee.equals("2")) {
            this.annee = 2;
        } else {
            this.annee = 1;
        }
        int add =0;
        if(this.annee == 3){
            add = 10;
        }
        this.moyenne = Double.parseDouble(moyenne) + add;
    }

    public String toString() {
        return "[" + prenom + ";" + moyenne + ";" + annee + "]";
    }

    public int compareTo(Student d) {
        if(d.annee == 1){
            return (int) (this.moyenne - d.moyenne);
        }else{
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

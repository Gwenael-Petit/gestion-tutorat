package main.Users;

public class Student extends Person implements Comparable<Student> {
    private double score;
    private final Level LEVEL;
    private boolean fixed;

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getScore() {
        return this.score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public Level getLevel() {
        return this.LEVEL;
    }

    public boolean isFixed() {
        return this.fixed;
    }

    public boolean getFixed() {
        return this.fixed;
    }

    public void setFixed(boolean fixed) {
        this.fixed = fixed;
    }



    public Student(String nom, String prenom,String login, String password, String moyenne, String annee) {
        super(nom,prenom,login,password);
        int add =0;
        if (annee.equals("3")) { // Si on avais plus de 3 année on aurais pu faire un Integer.parseInteger(annee) et faire les test plus intéligement
            this.LEVEL = Level.third;
            add = 10;
        } else if (annee.equals("2")) {
            this.LEVEL = Level.second;
        } else {
            this.LEVEL = Level.first;
        }
        this.score = Double.parseDouble(moyenne) + add;
        fixed=false;
    }

    public Student(String nom, String prenom, String login, String password, String moyenne, String annee,String modifier) {   // Le modifier est le nombre d'absence en premiére année, dans les autres promotion c'est la moyenne de la premiére année de l'étudiant
        super(nom,prenom,login,password);
        Double add =0.0;
        if (annee.equals("3")) {                            // Si troisiéme année on augmente artificiellement les moyenne pour prioriser l'affecation de troisiéme année
            this.LEVEL = Level.third;
            add = 10.0+Double.parseDouble(modifier)*0.1;  // J'ajoute en bonus sa moyenne de premiére année. Si il avais des bonnes notes il devrais pouvoir aider plus facilement.                                 
        } else if (annee.equals("2")) {
            this.LEVEL = Level.second;
            add=+Double.parseDouble(modifier)*0.1;
        } else {
            this.LEVEL = Level.first;
            add = add + 0.1*Double.parseDouble(modifier);   // Si premiére année on augmente artificiellement la moyenne en fonction des absence/10 pour penaliser les absences
        }
        this.score = Double.parseDouble(moyenne) + add;
        fixed=false;
    }

    public String toString() {
        return "[" + name + ";" + score + ";" + LEVEL + "]";
    }

    public int compareTo(Student d) { // Nous permet d'utiliser Collections.sort(Arraylist<Student>)
        if(d.LEVEL == Level.first){
            return (int) (this.score - d.score);
        }else{
            if(d.score>=5000 || score>=5000){
                return (int) -(this.score - d.score);
            }
            if (LEVEL == Level.third && d.LEVEL != Level.third) {
                return -1;
            } else if (d.LEVEL == Level.third && LEVEL != Level.third) {
                return 1;
            } else {
                return (int) -(this.score - d.score);
            }
        }
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Student)){
            return false;
        }
        Student tmp = (Student) obj;
         
        return this.LEVEL == tmp.LEVEL && this.score == tmp.score && this.lastName == tmp.lastName && this.name == tmp.name;
    }
}

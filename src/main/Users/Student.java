package main.Users;

public class Student extends Person implements Comparable<Student> {
    private double[] score;
    private final Level LEVEL;
    private boolean[] fixed;
    private int tmpSub;
    private int modifier;

    public String getLastName() {
        return this.lastName;
    }

    public void setModifier(int modifier) { this.modifier = modifier; }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getModifier() {
        return modifier;
    }

    public void setTmp(int tmp) {
        this.tmpSub = tmp;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double[] getScore() {
        return this.score;
    }

    public void setScore(double[] score) {
        this.score = score;
    }

    public Level getLevel() {
        return this.LEVEL;
    }

    public boolean[] getFixed() {
        return this.fixed;
    }

    public void setFixed(boolean fixed,int subjectID) {
        this.fixed[subjectID] = fixed;
    }



    public Student(String nom, String prenom,String login, String password, double[] moyenne, String annee) {
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
        this.score = moyenne;
        for (double d : moyenne) {
            d = d+add;
        }
        fixed=new boolean[5];
        for (int i = 0; i < 5; i++) {
            fixed[i]=false;
        }
    }

    public Student(String nom, String prenom, String password, double[] moyenne, String annee) {
        super(nom,prenom,prenom.toLowerCase()+"."+nom.toLowerCase()+".etu",password);
        int add =0;
        if (annee.equals("3")) { // Si on avais plus de 3 année on aurais pu faire un Integer.parseInteger(annee) et faire les test plus intéligement
            this.LEVEL = Level.third;
            add = 10;
        } else if (annee.equals("2")) {
            this.LEVEL = Level.second;
        } else {
            this.LEVEL = Level.first;
        }
        this.score = moyenne;
        for (double d : moyenne) {
            d = d+add;
        }
        fixed=new boolean[5];
        for (int i = 0; i < 5; i++) {
            fixed[i]=false;
        }
    }

    public Student(String nom, String prenom, String login, String password, double[] moyenne, String annee,String modifier) {   // Le modifier est le nombre d'absence en premiére année, dans les autres promotion c'est la moyenne de la premiére année de l'étudiant
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
        this.score = moyenne;
        for (double d : moyenne) {
            d = d+add;
        }
        fixed=new boolean[5];
        for (int i = 0; i < 5; i++) {
            fixed[i]=false;
        }
    }

    public Student(String nom, String prenom, String password, double[] moyenne, String annee,String modifier) {   // Le modifier est le nombre d'absence en premiére année, dans les autres promotion c'est la moyenne de la premiére année de l'étudiant
        this(nom, prenom, prenom+"."+nom+".etu", password,moyenne,annee, modifier);
    }

    public String toString() {
        String str = "[ "+score[0];
        for (int i = 1; i < score.length; i++) {
            str = str+", "+score[i];
        }
        str = str+" ]";
        return "[Login : " + name+"."+lastName+".etu ; Score : " + str + " ; Level = " + LEVEL + "]";
    }

    public String toString(int subjectId) {
        return "[Login : " + name+"."+lastName+".etu ; Score : " + score[subjectId] + " ; Level = " + LEVEL + "]";
    }


    // Il faut au préalable changer la valeur de tmpSub pour pointer sur la bonne matiére avant d'utiliser cette methode, avec Collections.sort() par exemple.
    public int compareTo(Student d) { // Nous permet d'utiliser Collections.sort(Arraylist<Student>)
        if(d.LEVEL == Level.first){
            return (int) (this.score[tmpSub] - d.score[tmpSub]);
        }else{
            if(d.score[tmpSub]>=5000 || score[tmpSub]>=5000){
                return (int) -(this.score[tmpSub] - d.score[tmpSub]);
            }
            if (LEVEL == Level.third && d.LEVEL != Level.third) {
                return -1;
            } else if (d.LEVEL == Level.third && LEVEL != Level.third) {
                return 1;
            } else {
                return (int) -(this.score[tmpSub] - d.score[tmpSub]);
            }
        }
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Student)){
            return false;
        }
        Student tmp = (Student) obj;

        boolean s = true;
        boolean f = true;
        for (int i = 0; i < score.length; i++) {
            if(tmp.score[i]!=score[i]){
                s=false;
            }if(tmp.fixed[i]!=fixed[i]){
                f=false;
            }
        }
         
        return this.LEVEL == tmp.LEVEL && this.lastName.equals(tmp.lastName) && this.name.equals(tmp.name) && s && f && this.login.equals(tmp.login) && this.modifier == tmp.modifier && this.password.equals(tmp.password);
    }

    
}

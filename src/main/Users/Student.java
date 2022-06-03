package main.Users;

public class Student extends Person implements Comparable<Student> {
    private double[] score;
    private final Level LEVEL;
    private boolean[] fixed;
    private int tmpSub;
    private int modifier;

    /**
     *Renvoie le nom de famille d'un étudiant
     *@return lastName soit le nom de famille
     */
    public String getLastName() {
        return this.lastName;
    }

    /**
     * Modifie le nom de famille d'un étudiant
     * @param lastName: nouveau nom de famille
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Renvoie le nombre d'absences pour un élèvé de 1ère année
     * Sinon, renvoie la moyenne de première année
     * @return modifier: nombre d'absences ou moyenne année 1
     */
    public int getModifier() {
        return modifier;
    }

    public void setTmp(int tmp) {
        this.tmpSub = tmp;
    }

    /**
     * Renvoie le prénom d'un étudiant
     * @return name: le prénom
     */
    public String getName() {
        return this.name;
    }

    /**
     * Modifie le prénom d'un étudiant
     * @param name: nouveau prénom
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Renvoie le tableau des moyennes de l'étudiant
     * @return score: tableau des moyennes
     */
    public double[] getScore() {
        return this.score;
    }

    /**
     * Change le tableau des moyenne de l'étudiant
     * @param score: nouveau tableau de moyenne
     */
    public void setScore(double[] score) {
        this.score = score;
    }

    /**
     * Renvoie le niveau détude de l'étudiant en nombre d'année de formation suvie
     * @return LEVEL: l'année d'étude
     */
    public Level getLevel() {
        return this.LEVEL;
    }

    /**
     * Permet de savoir si un étudiant est fixé (affecté) ou non pour les différentes matières
     * @return fixed: tableau de fixé(true) ou non(false)
     */
    public boolean[] getFixed() {
        return this.fixed;
    }

    /**
     * Permet de fixer l'affectation d'un étduaint pour une matière donnée
     * @param fixed: boolean fixé ou non
     * @param subjectID: ID de la matière
     */
    public void setFixed(boolean fixed,int subjectID) {
        this.fixed[subjectID] = fixed;
    }



    /**
     * Permet de créer un étudiant grâce aux informations qui le concernent
     * Utilisation du constructeur de Person
     * On utilise l'année en chiffre pour lui atribuer l'année correspondante de l'enum Level
     * On augemente la moyenne des 3èmes années pour les favoriser dans l'affectaion des tuteurs
     * @param nom: nom de l'étudiant
     * @param prenom: prénom de l'étudant
     * @param login: identifiant de l'étudiant
     * @param password: mot de passe de l'étudiant
     * @param moyenne: tableau des moyennes de l'étudiant
     * @param annee: année d'étude au sein de la formation
     */
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


    /**
     * Permet de créer un étudiant grâce aux informations qui le concernent
     * Utilisation du constructeur de Person
     * On utilise l'année en chiffre pour lui atribuer l'année correspondante de l'enum Level
     * On augemente la moyenne des 3èmes années pour les favoriser dans l'affectaion des tuteurs
     * @param nom: nom de l'étudiant
     * @param prenom: prénom de l'étudant
     * @param moyenne: tableau des moyennes de l'étudiant
     * @param annee: année d'étude au sein de la formation
     */
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


    /**
     * Permet de créer un étudiant grâce aux informations qui le concernent
     * Utilisation du constructeur de Person
     * On utilise l'année en chiffre pour lui atribuer l'année correspondante de l'enum Level
     * On augemente la moyenne des 3èmes années pour les favoriser dans l'affectaion des tuteurs
     * @param nom: nom de l'étudiant
     * @param prenom: prénom de l'étudant
     * @param login: identifiant de l'étudiant
     * @param password: mot de passe de l'étudiant
     * @param moyenne: tableau des moyennes de l'étudiant
     * @param annee: année d'étude au sein de la formation
     * @param modifier: nombre d'absence de lédtudiant si 1ère année sinon moyenne de 1ère année
     */
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

    /**
     * Renvoie l'étudiant sous la forme [Login : prenom.nom.etu ; Score : [0, 0, 0, 0, 0] ; Level = first];
     */
    public String toString() {
        String str = "[ "+score[0];
        for (int i = 1; i < score.length; i++) {
            str = str+", "+score[i];
        }
        str = str+" ]";
        return "[Login : " + name+"."+lastName+".etu ; Score : " + str + " ; Level = " + LEVEL + "]";
    }

    /**
     * Renvoie l'étudiant sous la forme [Login : prenom.nom.etu ; Score : 0 ; Level = first];
     * @param subjectID: matière concernée
     */
    public String toString(int subjectId) {
        return "[Login : " + name+"."+lastName+".etu ; Score : " + score[subjectId] + " ; Level = " + LEVEL + "]";
    }


    /**
     * Permet de comparer un étudiant à un autre sur plusieurs critères comme l'année ou la moyenne
     * @return int: un entier positif si l'étudiant initial est meilleur ou sinon négatif
     */
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

    /**
     * Permet de véridier l'aglité entre un étudiant et une instance d'Object
     */
    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Student)){
            return false;
        }
        Student tmp = (Student) obj;
         
        return this.LEVEL == tmp.LEVEL && this.score == tmp.score && this.lastName == tmp.lastName && this.name == tmp.name;
    }

    
}

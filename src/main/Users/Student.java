package main.Users;

/**
 * Un etudiant est une Person possedant des notes
 * Il peut etre un tuteur ou un tutore
 */
public class Student extends Person implements Comparable<Student> {

    /**
     * Tableau de moyenne, avec une moyenne par matiere
     */
    private double[] score;

    /**
     * Niveau de la formation en annee
     */
    private final Level LEVEL;

    /**
     * Statut de fication de l'etudiant
     */
    private boolean[] fixed;

    /**
     * ID de matiere, utilisee pour regarder les notes
     */ 
    private int tmpSub;

    /**
     * Nombre d'absences pour un etudiant de 1ere annee
     * Moyenne de la 1ere annee pour les autres
     */
    private int modifier;

    /**
     *Renvoie le nom de famille d'un etudiant
     *@return lastName soit le nom de famille
     */
    public String getLastName() {
        return this.lastName;
    }

    /*
     * setter pour modifier
     */
    public void setModifier(int modifier) { this.modifier = modifier; }

    /**
     * Modifie le nom de famille d'un etudiant
     * @param lastName: nouveau nom de famille
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Renvoie le nombre d'absences pour un eleve de 1ere annee
     * Sinon, renvoie la moyenne de premiere annee
     * @return modifier: nombre d'absences ou moyenne année 1
     */
    public int getModifier() {
        return modifier;
    }

    /*
     * setter pour tmp
     */
    public void setTmp(int tmp) {
        this.tmpSub = tmp;
    }

    /**
     * Renvoie le prenom d'un etudiant
     * @return name: le prenom
     */
    public String getName() {
        return this.name;
    }

    /**
     * Modifie le prenom d'un etudiant
     * @param name: nouveau prenom
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Renvoie le tableau des moyennes de l'etudiant
     * @return score: tableau des moyennes
     */
    public double[] getScore() {
        return this.score;
    }

    /**
     * Change le tableau des moyenne de l'etudiant
     * @param score: nouveau tableau de moyenne
     */
    public void setScore(double[] score) {
        this.score = score;
    }

    /**
     * Renvoie le niveau d'etude de l'etudiant en nombre d'annee de formation suivie
     * @return LEVEL: l'annee d'etude
     */
    public Level getLevel() {
        return this.LEVEL;
    }

    /**
     * Permet de savoir si un etudiant est fixe (affecte) ou non pour les differentes matieres
     * @return fixed: tableau de fixe(true) ou non(false)
     */
    public boolean[] getFixed() {
        return this.fixed;
    }

    /**
     * Permet de fixer l'affectation d'un etudiant pour une matiere donnee
     * @param fixed: boolean fixe ou non
     * @param subjectID: ID de la matière
     */
    public void setFixed(boolean fixed,int subjectID) {
        this.fixed[subjectID] = fixed;
    }



    /**
     * Permet de creer un etudiant grace aux informations qui le concernent
     * Utilisation du constructeur de Person
     * On utilise l'annee en chiffre pour lui atribuer l'annee correspondante de l'enum Level
     * On augemente la moyenne des 3emes annees pour les favoriser dans l'affectaion des tuteurs
     * @param nom: nom de l'etudiant
     * @param prenom: prenom de l'etudant
     * @param login: identifiant de l'etudiant
     * @param password: mot de passe de l'etudiant
     * @param moyenne: tableau des moyennes de l'etudiant
     * @param annee: annee d'etude au sein de la formation
     * @see Person
     * @see Level
     */
    public Student(String nom, String prenom,String login, String password, double[] moyenne, String annee) {
        super(nom,prenom,login,password);
        int add =0;
        if (annee.equals("3")) { // Si on avais plus de 3 année on aurais pu faire un Integer.parseInteger(annee) et faire les test plus intéligement
            this.LEVEL = Level.THIRD;
            add = 10;
        } else if (annee.equals("2")) {
            this.LEVEL = Level.SECOND;
        } else {
            this.LEVEL = Level.FIRST;
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
     * Permet de creer un etudiant grace aux informations qui le concernent
     * Utilisation du constructeur de Person
     * On utilise l'annee en chiffre pour lui atribuer l'annee correspondante de l'enum Level
     * On augemente la moyenne des 3emes annees pour les favoriser dans l'affectaion des tuteurs
     * @param nom: nom de l'etudiant
     * @param prenom: prenom de l'etudiant
     * @param moyenne: tableau des moyennes de l'etudiant
     * @param annee: annee d'etude au sein de la formation
     * @see Person
     */
    public Student(String nom, String prenom, String password, double[] moyenne, String annee) {
        super(nom,prenom,prenom.toLowerCase()+"."+nom.toLowerCase()+".etu",password);
        int add =0;
        if (annee.equals("3")) { // Si on avais plus de 3 année on aurais pu faire un Integer.parseInteger(annee) et faire les test plus intéligement
            this.LEVEL = Level.THIRD;
            add = 10;
        } else if (annee.equals("2")) {
            this.LEVEL = Level.SECOND;
        } else {
            this.LEVEL = Level.FIRST;
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
     * Permet de creer un etudiant grece aux informations qui le concernent
     * Utilisation du constructeur de Person
     * On utilise l'annee en chiffre pour lui atribuer l'annee correspondante de l'enum Level
     * On augemente la moyenne des 3emes annees pour les favoriser dans l'affectaion des tuteurs
     * @param nom: nom de l'etudiant
     * @param prenom: prenom de l'etudiant
     * @param login: identifiant de l'etudiant
     * @param password: mot de passe de l'etudiant
     * @param moyenne: tableau des moyennes de l'etudiant
     * @param annee: année d'etude au sein de la formation
     * @param modifier: nombre d'absence de l'etudiant si 1ere annee sinon moyenne de 1ere annee
     * @see Person
     */
    public Student(String nom, String prenom, String login, String password, double[] moyenne, String annee,String modifier) {   // Le modifier est le nombre d'absence en premiere annee, dans les autres promotion c'est la moyenne de la premiere année de l'etudiant
        super(nom,prenom,login,password);
        Double add =0.0;
        if (annee.equals("3")) {                            // Si troisieme année on augmente artificiellement les moyenne pour prioriser l'affecation de troisieme année
            this.LEVEL = Level.THIRD;
            add = 10.0+Double.parseDouble(modifier)*0.1;  // J'ajoute en bonus sa moyenne de premiere annee. Si il avais des bonnes notes il devrais pouvoir aider plus facilement.                                 
        } else if (annee.equals("2")) {
            this.LEVEL = Level.SECOND;
            add=+Double.parseDouble(modifier)*0.1;
        } else {
            this.LEVEL = Level.FIRST;
            add = add + 0.1*Double.parseDouble(modifier);   // Si premiere annee on augmente artificiellement la moyenne en fonction des absence/10 pour penaliser les absences
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
     * Permet de creer un etudiant grece aux informations qui le concernent
     * Utilisation du constructeur de Person
     * On utilise l'annee en chiffre pour lui atribuer l'annee correspondante de l'enum Level
     * On augemente la moyenne des 3emes annees pour les favoriser dans l'affectaion des tuteurs
     * @param nom: nom de l'etudiant
     * @param prenom: prenom de l'etudiant
     * @param password: mot de passe de l'etudiant
     * @param moyenne: tableau des moyennes de l'etudiant
     * @param annee: année d'etude au sein de la formation
     * @param modifier: nombre d'absence de l'etudiant si 1ere annee sinon moyenne de 1ere annee
     * @see Person
     */
    public Student(String nom, String prenom, String password, double[] moyenne, String annee,String modifier) {   // Le modifier est le nombre d'absence en premiere annee, dans les autres promotion c'est la moyenne de la premiere annee de l'etudiant
        this(nom, prenom, prenom+"."+nom+".etu", password,moyenne,annee, modifier);
    }

    /**
     * Renvoie l'etudiant sous la forme [Login : prenom.nom.etu ; Score : [0, 0, 0, 0, 0] ; Level = first];
     */
    public String toString() {
        String str = "[ "+score[0];
        for (int i = 1; i < score.length; i++) {
            str = str+", "+score[i];
        }
        str = str+" ]";
        return /*"[Login : " + */name+" "+lastName+" "+/*".etu ; Score : " +*/ str +" "+ /*" ; Level = " + */LEVEL /* + "]"*/;
    }

    /**
     * Renvoie l'etudiant sous la forme [Login : prenom.nom.etu ; Score : 0 ; Level = first];
     * @param subjectID: matiere concernee
     */
    public String toString(int subjectId) {
        return "[Login : " + name+"."+lastName+".etu ; Score : " + score[subjectId] + " ; Level = " + LEVEL + "]";
    }


    /**
     * Permet de comparer un etudiant a un autre sur plusieurs criteres comme l'annee ou la moyenne
     * @return int: un entier positif si l'etudiant initial est meilleur ou sinon negatif
     */
    // Il faut au prealable changer la valeur de tmpSub pour pointer sur la bonne matiere avant d'utiliser cette methode, avec Collections.sort() par exemple.
    public int compareTo(Student d) { // Nous permet d'utiliser Collections.sort(Arraylist<Student>)
        if(d.LEVEL == Level.FIRST){
            return (int) (this.score[tmpSub] - d.score[tmpSub]);
        }else{
            if(d.score[tmpSub]>=5000 || score[tmpSub]>=5000){
                return (int) -(this.score[tmpSub] - d.score[tmpSub]);
            }
            if (LEVEL == Level.THIRD && d.LEVEL != Level.THIRD) {
                return -1;
            } else if (d.LEVEL == Level.THIRD && LEVEL != Level.THIRD) {
                return 1;
            } else {
                return (int) -(this.score[tmpSub] - d.score[tmpSub]);
            }
        }
    }

    /**
     * Permet de verifier l'egalite entre un etudiant et une instance d'Object
     */
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

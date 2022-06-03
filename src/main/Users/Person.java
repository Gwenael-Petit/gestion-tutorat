package main.Users;
/**
 * Class abstraite definissant une Person et ses informations personnelles utiles pour se connecter
 */
public abstract class Person {

    /**
     * nom de famille
     */
    protected String lastName;
    
    /*
     * prenom
     */
    protected String name;

    /*
     * identifiant
     */
    protected String login;

    /**
     * mot de passe
     */
    protected String password;


    /**
     * Permet de creer une Person a partir de ses informations
     * @param lastName : nom de famille
     * @param name : prenom
     * @param login : identifiant
     * @param paswword : mot de passe
     */
    public Person(String lastName, String name, String login, String password) {
        this.lastName = lastName;
        this.name = name;
        this.login = login;
        this.password = password;
    }

    /**
     * Renvoie une Person sous la forme : [Login : prenom.nom; PWD : motdepasse]
     * @return information d'une personne
     */
    public String toString() {
        return "[Login : " + name+"."+lastName+"; PWD : "+password + "]\n";
    }


    /**
     * Renvoie le nom de famille d'une personne
     * @return lastName : nom de famille
     */
    public String getLastName() {
        return this.lastName;
    }

    /**
     * Renvoie le prenom d'une personne
     * @return name : prenom
     */
    public String getName() {
        return this.name;
    }

    /**
     * Renvoie l'identifiant d'une personne
     * @return login : identifiant
     */
    public String getLogin() {
        return this.login;
    }

    /**
     * Renvoie le mot de passe d'une personne
     * @return password : mot de passe
     */
    public String getPassword() {
        return this.password;
    } 

    /**
     * Verifie si une connexion est valide grace a l'identifiant et le mot de passe
     * @param login : identifiant entre
     * @param pwd : mot de passe entre
     */
    public boolean connect(String login, String pwd){
        return login.equals(this.login) && pwd.equals(this.password);
    }

    /**
     * Verifie si une personne est un enseignant
     */
    public boolean isTeacher(){
        return this instanceof Teacher;
    }

    /**
     * Verifie si une personne est un etudiant
     */
    public boolean isStudent(){
        return this instanceof Student;
    }
}

package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

import main.Users.Person;
import main.Util.exceptions.WrongLoginException;
/**
 * Classe qui g�re la connexion des utilisateurs en fonction d'un login et d'un mot de passe
 */
public class LogInManagement {
    //La personne de type Person qui est connectee
	private Person logged;

    /**
     * Cree un etat de connexion en prenant en compte la personne connectee (logged) en parametre
     * @param logged : montre qui est la personne connectee
     */
    public LogInManagement(Person logged) {
        this.logged = logged;
    }
    
    /** 
     * Cree un etat de connexion sans parametre qui instancie logged a null
     */
    public LogInManagement() {
        this.logged =null;
    }

    /**
     * Renvoie l'etat de connexion sous forme de chaine de caracteres
     * @return logged (de type Person) si la personne est connectee et "null" sinon
     */
    @Override
    public String toString() {
        if(isLogged()){
            return logged.toString();
        }else{
            return "null";
        }
    }

    /**
     * Interaction avec l'utilisateur lui demandant son login et son mot de passe avec un BufferedReader et renvoie ensuite la personne (Person) qui correspond
     * @param p : ArrayList de personnes
     * @param br : BufferedReader
     * @return  la personne qui correspond aux login et mot de passe et WrongLoginException si elle ne correspond pas
     */
    private Person getUserPwd(ArrayList<Person> p, BufferedReader br) throws IOException, WrongLoginException {
        System.out.println("Login:");
        String login = br.readLine();
        System.out.println("Mot de passe:");
        String pwd = br.readLine();
        Person res = null;
        for (int i = 0; i < p.size(); i++) {
            if(p.get(i).connect(login, pwd)){
                res=p.get(i);
            }
        }
        if(res==null){throw new WrongLoginException();}
        return res;
    }

    /**
     * "Connecte" l'utilisateur qui correspond aux login et mot de passe et lui signale
     * @param p : l'ArrayList de personnes
     * @param br : un BufferedReader
     * @return logged soit la personne maintenant connectee
     */
    public Person connect(ArrayList<Person> p, BufferedReader br) {
        boolean flag = true;
        while (flag) {
            try {
                logged = getUserPwd(p,br);
                System.out.println("Vous êtes connecté !");
                flag = false;
            } catch(IOException e){
                System.out.println(e.getMessage());
            }catch (WrongLoginException e) {
                System.out.println(e.getMessage());
            }
        }
        return logged;
    }

    /**
     * Verifie si l'utilisateur est connecte
     * @return true si il est connecte (logged est non null) et false sinon
     */
    public boolean isLogged(){
        return logged != null;
    }

    /**
     * Renvoie qui est la personne connectee
     * @return logged soit la personne
     */
    public Person getLogged() {
        return logged;
    }
}

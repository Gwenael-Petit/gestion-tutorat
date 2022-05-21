package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import main.Users.Person;
import main.Util.exceptions.WrongLoginException;

public class LogInManagement {
    private Person logged;

    public LogInManagement(Person logged) {
        this.logged = logged;
    }
    public LogInManagement() {
        this.logged =null;
    }

    @Override
    public String toString() {
        if(isLogged()){
            return logged.toString();
        }else{
            return "null";
        }
    }

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

    public boolean isLogged(){
        return logged != null;
    }

    public Person getLogged() {
        return logged;
    }
}

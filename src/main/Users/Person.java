package main.Users;

public abstract class Person {
    protected String lastName;
    protected String name;
    protected String login;
    protected String password;


    public Person(String lastName, String name, String login, String password) {
        this.lastName = lastName;
        this.name = name;
        this.login = login;
        this.password = password;
    }

    public String toString() {
        return "[Login : " + name+"."+lastName+"; PWD : "+password + "]\n";
    }


    public String getLastName() {
        return this.lastName;
    }

    public String getName() {
        return this.name;
    }

    public String getLogin() {
        return this.login;
    }

    public String getPassword() {
        return this.password;
    } 

    public boolean connect(String login, String pwd){
        return login.equals(this.login) && pwd.equals(this.password);
    }

    public boolean isTeacher(){
        return this instanceof Teacher;
    }

    public boolean isStudent(){
        return this instanceof Student;
    }
}

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
    
}

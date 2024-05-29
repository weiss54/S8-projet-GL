package fr.ul.miage;

public class Identification {
    private int idIdentification;
    private String login;
    private boolean isAdmin;

    public Identification(int idIdentification, String login, String password) {
        this.idIdentification = idIdentification;
        this.login = login;
        this.isAdmin = isAdmin;
    }

    public String getLogin() {
        return login;
    }

    public int getIdIdentification() {
        return idIdentification;
    }

    public boolean isAdmin() {
        return isAdmin;
    }
}

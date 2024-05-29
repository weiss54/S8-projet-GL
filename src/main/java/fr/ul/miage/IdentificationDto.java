package fr.ul.miage;

public class IdentificationDto {
    private int idIdentification;
    private String login;
    private String password;
    private boolean isAdmin;

    public IdentificationDto(int idIdentification, String login, String password) {
        this.idIdentification = idIdentification;
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public int getIdIdentification() {
        return idIdentification;
    }

    public boolean isAdmin() {
        return isAdmin;
    }
}

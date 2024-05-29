package fr.ul.miage;

public class ClientDto {
    // valider les données
    private int idClient;
    private String nom;
    private String prenom;
    private String adresse;
    private String email;
    private String numeroMobile;
    private String numeroCb;
    private int idIdentification;
    private int idSociete;

    public ClientDto(int idClient, String nom, String prenom, String adresse, String email, String numeroMobile, String numeroCb, int idIdentification, int idSociete) {
        this.idClient = idClient;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.email = email;
        this.numeroMobile = numeroMobile;
        this.numeroCb = numeroCb;
        this.idIdentification = idIdentification;
        this.idSociete = idSociete;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getAdresse() {
        return adresse;
    }

    public String getEmail() {
        return email;
    }

    public String getNumeroMobile() {
        return numeroMobile;
    }

    public String getNumeroCb() {
        return numeroCb;
    }

    public int getIdClient() {
        return idClient;
    }

    public int getIdIdentification() {
        return idIdentification;
    }

    public int getIdSociete() {
        return idSociete;
    }
}

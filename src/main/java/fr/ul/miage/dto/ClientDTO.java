package fr.ul.miage.dto;

public class ClientDTO {
    private final long idClient;
    private final String nom;
    private final String prenom;
    private final String adresse;
    private final String email;
    private final String numeroMobile;
    private final String numeroCb;
    private final boolean estAdmin;

    public ClientDTO(long idClient, String nom, String prenom, String adresse, String email, String numeroMobile, String numeroCb, boolean estAdmin) {
        this.idClient = idClient;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.email = email;
        this.numeroMobile = numeroMobile;
        this.numeroCb = numeroCb;
        this.estAdmin = estAdmin;
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

    public boolean estAdmin() {
        return estAdmin;
    }

    public long getIdClient() {
        return idClient;
    }
}

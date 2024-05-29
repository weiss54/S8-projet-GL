package fr.ul.miage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Client {
    private int idClient;
    private String nom;
    private String prenom;
    private String adresse;
    private String email;
    private String numeroMobile;
    private String numeroCb;
    private int idIdentification;
    private int idSociete;
    private Connection connection;

    public Client(String nom, String prenom, String adresse, String numeroMobile, String numeroCb, String email, int idIdentification, int idSociete) {
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.email = email;
        this. numeroMobile= numeroMobile;
        this.numeroCb = numeroCb;
        this.idIdentification = idIdentification;
        this.idSociete = idSociete;
    }

    public ClientDto insertNewClientDb()
            throws SQLException {

        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO client (nom, prenom, adresse, numeroMobile, numeroCb, email, idIdentification, idSociete ) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
        preparedStatement.setString(1, this.nom);
        preparedStatement.setString(2, this.prenom);
        preparedStatement.setString(3, this.adresse);
        preparedStatement.setString(4, this.numeroMobile);
        preparedStatement.setString(5, this.numeroCb);
        preparedStatement.setString(6, this.email);
        preparedStatement.setInt(7, this.idIdentification);
        preparedStatement.setInt(8, this.idSociete);

        preparedStatement.executeUpdate();

        preparedStatement = connection.prepareStatement("SELECT id FROM client WHERE idIdentification = ?");
        preparedStatement.setInt(1, this.idIdentification);

        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            return new ClientDto(resultSet.getInt("id"), nom, prenom, adresse, numeroMobile, numeroCb, email, resultSet.getInt("idIdentification"), resultSet.getInt("idSociete"));
        }else{
            return null;
        }
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

}

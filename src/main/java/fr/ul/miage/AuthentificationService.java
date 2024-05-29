package fr.ul.miage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthentificationService {
    private Connection connection;

    public AuthentificationService(Connection connection) {
        this.connection = connection;
    }

    public Client login(String login, String password) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM identification WHERE login = ? AND password = ?");
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int idIdentification = resultSet.getInt("id");
                preparedStatement = connection.prepareStatement("SELECT * FROM client WHERE idIdentification = ?");
                preparedStatement.setInt(1, idIdentification);
                resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    return new Client(resultSet.getString("nom"), resultSet.getString("prenom"), resultSet.getString("adresse"), resultSet.getString("numeroMobile"), resultSet.getString("numeroCb"), resultSet.getString("email"), resultSet.getInt("idIdentification"), resultSet.getInt("idSociete"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Client signup(String nom, String prenom, String adresse, String numeroMobile, String numeroCb, String email, String login, String password, String nomSociete) {
        try {
            SocieteDto societe = createOrUpdateSociete(nomSociete);
            IdentificationDto identification = createIdentification(login, password);
            Client client = new Client(nom, prenom, adresse, numeroMobile, numeroCb, email, identification.getIdIdentification(), societe.getIdSociete());
            return new Client(client.getNom(), client.getPrenom(), client.getAdresse(), client.getNumeroMobile(), client.getNumeroCb(), client.getEmail(), identification.getIdIdentification(), societe.getIdSociete());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private IdentificationDto createIdentification(String login, String password) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO identification (login, password) VALUES (?, ?)");
        preparedStatement.setString(1, login);
        preparedStatement.setString(2, password);
        preparedStatement.executeUpdate();

        preparedStatement = connection.prepareStatement("SELECT id FROM identification WHERE login = ? AND password = ?");
        preparedStatement.setString(1, login);
        preparedStatement.setString(2, password);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            return new IdentificationDto(resultSet.getInt("id"), login, password);
        }else{
            return null;
        }
    }

    private SocieteDto createOrUpdateSociete(String nomSociete) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT id FROM societe WHERE nom = ?");
        preparedStatement.setString(1, nomSociete);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            return new SocieteDto(resultSet.getInt("id"), nomSociete);
        }else{
            preparedStatement = connection.prepareStatement("INSERT INTO societe (nom) VALUES (?)");
            preparedStatement.setString(1, nomSociete);
            preparedStatement.executeUpdate();
            preparedStatement = connection.prepareStatement("SELECT id FROM societe WHERE nom = ?");
            preparedStatement.setString(1, nomSociete);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new SocieteDto(resultSet.getInt("id"), nomSociete);
            }else{
                return null;
            }
        }
    }
}

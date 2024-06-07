package fr.ul.miage.service;

import fr.ul.miage.DatabaseConnection;
import fr.ul.miage.EtatReservation;
import fr.ul.miage.Reservation;
import fr.ul.miage.TypeReservation;
import fr.ul.miage.dto.ReservationDTO;
import fr.ul.miage.mapper.ReservationMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class ReservationService {
    private final Connection connection;

    public ReservationService(){this.connection = DatabaseConnection.getConnection();}

    public ReservationDTO getReservationById(int id) throws SQLException {
        String query = "SELECT * FROM reservation WHERE num_reservation = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if(rs.next()){
                    // Récupération des valeurs de la base de données
                    int num_reservation = rs.getInt("num_reservation");
                    LocalDate date = rs.getDate("date").toLocalDate();
                    LocalTime heure_debut = rs.getTime("heure_debut").toLocalTime();
                    LocalTime heure_fin = rs.getTime("heure_fin").toLocalTime();
                    LocalTime heure_arrivee = (rs.getTime("heure_arrivee") != null) ? rs.getTime("heure_arrivee").toLocalTime() : null;
                    LocalTime heure_depart = (rs.getTime("heure_depart") != null) ? rs.getTime("heure_depart").toLocalTime() : null;
                    int prolongee = rs.getInt("prolongee");
                    EtatReservation etat = EtatReservation.valueOf(rs.getString("etat"));
                    TypeReservation type = TypeReservation.valueOf(rs.getString("type"));
                    int id_borne = rs.getInt("id_borne");
                    String immatriculation_voiture = rs.getString("id_vehicule");
                    int id_client = rs.getInt("id_client");

                    // Création de l'objet Reservation
                    Reservation reservation = new Reservation(num_reservation, date, heure_debut, heure_fin, heure_arrivee, heure_depart, prolongee, id_client, id_borne, immatriculation_voiture, etat, type);
                    return ReservationMapper.toDTO(reservation);
                }
            }
        }
        return null;
    }

    public List<ReservationDTO> getReservationsByBorne(int idBorne) throws SQLException {
        String query = "SELECT * FROM reservation WHERE id_borne = ? AND etat != 'TERMINÉE'";
        List<ReservationDTO> reservations = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, idBorne);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    // Récupération des valeurs de la base de données
                    int num_reservation = rs.getInt("num_reservation");
                    LocalDate date = rs.getDate("date").toLocalDate();
                    LocalTime heure_debut = rs.getTime("heure_debut").toLocalTime();
                    LocalTime heure_fin = rs.getTime("heure_fin").toLocalTime();
                    LocalTime heure_arrivee = (rs.getTime("heure_arrivee") != null) ? rs.getTime("heure_arrivee").toLocalTime() : null;
                    LocalTime heure_depart = (rs.getTime("heure_depart") != null) ? rs.getTime("heure_depart").toLocalTime() : null;
                    int prolongee = rs.getInt("prolongee");
                    EtatReservation etat = EtatReservation.valueOf(rs.getString("etat"));
                    TypeReservation type = TypeReservation.valueOf(rs.getString("type"));
                    String immatriculation_voiture = rs.getString("id_vehicule");
                    int id_client = rs.getInt("id_client");

                    // Création de l'objet Reservation
                    Reservation reservation = new Reservation(num_reservation, date, heure_debut, heure_fin, heure_arrivee, heure_depart, prolongee, id_client, idBorne, immatriculation_voiture, etat, type);

                    // Ajout de la réservation à la liste
                    reservations.add(ReservationMapper.toDTO(reservation));
                }
            }
        }
        return reservations;
    }

    public List<ReservationDTO> getReservationsByClient(int idClient) throws SQLException {
        String query = "SELECT * FROM reservation WHERE id_client = ? AND etat != 'TERMINÉE'";
        List<ReservationDTO> reservations = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, idClient);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    // Récupération des valeurs de la base de données
                    int num_reservation = rs.getInt("num_reservation");
                    LocalDate date = rs.getDate("date").toLocalDate();
                    LocalTime heure_debut = rs.getTime("heure_debut").toLocalTime();
                    LocalTime heure_fin = rs.getTime("heure_fin").toLocalTime();
                    LocalTime heure_arrivee = (rs.getTime("heure_arrivee") != null) ? rs.getTime("heure_arrivee").toLocalTime() : null;
                    LocalTime heure_depart = (rs.getTime("heure_depart") != null) ? rs.getTime("heure_depart").toLocalTime() : null;
                    int prolongee = rs.getInt("prolongee");
                    EtatReservation etat = EtatReservation.valueOf(rs.getString("etat"));
                    TypeReservation type = TypeReservation.valueOf(rs.getString("type"));
                    int id_borne = rs.getInt("id_borne");
                    String immatriculation_voiture = rs.getString("id_vehicule");

                    // Création de l'objet Reservation
                    Reservation reservation = new Reservation(num_reservation, date, heure_debut, heure_fin, heure_arrivee, heure_depart, prolongee, idClient, id_borne, immatriculation_voiture, etat, type);

                    // Ajout de la réservation à la liste
                    reservations.add(ReservationMapper.toDTO(reservation));
                }
            }
        }
        return reservations;
    }


    public void createReservation(ReservationDTO reservationDTO) throws SQLException {

        String query = "INSERT INTO reservation (date, heure_debut, heure_fin, heure_arrivee, heure_depart, prolongee, etat, type, id_borne, id_vehicule, id_client) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            // Mapper le DTO vers l'entité Reservation
            Reservation reservation = ReservationMapper.toEntity(reservationDTO);

            // Définir les paramètres de la requête
            stmt.setDate(1, java.sql.Date.valueOf(reservation.getDate()));
            stmt.setTime(2, java.sql.Time.valueOf(reservation.getHeure_debut()));
            stmt.setTime(3, java.sql.Time.valueOf(reservation.getHeure_fin()));
            stmt.setTime(4, (reservation.getHeure_arrivee() != null) ? java.sql.Time.valueOf(reservation.getHeure_arrivee()) : null);
            stmt.setTime(5, (reservation.getHeure_depart() != null) ? java.sql.Time.valueOf(reservation.getHeure_depart()) : null);
            stmt.setInt(6, reservation.getProlongee());
            stmt.setString(7, reservation.getEtat().name());
            stmt.setString(8, reservation.getType().name());
            stmt.setInt(9, reservation.getId_borne());
            stmt.setString(10, reservation.getImmatriculation_voiture());
            stmt.setInt(11, reservation.getId_client());

            // Exécuter la requête
            stmt.executeUpdate();
            System.out.println("Réservation créée avec succès !");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new IllegalStateException("Erreur lors de la création de la réservation : " + e.getMessage());
        }
    }


    //TODO alimenter classe

}

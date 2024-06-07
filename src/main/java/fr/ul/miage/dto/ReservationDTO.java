package fr.ul.miage.dto;

import fr.ul.miage.EtatReservation;
import fr.ul.miage.TypeReservation;

import java.time.LocalDate;
import java.time.LocalTime;

public class ReservationDTO {
    private LocalDate date;
    private LocalTime heure_debut, heure_fin, heure_arrivee, heure_depart;
    private int prolongee, num_reservation, id_client, id_borne;
    private String immatriculation_voiture;
    private EtatReservation etat;
    private TypeReservation type;

    /**
     * Constructeur de la classe RéservationDTO
     * @param date
     * @param heure_debut
     * @param heure_fin
     * @param heure_arrivee
     * @param heure_depart
     * @param prolongee
     * @param id_client
     * @param id_borne
     * @param immatriculation_voiture
     */
    public ReservationDTO(int num_reservation, LocalDate date, LocalTime heure_debut, LocalTime heure_fin, LocalTime heure_arrivee, LocalTime heure_depart, int prolongee, int id_client, int id_borne, String immatriculation_voiture) {
        this.num_reservation = num_reservation;
        this.etat = EtatReservation.CREE;
        this.date = date;
        this.heure_debut = heure_debut;
        this.heure_fin = heure_fin;
        this.heure_arrivee = heure_arrivee;
        this.heure_depart = heure_depart;
        this.prolongee = prolongee;
        this.id_client = id_client;
        this.id_borne = id_borne;
        this.immatriculation_voiture = immatriculation_voiture;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getHeure_debut() {
        return heure_debut;
    }

    public void setHeure_debut(LocalTime heure_debut) {
        this.heure_debut = heure_debut;
    }

    public LocalTime getHeure_fin() {
        return heure_fin;
    }

    public void setHeure_fin(LocalTime heure_fin) {
        this.heure_fin = heure_fin;
    }

    public LocalTime getHeure_arrivee() {
        return heure_arrivee;
    }

    public void setHeure_arrivee(LocalTime heure_arrivee) {
        this.heure_arrivee = heure_arrivee;
    }

    public LocalTime getHeure_depart() {
        return heure_depart;
    }

    public void setHeure_depart(LocalTime heure_depart) {
        this.heure_depart = heure_depart;
    }

    public int getProlongee() {
        return prolongee;
    }

    public void setProlongee(int prolongee) {
        this.prolongee = prolongee;
    }

    public int getNum_reservation() {
        return num_reservation;
    }

    public void setNum_reservation(int num_reservation) {
        this.num_reservation = num_reservation;
    }

    public EtatReservation getEtat() {
        return etat;
    }

    public void setEtat(EtatReservation etat) {
        this.etat = etat;
    }

    public TypeReservation getType() {
        return type;
    }

    public void setType(TypeReservation type) {
        this.type = type;
    }

    public int getId_client() {
        return id_client;
    }

    public void setId_client(int id_client) {
        this.id_client = id_client;
    }

    public int getId_borne() {
        return id_borne;
    }

    public void setId_borne(int id_borne) {
        this.id_borne = id_borne;
    }

    public String getImmatriculation_voiture() {
        return immatriculation_voiture;
    }

    public void setImmatriculation_voiture(String immatriculation_voiture) {
        this.immatriculation_voiture = immatriculation_voiture;
    }
}

package fr.ul.miage.dto;

import fr.ul.miage.EtatReservation;
import fr.ul.miage.TypeReservation;

import java.time.LocalDate;
import java.time.LocalTime;

public class ReservationDTO {
    private LocalDate date;
    private LocalTime heure_debut, heure_fin, heure_arrivee, heure_depart;
    private int prolongee, num_reservation;
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
     */
    public ReservationDTO(int num_reservation, LocalDate date, LocalTime heure_debut, LocalTime heure_fin, LocalTime heure_arrivee, LocalTime heure_depart, int prolongee) {
        this.num_reservation = num_reservation;
        this.etat = EtatReservation.CREE;
        this.date = date;
        this.heure_debut = heure_debut;
        this.heure_fin = heure_fin;
        this.heure_arrivee = heure_arrivee;
        this.heure_depart = heure_depart;
        this.prolongee = prolongee;
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
}

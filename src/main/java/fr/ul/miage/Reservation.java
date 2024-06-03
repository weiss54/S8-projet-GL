package fr.ul.miage;

import java.sql.Time;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;


/**
 * Classe qui représente une reservation de borne de recharge
 */
public class Reservation {
    private static final int NB_MAX_PROLONGATION = 3;
    private Date date;
    private Time heure_debut, heure_fin, heure_arrivee, heure_depart;
    private int prolongee, num_reservation;
    private EtatReservation etat;
    private TypeReservation type;
    //TODO ajout Borne, Vehicule, Client (pas oublier le constructeur)

    public Reservation(Date date, Time heure_debut, Time heure_fin){
        this.etat = EtatReservation.CREE;
        this.date = date;
        this.heure_debut = heure_debut;
        this.heure_fin = heure_fin;
        this.heure_arrivee = null;
        this.heure_depart = null;
        this.prolongee = 0;
    }

    public void modifierReservation(Time heure_debut, Time heure_fin){
        if (this.etat.equals(EtatReservation.CONFIRMEE)){
            this.heure_debut = heure_debut;
            this.heure_fin = heure_fin;
        } else {
            throw new IllegalStateException("La réservation ne peut être modifiée que si elle est confirmée.");
        }
    }
    public void prolongerReservation(Duration duree){
        boolean peutProlonger = Duration.between(this.heure_fin.toInstant(), Instant.now()).toMinutes()<=15;
        if (this.prolongee<NB_MAX_PROLONGATION && peutProlonger){
            this.prolongee++;
        } else {
            throw new IllegalStateException("La réservation ne peut être prolongée.");
        }
    }
    public Date getDate() {
        return date;
    }

    public Date getHeure_debut() {
        return heure_debut;
    }

    public void setHeure_debut(Time heure_debut) {
        this.heure_debut = heure_debut;
    }

    public Date getHeure_fin() {
        return heure_fin;
    }

    public void setHeure_fin(Time heure_fin) {
        this.heure_fin = heure_fin;
    }

    public Date getHeure_arrivee() {
        return heure_arrivee;
    }

    public void setHeure_arrivee(Time heure_arrivee) {
        this.heure_arrivee = heure_arrivee;
    }

    public Date getHeure_depart() {
        return heure_depart;
    }

    public void setHeure_depart(Time heure_depart) {
        this.heure_depart = heure_depart;
    }

    public int getProlongee() {
        return prolongee;
    }

    public void setProlongee(int prolongee) {
        this.prolongee = prolongee;
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

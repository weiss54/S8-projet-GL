package fr.ul.miage;

import java.sql.Time;
import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;


/**
 * Classe qui représente une reservation de borne de recharge
 */
public class Reservation {
    private static final int NB_MAX_PROLONGATION = 3;
    private LocalDate date;
    private LocalTime heure_debut, heure_fin, heure_arrivee, heure_depart;
    private int prolongee, num_reservation;
    private EtatReservation etat;
    private TypeReservation type;
    //TODO ajout Borne, Vehicule, Client (pas oublier le constructeur)

    /**
     * Constructeur utilisé lors de la création d'une nouvelle reservation
     * @param date
     * @param heure_debut
     * @param heure_fin
     */
    public Reservation(LocalDate date, LocalTime heure_debut, LocalTime heure_fin){
        this.etat = EtatReservation.CREE;
        this.date = date;
        this.heure_debut = heure_debut;
        this.heure_fin = heure_fin;
        this.heure_arrivee = null;
        this.heure_depart = null;
        this.prolongee = 0;
    }

    /**
     * Constructeur par défaut de la classe Réservation
     * @param date
     * @param heure_debut
     * @param heure_fin
     * @param heure_arrivee
     * @param heure_depart
     * @param prolongee
     */
    public Reservation(int num_reservation, LocalDate date, LocalTime heure_debut, LocalTime heure_fin, LocalTime heure_arrivee, LocalTime heure_depart, int prolongee) {
        this.num_reservation = num_reservation;
        this.etat = EtatReservation.CREE;
        this.date = date;
        this.heure_debut = heure_debut;
        this.heure_fin = heure_fin;
        this.heure_arrivee = heure_arrivee;
        this.heure_depart = heure_depart;
        this.prolongee = prolongee;
    }

    /**
     * Méthode qui pérmet de créer une nouvelle réservation
     */
    public void creerReservation (){
        if (estReservationCoherente(this.date, this.heure_debut, this.heure_fin)){
            //TODO ajout nouvelle réservation dans bdd
        } else throw new IllegalStateException("Votre demande n'est pas cohérente.");
    }

    /**
     * Méthode qui permet de modifier une réservation existante
     * @param heure_debut nouvelle heure de début de la réservation
     * @param heure_fin nouvelle heure de fin de la réservation
     */
    public void modifierReservation(LocalTime heure_debut, LocalTime heure_fin){
        if (this.etat.equals(EtatReservation.CONFIRMEE)){
            this.heure_debut = heure_debut;
            this.heure_fin = heure_fin;
        } else {
            throw new IllegalStateException("La réservation ne peut être modifiée que si elle est confirmée.");
        }
    }
    //TODO vérifier le booléen peutProlonger, je crois qu'il ne marche pas dans tous les cas

    /**
     * Méthode de vérification de la possibilité de prolongation d'une réservation
     * @param duree la durée de la prolongation
     */
    public void prolongerReservation(LocalTime duree){
        if(this.etat.equals(EtatReservation.EN_COURS)){
            boolean peutProlonger = (LocalTime.now().isBefore(this.heure_fin)) &&
                    ((ChronoUnit.MINUTES.between(LocalTime.now(),this.heure_fin) <= 15) &&
                            (ChronoUnit.MINUTES.between(LocalTime.now(),this.heure_fin) >= 0));
            System.out.println(ChronoUnit.MINUTES.between(this.heure_fin, LocalTime.now()));
            if (this.prolongee<NB_MAX_PROLONGATION && peutProlonger){
                this.prolongee++;
                this.heure_fin=this.heure_fin.plusHours(duree.getHour());
                this.heure_fin=this.heure_fin.plusMinutes(duree.getMinute());
                this.heure_fin=this.heure_fin.plusSeconds(duree.getSecond());
            } else {
                throw new IllegalStateException("La réservation ne peut être prolongée.");
            }
        }else throw new IllegalStateException("La réservation doit être une réservation en cours.");
    }

    /**
     * Méthode de vérification que la saisie de la nouvelle réservation / modification d'une réservation est cohérente
     * @param dateReservation
     * @param debut
     * @param fin
     * @return
     */
    public boolean estReservationCoherente (LocalDate dateReservation, LocalTime debut, LocalTime fin ){

        switch (dateReservation.compareTo(LocalDate.now())){
            // après
            case 1:
                System.out.println("après aujourd'hui");
                return (debut.isBefore(fin));
            // avant
            case -1:
                System.out.println("avant aujourd'hui");
                return false;
            // aujourd'hui
            case 0:
                System.out.println("aujourd'hui");
                return (debut.isBefore(fin)&& debut.isAfter(LocalTime.now()));
            default:
                return false;
        }
    }

    public int getNum_reservation() {
        return num_reservation;
    }

    public void setNum_reservation(int num_reservation) {
        this.num_reservation = num_reservation;
    }

    public LocalDate getDate() {
        return date;
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

package fr.ul.miage;

import java.util.Date;

/**
 * Classe qui représente une facture d'un client pour l'utilisation d'une borne de recharge
 */
public class Facture {
    private float total;
    private Tarif tarif;
    private Date date;
    private int id_tarif, id_reservation;

    public Facture(float total, Tarif tarif, Date date, int id_tarif, int id_reservation) {
        this.total = total;
        this.tarif = tarif;
        this.date = date;
        this.id_tarif = id_tarif;
        this.id_reservation = id_reservation;
    }
    public double calculTarifClient(int duree_reservation, int duree_prolongation, int duree_depassement,
                                    int duree_electricite, int duree_remboursement){
        return (calculCoutReservation(duree_reservation)+calculCoutProlongation(duree_prolongation)
                +calculCoutDepassement(duree_depassement)+calculCoutElectricite(duree_electricite)
                +calculRemboursement(duree_remboursement));
    }
    private double calculCoutReservation(int duree){
        return duree*tarif.getTarif_base();
    }
    private double calculCoutProlongation(int duree){
        return duree*tarif.getTarif_prolongation();
    }
    private double calculCoutDepassement (int duree){
        return duree*tarif.getTarif_depassement();
    }
    private double calculCoutElectricite (int duree){
        return  duree*tarif.getTarif_electricite();
    }
    private  double calculRemboursement (int duree){
        return duree*tarif.getTarif_remboursement();
    }
    public float getTotal() {
        return total;
    }
    public void setTotal(float total) {
        this.total = total;
    }
}

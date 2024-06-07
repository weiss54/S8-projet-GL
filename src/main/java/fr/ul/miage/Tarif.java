package fr.ul.miage;

/**
 * Classe contenant tous les tarifs de l'application
 */
public class Tarif {
    private int id_tarif;
    private double tarif_base, tarif_prolongation, tarif_depassement, tarif_electricite, tarif_remboursement;

    /**
     * Constructeur de la classe Tarif
     * @param id_tarif
     * @param tarif_base
     * @param tarif_prolongation
     * @param tarif_depassement
     * @param tarif_electricite
     * @param tarif_remboursement
     */
    public Tarif(int id_tarif, double tarif_base, double tarif_prolongation, double tarif_depassement, double tarif_electricite, double tarif_remboursement){
        this.id_tarif = id_tarif;
        this.tarif_base = tarif_base;
        this.tarif_prolongation = tarif_prolongation;
        this.tarif_depassement = tarif_depassement;
        this.tarif_electricite = tarif_electricite;
        this.tarif_remboursement = tarif_remboursement;
    }

    /**
     * Constructeur de la classe tarif avec des valeurs par défaut
     */
    public Tarif(){
        //tarifs à l'heure
        this.tarif_base =5.0;
        this.tarif_prolongation = 6.0;
        this.tarif_depassement = 15.0;
        this.tarif_electricite = 0.30*22;
        this.tarif_remboursement = -2.0;
    }

    public double getTarif_base() {
        return tarif_base;
    }
    public void setTarif_base(double tarif_base) {
        this.tarif_base = tarif_base;
    }
    public double getTarif_prolongation() {
        return tarif_prolongation;
    }
    public void setTarif_prolongation(double tarif_prolongation) {
        this.tarif_prolongation = tarif_prolongation;
    }
    public double getTarif_depassement() {
        return tarif_depassement;
    }
    public void setTarif_depassement(double tarif_depassement) {
        this.tarif_depassement = tarif_depassement;
    }
    public double getTarif_electricite() {
        return tarif_electricite;
    }
    public void setTarif_electricite(double tarif_electricite) {
        this.tarif_electricite = tarif_electricite;
    }
    public double getTarif_remboursement() {
        return tarif_remboursement;
    }
    public void setTarif_remboursement(double tarif_remboursement) {
        this.tarif_remboursement = tarif_remboursement;
    }
}

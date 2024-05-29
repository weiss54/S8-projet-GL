package fr.ul.miage;

public class Tarif {
    private double tarif_base, tarif_prolongation, tarif_depassement, tarif_electricite, tarif_remboursement;

    /**
     * Classe contenant tous les tarifs de l'application
     */
    public Tarif(){
        //tarifs à l'heure
        setTarif_base(5.0);
        setTarif_prolongation(6.0);
        setTarif_depassement(15.0);
        setTarif_electricite(0.30*22);
        setTarif_remboursement(-2.0);
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

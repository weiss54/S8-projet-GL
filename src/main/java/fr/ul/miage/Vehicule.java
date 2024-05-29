package fr.ul.miage;

/**
 * Classe qui représente un véhicule
 */
public class Vehicule {
    private String immatriculation;

    public Vehicule() {
    }
    public Vehicule(String immatriculation){
        this.immatriculation=immatriculation.toUpperCase();
    }

    public boolean verifierFormatImmatriculation(String immatriculation){
        String format = "[A-Z]{2}-\\d{3}-[A-Z]{2}$";
        return immatriculation.matches(format);
    }

    public String getImmatriculation() {
        return immatriculation;
    }

    public void setImmatriculation(String immatriculation) {
        this.immatriculation = immatriculation;
    }
}

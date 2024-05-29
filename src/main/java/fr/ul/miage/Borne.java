package fr.ul.miage;

public class Borne {

    private int numero;
    private EtatBorne etat;

    public Borne(int numero, EtatBorne etat) {
        this.numero = numero;
        this.etat = etat;
    }

    @Override
    public String toString() {
        return "[" + numero + "] " + etat;
    }

    public void setEtat(EtatBorne etat) {
        this.etat = etat;
    }

    public int getNumero() {
        return numero;
    }

    public EtatBorne getEtat() {
        return etat;
    }

}

package fr.ul.miage.dto;

import fr.ul.miage.EtatBorne;

public class BorneDTO {

    private int numero;
    private EtatBorne etat;

    public BorneDTO() {
    }

    public BorneDTO(int numero, EtatBorne etat) {
        this.numero = numero;
        this.etat = etat;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public EtatBorne getEtat() {
        return etat;
    }

    public void setEtat(EtatBorne etat) {
        this.etat = etat;
    }

}

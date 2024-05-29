package fr.ul.miage;

public class Societe {
    private int idSociete;
    private String nomSociete;

    public Societe(int idSociete, String nomSociete) {
        this.nomSociete = nomSociete;
    }

    public String getNomSociete() {
        return nomSociete;
    }

    public int getIdSociete() {
        return idSociete;
    }
}

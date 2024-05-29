package fr.ul.miage;

public class SocieteDto {
    private int idSociete;
    private String nomSociete;

    public SocieteDto(int idSociete, String nomSociete) {
        this.idSociete = idSociete;
        this.nomSociete = nomSociete;
    }

    public String getNomSociete() {
        return nomSociete;
    }

    public int getIdSociete() {
        return idSociete;
    }
}

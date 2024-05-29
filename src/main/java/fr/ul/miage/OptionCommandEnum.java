package fr.ul.miage;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Enumération des commandes possibles.
 */
public enum OptionCommandEnum {

    /* Liste des commandes */

    QUIT_APPLICATION(0, "Quit application","terminate application", false),
    CONNECT_USER(1, "Connect user", "connect user", false);

    /**
     * Attributs de l'énumération
     */
    public final int command;

    /**
     * Nom de la commande
     */
    public final String name;

    /**
     * Description détaillée de la commande
     */
    public final String description;

    /**
     * Indique si la commande est réservée aux administrateurs
     */
    public final boolean adminOnly;

    /**
     * Constructeur de l'énumération
     * @param command
     * @param name
     * @param description
     * @param adminOnly
     */
    OptionCommandEnum(int command, String name, String description, boolean adminOnly) {
        this.command = command;
        this.name = name;
        this.description = description;
        this.adminOnly = adminOnly;
    }

    /**
     * Recherche d'une commande par son numéro
     * @param command numéro de la commande
     * @return la commande correspondante, sinon null
     */
    public static OptionCommandEnum searchCommand(int command) {
        for (OptionCommandEnum option : OptionCommandEnum.values()) {
            if (option.command==command) {
                return option;
            }
        }
        return null;
    }

    /**
     * Affichage du menu, méthode privée
     * @param list liste des commandes à afficher, elle est triée par ordre croissant
     * @return le menu sous forme de chaîne de caractères
     */
    private static String displayMenu(List<OptionCommandEnum> list) {
        StringBuilder sb = new StringBuilder();
        list.sort(Comparator.comparingInt(o -> o.command));
        for (OptionCommandEnum option : list) {
            sb.append(String.format("%2d - %s\n", option.command, option.name));
        }
        return sb.toString();
    }

    /**
     * Affichage du menu de connexion
     * @return le menu de connexion sous forme de chaîne de caractères
     */
    public static String displayMenuConnexion() {
        List<OptionCommandEnum> commands = new ArrayList<>();
        commands.add(QUIT_APPLICATION);
        commands.add(CONNECT_USER);
        return displayMenu(commands);
    }

}

package fr.ul.miage;

import java.util.Scanner;

/**
 * Classe qui affiche un menu console pour l'application.
 */
public class ConsoleApp {

    /**
     * Indique si l'application doit continuer à s'exécuter.
     */
    private boolean keepGoing = true;

    /**
     * Scanner pour lire les commandes de l'utilisateur.
     */
    private final Scanner scanner = new Scanner(System.in);

    /**
     * Exécute l'application.
     * @throws Exception
     */
    public void run() throws Exception {
        // écoute les commandes
        while (keepGoing) {

            System.out.println(this.displayHome());
            System.out.println(OptionCommandEnum.displayMenuConnexion());
            System.out.print("$ ");

            // Vérifier si l'entrée est un entier
            if (scanner.hasNextInt()) {
                int commande = scanner.nextInt();
                scanner.nextLine(); // Lire la fin de la ligne pour éviter les problèmes de lecture

                keepGoing = processCommand(commande);
            } else {
                // Si l'entrée n'est pas un entier, lire et ignorer l'entrée
                System.out.println("Commande non reconnue. Tapez 'help' pour la liste des commandes disponibles.");
                scanner.nextLine(); // Lire et ignorer l'entrée non valide
            }
        }
        System.exit(0);
    }

    /**
     * Affiche le nom de l'application, optionnel
     * @return le nom de l'application en chaines de caractères
     */
    private String displayHome() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n");
        sb.append("PPPP   EEEE  RRRR  RRRR  I  N    N\n");
        sb.append("P  P   E     R  R  R  R  I  NN   N\n");
        sb.append("PPPP   E     RRRR  RRRR  I  N N  N\n");
        sb.append("P      EEEE  R R   R R   I  N  N N\n");
        sb.append("P      E     R  R  R  R  I  N   NN\n");
        sb.append("P      EEEE  R  R  R  R  I  N    N\n\n");
        sb.append("Welcome. Here is the list of available commands:\n");
        return sb.toString();
    }

    /**
     * Traite la commande de l'utilisateur.
     * @param command la commande de l'utilisateur
     * @return true si l'application doit continuer à s'exécuter, sinon false
     */
    private boolean processCommand(int command) {
        switch (OptionCommandEnum.searchCommand(command)) {
            case QUIT_APPLICATION -> {
                return false;
            }
            case CONNECT_USER -> {
                connectUser();
            }
            case null, default -> {
                System.out.println("Commande non reconnue. Tapez 'help' pour la liste des commandes disponibles.");
            }
        }
        return true;
    }

    /**
     * Connecte un utilisateur.
     * @return true si l'utilisateur est connecté, sinon false
     */
    private boolean connectUser() {
        String identifiant = null;
        String mdp = null;
        while (identifiant == null) {
            System.out.print("Identifiant? ");
            identifiant = scanner.nextLine();
        }
        while (mdp == null) {
            System.out.print("Mot de passe? ");
            mdp = scanner.nextLine();
        }
        //TODO on vérifie que l'utilisateur se connecte
        // S'il se connecte, on va ajouter dans le chemin utilisateur qu'il s'est connecté et enregistrer le compte user
        // Sinon, on affiche le menu de connexion
        throw new UnsupportedOperationException("Not implemented yet");
    }

    /**
     * Point d'entrée de l'application.
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        new ConsoleApp().run();
    }
}

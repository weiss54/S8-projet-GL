package fr.ul.miage;

import fr.ul.miage.dto.ClientDTO;
import fr.ul.miage.entity.Client;
import fr.ul.miage.mapper.ClientMapper;
import fr.ul.miage.service.ClientService;

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
     * Modèle de l'application.
     */
    private final Modele modele = new Modele();

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
        sb.append("PPPP   EEEE  RRRR  RRRR  I  N   N\n");
        sb.append("P  P   E     R  R  R  R  I  NN  N\n");
        sb.append("PPPP   E     RRRR  RRRR  I  N N N\n");
        sb.append("P      EEEE  R R   R R   I  N  NN\n");
        sb.append("P      EEEE  R  R  R  R  I  N   N\n\n");
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
            case QUITTER_APPLICATION -> {
                return false;
            }
            case CONNECTION_UTILISATEUR -> connectUser();
            case INSCRIPTION_UTILISATEUR -> inscrireUser();
            case AFFICHAGE_BORNES -> System.out.println(modele.getParc().afficherListeBornes());
            case AJOUTER_BORNE -> ajouterBorne();
            case MODIFIER_BORNE -> modifierBorne();
            case null, default -> System.out.println("Commande non reconnue.");
        }
        return true;
    }


    /**
     * Inscrire un utilisateur.
     * @return true si l'utilisateur est inscrit, sinon false
     */
    private boolean inscrireUser(){
        String nom = null;
        String prenom = null;
        String adresse = null;
        String email = null;
        String numeroMobile = null;
        String numeroCb = null;
        String identifiant = null;
        String mdp = null;
        while (nom == null) {
            System.out.print("Nom? ");
            nom = scanner.nextLine();
        }
        while (prenom == null) {
            System.out.print("Prénom? ");
            prenom = scanner.nextLine();
        }
        while (adresse == null) {
            System.out.print("Adresse? ");
            adresse = scanner.nextLine();
        }
        while (email == null) {
            System.out.print("Email? ");
            email = scanner.nextLine();
        }
        while (numeroMobile == null) {
            System.out.print("Numéro de mobile? ");
            numeroMobile = scanner.nextLine();
        }
        while (numeroCb == null) {
            System.out.print("Numéro de carte bancaire? ");
            numeroCb = scanner.nextLine();
        }
        while (identifiant == null) {
            System.out.print("Identifiant? ");
            identifiant = scanner.nextLine();
        }
        while (mdp == null) {
            System.out.print("Mot de passe? ");
            mdp = scanner.nextLine();
        }
        ClientService clientService = new ClientService();
        try {
            clientService.inscrireClient(nom, prenom, adresse, email, numeroMobile, numeroCb, identifiant, mdp);
            System.out.println("Inscription réussie.");
            return true;
        } catch (Exception e) {
            System.out.println("Erreur lors de l'inscription: " + e.getMessage());
            return false;
        }
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
        ClientService clientService = new ClientService();
        try {
            ClientDTO clientDto = clientService.connecterClient(identifiant, mdp);
            Client client = new ClientMapper().toClient(clientDto);
            System.out.println("Bienvenue " + client.getPrenom() + " " + client.getNom() + "!");
            return true;
        } catch (Exception e) {
            System.out.println("Erreur lors de la connexion: " + e.getMessage());
            return false;
        }
    }

    private int selectionBorne() {
        int numero = -1;
        boolean res;
        System.out.print("Numéro de borne? ");
        if (scanner.hasNextInt()) {
            numero = scanner.nextInt();
        } else {
            System.out.println("Numéro de borne invalide.");
            scanner.nextLine();
        }
        if (numero < 0) {
            System.out.println("Numéro de borne invalide: il doit être positif.");
        }
        return numero;
    }

    private void ajouterBorne() {
        int numBorne = selectionBorne();
        if (numBorne != -1) {
            boolean res = modele.getParc().ajouterBorne(new Borne(numBorne, EtatBorne.DISPONIBLE));
            if (res) {
                System.out.println("Borne ajoutée avec succès.");
            } else {
                System.out.println("Impossible d'ajouter la borne: le numéro existe déjà.");
            }
        }
    }

    private void modifierBorne() {
        int numBorne = selectionBorne();
        Borne borne = modele.getParc().getBorne(numBorne);
        if (numBorne == -1 || borne == null) {
            System.out.println(numBorne == -1 ? "Aucune borne sélectionnée." : "Borne inexistante.");
            return;
        }
        if (borne.getEtat() != EtatBorne.INDISPONIBLE && borne.getEtat() != EtatBorne.DISPONIBLE) {
            System.out.println("Impossible de modifier cette borne. Elle est actuellement en état: " + borne.getEtat());
            return;
        }
        System.out.println(borne);
        System.out.println("Nouvel état de la borne?");
        System.out.println("1. DISPONIBLE");
        System.out.println("2. INDISPONIBLE");
        System.out.println("Votre choix: ");
        if (scanner.hasNextInt()) {
            int choix = scanner.nextInt();
            modele.getParc().modifierBorne(numBorne, choix);
        }
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

package fr.ul.miage;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Test inscription");
        System.out.println("Nom :");
        String nom = sc.nextLine();
        System.out.println("Prénom :");
        String prenom = sc.nextLine();
        System.out.println("Adresse :");
        String adresse = sc.nextLine();
        System.out.println("Email :");
        String email = sc.nextLine();
        System.out.println("Numéro mobile :");
        String numeroMobile = sc.nextLine();
        System.out.println("Numéro carte bancaire:");
        String numeroCb = sc.nextLine();
        //Client client = new Client(nom, prenom, adresse, numeroMobile, numeroCb, email);
        //System.out.println("Log Client inscrit : " + client.getNom() + " " + client.getPrenom() + " " + client.getAdresse() + " " + client.getEmail() + " " + client.getNumeroMobile() + " " + client.getNumeroCb());

        System.out.println("Nom societe :");
        String nomSociete = sc.nextLine();

        System.out.println("Login :");
        String login = sc.nextLine();
        System.out.println("Password :");
        String password = sc.nextLine();


        System.out.println("Test connexion");
    }
}
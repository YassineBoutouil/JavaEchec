package jeu;

import java.util.Scanner;

public class Joueur {

    private String nom;

    private static Scanner input = new Scanner(System.in);

    public Joueur(String nom) {
        this.nom = nom;
    }

    /**
     * Permet à l'utilisateur de saisir une case
     * @return Une chaine de caractère représentant les coordonnées saisis par l'utilisateur
     */
    public static String choisirCase() {
        String coord;
        coord = input.nextLine();
        return coord;
    }

    public String toString() {
        return this.nom;
    }

    public String getNom() {
        return this.nom;
    }

}

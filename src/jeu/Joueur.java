package jeu;

import plateau.Echiquier;

import java.util.Scanner;

public class Joueur {

    private String nom;

    public Joueur(String nom) {
        this.nom = nom;
    }

    public String choisirCase() {
        String coord;
        Scanner input = new Scanner(System.in);

        do {
            coord = input.nextLine();
        } while(!Echiquier.estFormatCoord(coord));
        return coord;
    }

    public static boolean estFormatCoord(String coord) {
        return (coord.length() == 2 && Character.isLetter(coord.charAt(0)) && coord.charAt(0) > 65
                && coord.charAt(0) < 65+((Echiquier.TAILLE_ECHIQUIER/8) -1) && Echiquier.dansEchiquier(coord.charAt(1)-48-1));
    }
	
}

package jeu;

import pieces.Piece;

import java.util.ArrayList;
import java.util.Scanner;

public class Joueur {

    private String nom;
    private final ArrayList<Piece> piecesCapturees = new ArrayList<>();

    public Joueur(String nom) {
        this.nom = nom;
    }

    public String choisirCase() {
        String coord;
        Scanner input = new Scanner(System.in);
        coord = input.nextLine();
        input.close();
        return coord;
    }

    public void ajouterCapture(Piece piece) {
        piecesCapturees.add(piece);
    }
	
}

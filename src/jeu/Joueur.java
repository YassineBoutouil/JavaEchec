package jeu;

import pieces.Piece;

import java.util.ArrayList;
import java.util.Scanner;

public class Joueur {

    private String nom;
    private ArrayList<Piece> pieces = new ArrayList<>();
    private final ArrayList<Piece> piecesCapturees = new ArrayList<>();
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

    public void ajouterCapture(Piece piece) {
        this.piecesCapturees.add(piece);
    }

    public void ajouterPieceJoueur(Piece piece) {
        this.pieces.add(piece);
    }

    public void setPieceJoueur(ArrayList<Piece> pieces) {
        this.pieces = pieces;
    }


    public String toString() {
        return this.nom;
    }
}

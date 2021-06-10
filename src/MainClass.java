import jeu.Partie;
import pieces.*;
import plateau.Echiquier;

public class MainClass {
    public static void main(String[] args) {
        // Verif pat
        /*Echiquier plateau = new Echiquier(true);
        plateau.ajouterPiece(new Roi(true), 0, 7);
        plateau.ajouterPiece(new Roi(false), 7, 0);
        plateau.ajouterPiece(new Dame(true), 2, 1);
        Partie partie = new Partie(plateau);
        partie.start();*/

        new Partie().start();
    }
}

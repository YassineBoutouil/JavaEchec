import jeu.Partie;
import pieces.Dame;
import pieces.Roi;
import pieces.Tour;
import plateau.Echiquier;

public class MainClass {
    public static void main(String[] args) {
        Partie partie = new Partie(new Echiquier(true));
        Echiquier plateau = partie.getPlateau();
        plateau.ajouterPiece(new Roi(true), 0, 0);
        plateau.ajouterPiece(new Dame(true), 1, 1);
        plateau.ajouterPiece(new Roi(false), 7, 7);
        plateau.ajouterPiece(new Tour(false), 5, 5);
        plateau.ajouterPiece(new Dame(false), 6, 6);
        partie.start();
    }
}

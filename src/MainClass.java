import pieces.NomPiece;
import plateau.Echiquier;

public class MainClass {
    public static void main(String[] args) {
        Echiquier plateau = new Echiquier();
        System.out.println(plateau);
        plateau.ajouterPiece(NomPiece.Tour, true, 3, 3);

    }
}

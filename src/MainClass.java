import plateau.Case;
import plateau.Echiquier;

public class MainClass {
    public static void main(String[] args) {
        Echiquier plateau = new Echiquier();
        plateau.ajouterPiece("Tour", false, 3, 3);
        Case c = new Case(2, 2);
        System.out.println(c.equals(plateau.getCasePlateau(2, 2)));
        System.out.println(plateau);
    }
}

import plateau.Echiquier;

public class MainClass {
    public static void main(String[] args) {
        Echiquier plateau = new Echiquier();
        System.out.println(plateau);
        plateau.ajouterPiece("Cavalier", true,4, 4);
        plateau.ajouterPiece("Cavalier", false, 6, 4);
        System.out.println(plateau);
        System.out.println(plateau.estValide(plateau.getCasePlateau(4, 4), plateau.getCasePlateau(5, 2)));
    }
}

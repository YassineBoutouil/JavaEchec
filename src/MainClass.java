import plateau.Echiquier;

public class MainClass {
    public static void main(String[] args) {
        Echiquier plateau = new Echiquier();
        System.out.println(plateau);
        plateau.ajouterPiece("Tour", true,4, 4);
        System.out.println(plateau);
        System.out.println(plateau.getCasePlateau(4, 7));
        System.out.println();
    }
}

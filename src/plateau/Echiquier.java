package plateau;

import pieces.NomPiece;
import pieces.Piece;

import java.awt.*;

public class Echiquier {

    public final static int TAILLE_ECHIQUIER = 64;
    public final Case[][] plateau = new Case[TAILLE_ECHIQUIER/8][TAILLE_ECHIQUIER/8];

    public Echiquier() {
        for(int y = 0; y < (TAILLE_ECHIQUIER/8); y++) {
            for(int x = 0;  x < (TAILLE_ECHIQUIER/8); x++) {
                this.setCasePlateau(new Case(x, y));
            }
        }
        initialiserPiece();
        /*initialiserPiece(true,
                new String[]{"Tour", "Cavalier", "Fou",  "Dame",  "Roi",   "Fou", "Cavalier", "Tour",
                "Pion",   "Pion",   "Pion", "Pion",  "Pion",  "Pion",  "Pion",   "Pion"}
        );

        initialiserPiece(false,
                new String[]{"Tour", "Cavalier", "Fou",  "Dame",  "Roi",   "Fou", "Cavalier", "Tour",
                "Pion",   "Pion",   "Pion", "Pion",  "Pion",  "Pion",  "Pion",   "Pion"}
        );*/
    }

    public void initialiserPiece() {
        ajouterPiece(NomPiece.Tour, true, 0, 0); ajouterPiece(NomPiece.Cavalier, true, 1, 0);
        ajouterPiece(NomPiece.Fou, true, 2, 0); ajouterPiece(NomPiece.Dame, true, 3, 0);
        ajouterPiece(NomPiece.Roi, true, 4, 0); ajouterPiece(NomPiece.Fou, true, 5, 0);
        ajouterPiece(NomPiece.Cavalier, true, 6, 0); ajouterPiece(NomPiece.Tour, true, 7, 0);

        ajouterPiece(NomPiece.Pion, true, 0, 1); ajouterPiece(NomPiece.Pion, true, 1, 1);
        ajouterPiece(NomPiece.Pion, true, 2, 1); ajouterPiece(NomPiece.Pion, true, 3, 1);
        ajouterPiece(NomPiece.Pion, true, 4, 1); ajouterPiece(NomPiece.Pion, true, 5, 1);
        ajouterPiece(NomPiece.Pion, true, 6, 1); ajouterPiece(NomPiece.Pion, true, 7, 1);

        ajouterPiece(NomPiece.Tour, false, 0, 7); ajouterPiece(NomPiece.Cavalier, false, 1, 7);
        ajouterPiece(NomPiece.Fou, false, 2, 7); ajouterPiece(NomPiece.Dame, false, 3, 7);
        ajouterPiece(NomPiece.Roi, false, 4, 7); ajouterPiece(NomPiece.Fou, false, 5, 7);
        ajouterPiece(NomPiece.Cavalier, false, 6, 7); ajouterPiece(NomPiece.Tour, false, 7, 7);

        ajouterPiece(NomPiece.Pion, false, 0, 6); ajouterPiece(NomPiece.Pion, false, 1, 6);
        ajouterPiece(NomPiece.Pion, false, 2, 6); ajouterPiece(NomPiece.Pion, false, 3, 6);
        ajouterPiece(NomPiece.Pion, false, 4, 6); ajouterPiece(NomPiece.Pion, false, 5, 6);
        ajouterPiece(NomPiece.Pion, false, 6, 6); ajouterPiece(NomPiece.Pion, false, 7, 6);
    }

    @Override
    public String toString() {
        StringBuilder chaine = new StringBuilder("    -");

        for(int i = 1; dansEchiquier(i); i++) chaine.append("--");
        chaine.append("\n    ");
        for(int i = 0; dansEchiquier(i); i++) chaine.append((char)(65 + i)).append(" ");
        chaine.insert(0, "\n");

        for(int ligne = 0; dansEchiquier(ligne); ligne++) {
            for(int colonne = (TAILLE_ECHIQUIER/8)-1; dansEchiquier(colonne); colonne--) {
                chaine.insert(0, " " + this.getCasePlateau(colonne, ligne).toStringPiece());
            }
            chaine.insert(0, "\n" + (ligne+1) + " |");
        }
        return chaine.toString();

    }

    public void ajouterPiece(Piece piece, int x, int y) {
        this.getCasePlateau(x, y).setPiece(piece);
    }

    public void ajouterPiece(NomPiece piece, boolean couleur, int x, int y) {
        try {
            this.getCasePlateau(x, y).setPiece(Piece.parse(piece, couleur));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setCasePlateau(Case c) {
        this.plateau[c.getLigne()][c.getColonne()] = c;
    }

    public Case getCasePlateau(int colonne, int ligne) {
        return this.plateau[ligne][colonne];
    }


    public boolean estValide(Case caseDepart, Case caseArrivee) {
        int x_init, y_init;
        int x = 0, y = 0;
        if (!caseDepart.estVide() && !caseDepart.memeCase(caseArrivee) && caseDepart.getPiece().verifDeplacement(caseArrivee)) {
            Point p = caseDepart.getPiece().directionDeplacement(caseArrivee);
            x = caseDepart.getColonne() + ((int) p.getX());
            y = caseDepart.getLigne() + ((int) p.getY());
            x_init = (int) p.getX();
            y_init = (int) p.getY();
            while (dansEchiquier(x, y) && getCasePlateau(x, y).estVide() && !getCasePlateau(x, y).memeCase(caseArrivee)) {
                x += x_init;
                y += y_init;
            }
            return (dansEchiquier(x, y) && getCasePlateau(x, y).memeCase(caseArrivee) &&
                    (caseArrivee.estVide() || caseDepart.getPiece().couleurOpposee(caseArrivee.getPiece())));
        }
        return false;
    }

    public boolean deplacerPiece(Case caseDepart, Case caseArrivee) {
        if(!caseDepart.estVide()) {
            caseArrivee.setPiece(caseDepart.getPiece());
            caseDepart.viderCase();
            return true;
        }
        return false;
    }

    public Case traduireCoord(String coord) {
        if(estFormatCoord(coord)) {
            throw new IllegalArgumentException("Coordonnées non valide.");
        }
        int ligne, colonne;
        colonne = coord.charAt(0) - 65;
        ligne = coord.charAt(1) - 48 -1;
        return this.getCasePlateau(colonne, ligne);
    }

    public static boolean estFormatCoord(String coord) {
        return (coord.length() == 2 && Character.isLetter(coord.charAt(0)) && coord.charAt(0) > 65
                && coord.charAt(0) < 65+((TAILLE_ECHIQUIER/8) -1) && dansEchiquier(coord.charAt(1)-48-1));
    }

    private static boolean dansEchiquier(int colonne, int ligne) {
        return dansEchiquier(colonne) && dansEchiquier(ligne);
    }

    public static boolean dansEchiquier(int x) {
        return 0 <= x && x < TAILLE_ECHIQUIER/8;
    }
}


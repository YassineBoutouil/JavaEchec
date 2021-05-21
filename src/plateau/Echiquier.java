package plateau;

import pieces.Piece;

import java.awt.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class Echiquier {

    public final static int TAILLE_ECHIQUIER = 64;
    public final Case[] plateau = new Case[TAILLE_ECHIQUIER];

    public Echiquier() {
        for(int y = 1; y < (TAILLE_ECHIQUIER/8)+1; y++) {
            for(int x = 1;  x < (TAILLE_ECHIQUIER/8)+1; x++) {
                this.setCasePlateau(new Case(x, y));
            }
        }
        initialiserPiece(true,
        "Tour", "Cavalier", "Fou",  "Dame",  "Roi",   "Fou", "Cavalier", "Tour",
        "Pion",   "Pion",   "Pion", "Pion",  "Pion",  "Pion",  "Pion",   "Pion"
        );

        initialiserPiece(false,
        "Tour", "Cavalier", "Fou",  "Dame",  "Roi",   "Fou", "Cavalier", "Tour",
        "Pion",   "Pion",   "Pion", "Pion",  "Pion",  "Pion",  "Pion",   "Pion"
        );
    }

    @Override
    public String toString() {
        String chaine = "";
        for (int y = TAILLE_ECHIQUIER/8; y > 0; y--) {
            for(int x = 1; x < TAILLE_ECHIQUIER/8 +1 ; x++) {
                chaine += getCasePlateau(x , y).toStringPiece() + "  ";
                if (x == 8) {
                    chaine += "\n";
                }
            }
        }
        return chaine;

    }

    public void ajouterPiece(Piece piece, int x, int y) {
        this.getCasePlateau(x, y).setPiece(piece);
    }

    public void ajouterPiece(String piece, boolean couleur, int x, int y) {
        try {
            Class<?> piece_class = Class.forName("pieces."+piece);
            Constructor<?> piece_constructor = piece_class.getConstructor(boolean.class);
            this.getCasePlateau(x, y).setPiece((Piece) piece_constructor.newInstance(new Object[]{couleur}));
            this.getCasePlateau(x, y).getPiece().setCase(this.getCasePlateau(x, y));
        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException ignored) {

        }
    }

    private void setCasePlateau(Case c) {
        this.plateau[getCoordonnee(c)] = c;
    }

    public Case getCasePlateau(int x, int y) {
        return this.plateau[getCoordonnee(x, y)];
    }

    public Case getCasePlateau(int i) {
        return this.plateau[i];
    }

    public void initialiserPiece(boolean b, String... args) {
        int incremente = -1, x = 1, y = 8;
        if (b) { incremente = 1; y = 1; }
        for(String piece : args) {
            ajouterPiece(piece, b, x, y);
            x++;
            if (x == 9) {
                x = 1;
                y = y + incremente;
            }
        }
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
            System.out.println(p);
            while (dansEchiquier(x, y) && getCasePlateau(x, y).estVide() && !getCasePlateau(x, y).memeCase(caseArrivee)) {
                x += x_init;
                y += y_init;
            }
            return (dansEchiquier(x, y) && getCasePlateau(x, y).memeCase(caseArrivee) &&
                   (caseArrivee.estVide() || caseDepart.getPiece().couleurOpposee(caseArrivee.getPiece())));
        }
        return false;
    }

    public void deplacerPiece(Case caseDepart, Case CaseArrivee) {

    }

    private static int getCoordonnee(int x, int y) {
        return ((y-1)*8)+x-1;
    }

    private static int getCoordonnee(Case c) {
        return getCoordonnee(c.getColonne(), c.getLigne());
    }
    private static boolean dansEchiquier(int x, int y) {
        return 0 < x && x <= 8 && 0 < y && y <= 8;
    }
}

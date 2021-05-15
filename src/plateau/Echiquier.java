package plateau;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import pieces.Piece;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

@objid ("e24132db-b559-4888-b9fd-4086fc39a182")
public class Echiquier {

    @objid ("8d325553-3ae3-4d0a-b888-879dbb31cf28")

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
            for(int x = 1; x < TAILLE_ECHIQUIER/8 ; x++) {
                chaine += getCasePlateau(x , y).toStringPiece() + "  ";
                if (x == 7) {
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

    private static int getCoordonnee(Case c) {
        return getCoordonnee(c.getColonne(), c.getLigne());
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

    private static int getCoordonnee(int x, int y) {
        return ((y-1)*8)+x-1;
    }

}

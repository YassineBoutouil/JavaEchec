package pieces;

import plateau.Case;

import java.awt.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public abstract class Piece {
    protected Case case_piece;

    protected final boolean couleur;

    protected boolean dejaJoue = false;

    public Piece(boolean uneCouleur) {
        this.couleur = uneCouleur;
    }

    public abstract boolean verifDeplacement(Case case_p);

    protected int calculNombreCaseDeplacement(Case case_p) {
        return (Math.abs(this.case_piece.getLigne()-case_p.getLigne())
                + Math.abs(this.case_piece.getColonne()-case_p.getColonne()));
    }

    public Point directionDeplacement(Case case_p) {
        int x = 0, y = 0;
        if (!this.case_piece.memeColonne(case_p)) {
            x = (case_p.getColonne() - this.case_piece.getColonne()) / Math.abs(case_p.getColonne() - this.case_piece.getColonne());
        }
        if (!this.case_piece.memeLigne(case_p)) {
            y = (case_p.getLigne() - this.case_piece.getLigne()) / Math.abs(case_p.getLigne() - this.case_piece.getLigne());
        }
        return new Point(x, y);
    }

    public boolean estBlanc() {
        return this.couleur;
    }

    public void bouge() {
        this.dejaJoue = true;
    }

    public void setCase(Case c) {
        this.case_piece = c;
    }

    public Case getCase() {
        return this.case_piece;
    }

    public boolean couleurOpposee(Piece p) {
        return (this.estBlanc() ^ p.estBlanc());
    }

    public static Piece parse(NomPiece piece, boolean couleur) throws IllegalAccessException, InvocationTargetException, InstantiationException, ClassNotFoundException, NoSuchMethodException {
        Piece new_piece;
        Class<?> piece_class = Class.forName("pieces."+piece);
        Constructor<?> piece_constructor = piece_class.getConstructor(boolean.class);
        new_piece = ((Piece) piece_constructor.newInstance(new Object[]{couleur}));

        return new_piece;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName()+"{" +
                "couleur=" + couleur +
                '}';
    }

    public abstract String toStringAffiche();
}

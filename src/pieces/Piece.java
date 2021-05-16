package pieces;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import plateau.Case;

import java.awt.*;

@objid ("3b9c3832-b223-45a0-92db-331b1930e541")
public abstract class Piece {
    @objid ("e1b201cf-38e4-4cf0-82cb-62f102176ed3")
    protected Case case_piece;

    protected final boolean couleur;

    protected boolean dejaJoue = false;

    public Piece(boolean uneCouleur) {
        this.couleur = uneCouleur;
    }

    public abstract boolean verifDeplacement(Case case_p);

    protected boolean estRectiligne(Case case_p) {
        return this.case_piece.memeLigne(case_p) || this.case_piece.memeColonne(case_p);
    }

    protected boolean estDiagonal(Case case_p) {
        return Math.abs(this.case_piece.getLigne() - case_p.getLigne()) ==
               Math.abs(this.case_piece.getColonne() - case_p.getColonne());
    }

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

    public void aJoue() {
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

    @Override
    public String toString() {
        return this.getClass().getSimpleName()+"{" +
                "couleur=" + couleur +
                '}';
    }

    public abstract String toStringAffiche();
}

package pieces;

import plateau.Case;

import java.awt.*;
import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public abstract class Piece implements Serializable {
    protected Case case_piece;

    protected final boolean couleur;

    protected boolean dejaJoue = false;

    public Piece(boolean uneCouleur) {
        this.couleur = uneCouleur;
    }

    /**
     * Indique si le déplacement vers la case est conforme au modèle de déplacement de la pièce.
     * @param case_p une case.
     * @return True si le déplacement est correcte.
     */
    public abstract boolean verifDeplacement(Case case_p);


    protected int calculNombreCaseDeplacement(Case case_p) {
        return (Math.abs(this.case_piece.getLigne()-case_p.getLigne())
                + Math.abs(this.case_piece.getColonne()-case_p.getColonne()));
    }

    /**
     * Permet de récupérer la direction de déplacement de la pièce vers une case
     * @param case_p Une case
     * @return un Point(x, y) qui représente la direction de déplacement.
     */
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

    /**
     * Permet de savoir la couleur de la pièce
     * @return true si la pièce est blanche
     */
    public boolean estBlanc() {
        return this.couleur;
    }

    /**
     * Indique que la pièce a joué
     */
    public void aBouge() {
        this.dejaJoue = true;
    }

    /**
     * Change la case où se situe la pièce courante.
     * @param c une Case
     */
    public void setCase(Case c) {
        this.case_piece = c;
    }

    /**
     * Sort la pièce de sa case
     */
    public void enleverPieceCase() {
        this.case_piece = null;
    }

    /**
     * Indique si la pièce est dans une case
     * @return true si la pièce est dans une case
     */
    public boolean estDansUneCase() {
        return !(this.case_piece == null);
    }

    /**
     * Renvoie la case de la pièce
     * @return la case où est la pièce
     */
    public Case getCase() {
        return this.case_piece;
    }

    /**
     * Indique si la pièce courante est de couleur opposée à celle passée en paramètre.
     * @param p Une pièce.
     * @return true si la pièce passée en paramètre est de couleur opposée.
     */
    public boolean couleurOpposee(Piece p) {
        return (this.estBlanc() ^ p.estBlanc());
    }

    /**
     * @see #couleurOpposee(Piece)
     */
    public boolean couleurOpposee(boolean couleur) {
        return (this.estBlanc() ^ couleur);
    }

    /**
     * Permet de construire une pièce à partir d'une chaine de caractère et d'une couleur.
     * @param piece un String de type NomPiece.
     * @param couleur une couleur.
     * @return une nouvelle pièce d'échec en fonction des paramètres.

     */
    public static Piece parse(NomPiece piece, boolean couleur) throws IllegalAccessException, InvocationTargetException, InstantiationException, ClassNotFoundException, NoSuchMethodException {
        Piece new_piece;
        Class<?> piece_class = Class.forName("pieces."+piece);
        Constructor<?> piece_constructor = piece_class.getConstructor(boolean.class);
        new_piece = ((Piece) piece_constructor.newInstance(new Object[]{couleur}));

        return new_piece;
    }

    /**
     * Renvoie une copie de la pièce d'échecs.
     * @return une copie de la pièce d'échecs courante.
     */
    public Piece copy() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Piece new_piece;
        Class<?> piece_class = Class.forName("pieces."+this.getClass().getSimpleName());
        Constructor<?> piece_constructor = piece_class.getConstructor(boolean.class);
        new_piece = ((Piece) piece_constructor.newInstance(new Object[]{this.estBlanc()}));

        return new_piece;
    }

    @Override
    public String toString() {
        StringBuilder chaine = new StringBuilder(this.getClass().getSimpleName()+"{" +
                                                "couleur=" + couleur + ", ");
        if(this.estDansUneCase()) {
            chaine.append(this.case_piece.toStringCallInPiece());
        } else {
            chaine.append("Case=null");
        }
        chaine.append("}");
        return chaine.toString();
    }

    public String toStringSimple() {
        return this.getClass().getSimpleName() +
                "{couleur=" + this.couleur+"}";
    }

    public abstract String toStringPiece();
}

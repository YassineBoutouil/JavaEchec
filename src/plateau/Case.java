package plateau;

import pieces.NomPiece;
import pieces.Piece;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;

public class Case implements Serializable {

    private final int ligne; // y
    private final int colonne; // x
    private final boolean couleur; // true = blanc

    private Piece piece = null; // Reference null == case vide

    /**
    * Contruis une case vide avec la couleur correspondante au coordonnée
    * @param x colonne
    * @param y ligne
    */
    public Case(int x, int y) {
        this.colonne = x;
        this.ligne = y;
        this.couleur = !((this.colonne + this.ligne) % 2 == 0); // Si impair claire sinon foncée
    }

    /**
     * Constructeur par copie d'une case
     * @param uneCase la case à copier
     */
    public Case(Case uneCase) {
        this.colonne = uneCase.getColonne();
        this.ligne = uneCase.getLigne();
        this.couleur = uneCase.estClaire();
        if(!uneCase.estVide()) {
            try {
                this.piece = uneCase.getPiece().copy();
                this.piece.setCase(this);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            this.piece = null;
        }
    }

    /**
     * Construis une case avec une pièce
     * @param unePiece pièce contenu dans la case
     * @param x colonne
     * @param y ligne
     */
    public Case(Piece unePiece, int x, int y) {
        this(x ,y);
        this.piece = unePiece;
        this.piece.setCase(this);
    }

    /**
     *
     * @return La pièce contenu dans la case
     */
    public Piece getPiece() {
        return this.piece;
    }

    /**
     * Modifie la pièce contenu dans la case de telle sorte
     * qu'elle référence l'instance courante de case.
     * @param p
     */
    public void setPiece(Piece p) {
        if (!(p.getCase() == null)) {
            p.getCase().viderCase();
        }
        if(!(getPiece() == null)) {
            getPiece().enleverPieceCase();
        }
        p.setCase(this);
        this.piece = p;
    }

    /**
     * Retire la pièce contenu dans la case.
     */
    public void viderCase() {
        this.piece = null;
    }

    /**
     * @return la ligne de la case.
     */
    public int getLigne() {
        return this.ligne;
    }

    /**
     * @return la colonne de la case.
     */
    public int getColonne() {
        return this.colonne;
    }

    /**
     * Renvoie la couleur de la case.
     * @return true si la case est claire false si la case est foncée.
     */
    public boolean estClaire() {
        return this.couleur;
    }

    /**
     * Renvoie si la colonne de la case courante est égale à celle de la case
     * passée en paramètre
     * @param c Case testée
     * @return true si la case a la même colonne
     */
    public boolean memeColonne(Case c) {
        return this.colonne == c.getColonne();
    }

    /**
     * Renvoie si la ligne de la case courante est égale à celle de la case
     * passée en paramètre
     * @param c une case
     * @return true true si la case a la même ligne
     */
    public boolean memeLigne(Case c) {
        return this.ligne == c.getLigne();
    }

    /**
     * Renvoie si la case passée en paramètre est égale à celle courante
     * @param c une case
     * @return true si la case est identique
     */
    public boolean memeCase(Case c) {
        return this.memeColonne(c) && this.memeLigne(c);
    }

    /**
     * Renvoie si la case est vide. (case vide == null)
     * @return true si la case est vide
     */
    public boolean estVide() {
        return this.piece == null;
    }

    /**
     * Renvoie si la case courante est sur la même ligne ou même colonne que celle passée en paramètre
     * @param case_p une case
     * @return true si est sur la même ligne ou même colonne
     */
    public boolean estRectiligne(Case case_p) {
        return this.memeLigne(case_p) || this.memeColonne(case_p);
    }

    /**
     * Renvoie si la case courante est sur la même diagonale que celle passée en paramètre
     * @param case_p une case
     * @return true si est sur la même diagonale.
     */
    public boolean estDiagonal(Case case_p) {
        return Math.abs(this.getLigne() - case_p.getLigne()) ==
                Math.abs(this.getColonne() - case_p.getColonne());
    }

    /**
     * Renvoie une case vide correspondante aux coordonnées passées en paramètre.
     * @param x colonne de la case.
     * @param y ligne de la case.
     * @return une nouvelle case.
     */
    public static Case parse(int x, int y) {
        return new Case(x, y);
    }

    /**
     * Construis et renvoie une case contenant une pièce.
     * @param piece Nom de la pièce.
     * @param couleur Couleur de la pièce.
     * @param x Colonne de la case.
     * @param y Ligne de la case.
     * @return Une nouvelle case contenant une pièce
     * @see pieces.Piece#parse(NomPiece, boolean)
     */
    public static Case parse(NomPiece piece, boolean couleur, int x, int y) throws InvocationTargetException, ClassNotFoundException, InstantiationException, NoSuchMethodException, IllegalAccessException {
        return new Case(Piece.parse(piece, couleur), x, y);
    }

    public String toStringPiece(){
        if (this.piece == null) {
            return "~";
        }
        return this.piece.toStringPiece();
    }

    @Override
    public String toString() {
        return "Case{" +
                "("+this.colonne+", "+this.ligne+")"+
                ", piece=" + this.piece +
                '}';
    }

    public String toStringCallInPiece() {
        return "Case{" +
                "("+this.colonne+", "+this.ligne+")";
    }
}

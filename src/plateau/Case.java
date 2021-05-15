package plateau;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import pieces.Piece;

import java.util.HashSet;
import java.util.Objects;

@objid ("0533cf57-8107-42c5-a8fa-be3915234795")
public class Case {

    public final static HashSet<Case> casePlateau = new HashSet<>();

    private final int ligne; // y
    private final int colonne; // x

    @objid ("8501d6a7-a8e5-4d16-8db3-718d93fba5d4")
    private Piece piece = null; // Reference null == case vide

    public Case(int x, int y) {
        this.colonne = x;
        this.ligne = y;
        Case.casePlateau.add(this);
    }

    public Case(Piece unePiece, int x, int y) {
        this(x ,y);
        this.piece = unePiece;
        this.piece.setCase(this);
    }

    public Piece getPiece() {
        return this.piece;
    }

    public void setPiece(Piece p) {
        if (!(p.getCase() == null)) {
            p.getCase().viderCase();
        }
        p.setCase(this);
        this.piece = p;
    }

    public void viderCase() {
        this.piece = null;
    }

    public int getLigne() {
        return this.ligne;
    }

    public int getColonne() {
        return this.colonne;
    }

    public boolean memeColonne(Case c) {
        return this.colonne == c.getColonne();
    }

    public boolean memeLigne(Case c) {
        return this.ligne == c.getLigne();
    }

    public boolean memeCase(Case c) {
        return this.memeColonne(c) && this.memeLigne(c);
    }

    public boolean estVide() {
        return this.piece == null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }
        if (o == null || getClass() != o.getClass()){
            return false;
        }
        Case aCase = (Case) o;
        return ligne == aCase.ligne && colonne == aCase.colonne;
    }


    public String toStringPiece(){
        if (this.piece == null) {
            return "-";
        }
        return this.piece.toStringAffiche();
    }

    @Override
    public int hashCode() {
        return Objects.hash(ligne, colonne);
    }

    @Override
    public String toString() {
        return "Case{" +
                "("+this.colonne+", "+this.ligne+")"+
                ", piece=" + this.piece +
                '}';
    }
}

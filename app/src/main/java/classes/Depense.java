package classes;


import java.io.Serializable;
import java.util.Date;

public class Depense implements Serializable {

    private Integer idDepense;
    private Date dateDepense;
    private double montant;
    private String pieceJoint;
    private Magasin magasin;
    private String domaine;
    private Date garantieDebut;
    private Date garantieFin;
    private Utilisateur utilisatuer;

    public Depense(Integer idDepense, Date dateDepense, double montant, Utilisateur utilisatuer, String domaine, Magasin magasin, String pieceJoint, Date garantieDebut, Date garantieFin) {
        this.idDepense = idDepense;
        this.dateDepense = dateDepense;
        this.montant = montant;
        this.utilisatuer = utilisatuer;
        this.domaine = domaine;
        this.magasin = magasin;
        this.pieceJoint = pieceJoint;
        this.garantieDebut = garantieDebut;
        this.garantieFin = garantieFin;
    }

    public Depense(Integer idDepense, Date dateDepense, double montant, Utilisateur utilisatuer, String domaine, Magasin magasin, String pieceJoint) {
        this.idDepense = idDepense;
        this.dateDepense = dateDepense;
        this.montant = montant;
        this.utilisatuer = utilisatuer;
        this.domaine = domaine;
        this.magasin = magasin;
        this.pieceJoint = pieceJoint;
        this.garantieDebut = null;
        this.garantieFin = null;
    }

    public Integer getIdDepense() {
        return idDepense;
    }

    public Date getDateDepense() {
        return dateDepense;
    }

    public double getMontant() {
        return montant;
    }

    public String getPieceJoint() {
        return pieceJoint;
    }

    public Magasin getMagasin() {
        return magasin;
    }

    public String getDomaine() {
        return domaine;
    }

    public void setDomaine(String domaine) {
        this.domaine = domaine;
    }

    public Utilisateur getUtilisatuer() {
        return utilisatuer;
    }

    public Date getGarantieDebut() {
        return garantieDebut;
    }

    public Date getGarantieFin() {
        return garantieFin;
    }

}


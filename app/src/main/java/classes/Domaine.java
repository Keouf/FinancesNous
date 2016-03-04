package classes;

/**
 * Created by Nicolas on 04/02/2016.
 */
public class Domaine {

    private int idDomaine;
    private String nomDomaine;

    public Domaine(int idDomaine, String nomDomaine) {
        this.idDomaine = idDomaine;
        this.nomDomaine = nomDomaine;
    }

    public void setIdDepense(int idDomaine) {
        this.idDomaine = idDomaine;
    }

    public int getIdDomaine() {
        return this.idDomaine;
    }

    public String getNomDomaine() {
        return this.nomDomaine;
    }

    public void setNomDomaine(String nomDomaine) {
        this.nomDomaine = nomDomaine;
    }

}

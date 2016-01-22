package classes;


public class Domaine {

    private int idDomaine;
    private String nomDomaine;

    public int getIdDomaine() {
        return idDomaine;
    }
    public void setIdDomaine(int idDomaine) {
        this.idDomaine = idDomaine;
    }
    public String getNomDomaine() {
        return nomDomaine;
    }
    public void setNomDomaine(String nomDomaine) {
        this.nomDomaine = nomDomaine;
    }

    public Domaine(int idDomaine, String nomDomaine) {
        this.idDomaine = idDomaine;
        this.nomDomaine = nomDomaine;
    }
}

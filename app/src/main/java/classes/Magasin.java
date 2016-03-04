package classes;


import java.io.Serializable;

public class Magasin implements Serializable {

    private int id;
    private String nom_managasin;
    private String adresse1;
    private String adresse2;
    private String codePostal;
    private String siteWeb;
    private String telephone;

    public Magasin(int id, String nom_managasin, String adresse1, String adresse2, String codePostal, String siteWeb, String telephone) {
        this.id = id;
        this.nom_managasin = nom_managasin;
        this.adresse1 = adresse1;
        this.adresse2 = adresse2;
        this.codePostal = codePostal;
        this.siteWeb = siteWeb;
        this.telephone = telephone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom_managasin() {
        return nom_managasin;
    }

    public void setNom_managasin(String nom_managasin) {
        this.nom_managasin = nom_managasin;
    }

    public String getAdresse1() {
        return adresse1;
    }

    public void setAdresse1(String adresse1) {
        this.adresse1 = adresse1;
    }

    public String getAdresse2() {
        return adresse2;
    }

    public void setAdresse2(String adresse2) {
        this.adresse2 = adresse2;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    public String getSiteWeb() {
        return siteWeb;
    }

    public void setSiteWeb(String siteWeb) {
        this.siteWeb = siteWeb;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
}

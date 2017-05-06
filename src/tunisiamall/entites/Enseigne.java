/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisiamall.entites;


import java.util.Vector;


public class Enseigne {
    private String nom;
    private String adresse;
    private String loginResponsableEnseigne;
    private String url;
    private String info;
    Vector Produits;
    Vector Boutiques;
    Vector Catalogues;

    public Enseigne() {
    }

    public Enseigne(String nom, String adresse) {
        this.nom = nom;
        this.adresse = adresse;
        Produits=new Vector();//set
        Boutiques=new Vector();//set
        Catalogues=new Vector();//???
    }

    public Enseigne(String nom, String adresse, String loginResponsableEnseigne) {
        this.nom = nom;
        this.adresse = adresse;
        this.loginResponsableEnseigne = loginResponsableEnseigne;
        
    }

    public Enseigne(String nom, String adresse, String loginResponsableEnseigne, String url) {
        this.nom = nom;
        this.adresse = adresse;
        this.loginResponsableEnseigne = loginResponsableEnseigne;
        this.url = url;
    }

    public Enseigne(String nomE, String adresse, String loginE, String url, String info) {
        this.nom=nomE;
        this.adresse=adresse;
        this.loginResponsableEnseigne=loginE;
        this.url=url;
        this.info=info;
    }
    

    public String getLoginResponsableEnseigne() {
        return loginResponsableEnseigne;
    }

    public void setLoginResponsableEnseigne(String loginResponsableEnseigne) {
        this.loginResponsableEnseigne = loginResponsableEnseigne;
    }
    
    
    
    public String getNom() {
        return nom;
    }

  
    

    public String getAdresse() {
        return adresse;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String toString() {
        return "Enseigne{" + "nom=" + nom + ", adresse=" + adresse + '}';
    }
    
}

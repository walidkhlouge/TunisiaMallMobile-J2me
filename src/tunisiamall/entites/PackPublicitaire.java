/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisiamall.entites;




public class PackPublicitaire {
    private int id;
    private float prix;
    private String nom;
    private String desc;

    public PackPublicitaire() {
    }

    public PackPublicitaire(float prix) {
        this.prix = prix;
     
    }

    public PackPublicitaire(int id, float prix) {
        this.id = id;
        this.prix = prix;
    }
    
      public PackPublicitaire(int id,String nom,float prix,String desc) {
        this.id = id;
        this.prix = prix;
        this.nom=nom;
        this.desc=desc;
    }

    public PackPublicitaire(String nom, String desc) {
        this.nom = nom;
        this.desc = desc;
    }

    public PackPublicitaire(float prix, String nom, String desc) {
        this.prix = prix;
        this.nom = nom;
        this.desc = desc;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }
    

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }


    public String toString() {
        return "PackPublicitaire{" + "prix=" + prix + '}';
    }
    
}

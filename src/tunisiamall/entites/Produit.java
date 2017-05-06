/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisiamall.entites;


public class Produit {
   private String id;
   private String nom;
   private String quantite;
   private String prixUnitaire;
   private String idBoutique;
   private String tauxReduction;
   private String nomEnseigne;
   private String reference;
   private String info;
   private String url;
private int pts;

    public Produit(String idProduit, String nomEnseigne, String idBoutique, String nom, String quantite, String prixVenteUnitaire, String tauxReduction, String reference, String info, String url, String categorie) {
        this.id=idProduit;
        this.nomEnseigne=nomEnseigne;
        this.idBoutique=idBoutique;
        this.nom=nom;
        this.quantite=quantite;
        this.prixUnitaire=prixVenteUnitaire;
        this.tauxReduction=tauxReduction;
        this.reference=reference;
        this.info=info;
        this.url=url;
    }

    public int getPts() {
        return pts;
    }

    public void setPts(int pts) {
        this.pts = pts;
    }

    public Produit(String id, String nom, String quantite, String prixUnitaire, String idBoutique, String tauxReduction, String nomEnseigne, String reference, String info, String url) {
        this.id = id;
        this.nom = nom;
        this.quantite = quantite;
        this.prixUnitaire = prixUnitaire;
        this.idBoutique = idBoutique;
        this.tauxReduction = tauxReduction;
        this.nomEnseigne = nomEnseigne;
        this.reference = reference;
        this.info = info;
        this.url = url;
    }

    public Produit(int id, String nomP, String nomE, String info, String url, int ptss) {
        this.id=this.id;
        this.nom=nomP;
        this.nomEnseigne=nomE;
        this.info=info;
        this.url=url;
        this.pts = ptss;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getQuantite() {
        return quantite;
    }

    public void setQuantite(String quantite) {
        this.quantite = quantite;
    }

    public String getPrixUnitaire() {
        return prixUnitaire;
    }

    public void setPrixUnitaire(String prixUnitaire) {
        this.prixUnitaire = prixUnitaire;
    }

    public String getIdBoutique() {
        return idBoutique;
    }

    public void setIdBoutique(String idBoutique) {
        this.idBoutique = idBoutique;
    }

    public String getTauxReduction() {
        return tauxReduction;
    }

    public void setTauxReduction(String tauxReduction) {
        this.tauxReduction = tauxReduction;
    }

    public String getNomEnseigne() {
        return nomEnseigne;
    }

    public void setNomEnseigne(String nomEnseigne) {
        this.nomEnseigne = nomEnseigne;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    
}

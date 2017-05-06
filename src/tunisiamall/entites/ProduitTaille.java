/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisiamall.entites;


public class ProduitTaille {

    private int idProduit;
    private String taille;
    private int quantite;

    public ProduitTaille(int idProduit, String taille, int quantite) {
        this.idProduit = idProduit;
        this.taille = taille;
        this.quantite = quantite;
    }

    public ProduitTaille() {
    }


    public int getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(int idProduit) {
        this.idProduit = idProduit;
    }

    public String getTaille() {
        return taille;
    }

    public void setTaille(String taille) {
        this.taille = taille;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

}

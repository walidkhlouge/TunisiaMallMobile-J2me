/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisiamall.entites;


public class Commande {
    private int idCommande;
    private String login;
    private int idProduit;
    private String adresseLivraison;

    public Commande() {
    }

    public Commande(int idCommande, String login, int idProduit, String adresseLivraison) {
        this.idCommande = idCommande;
        this.login = login;
        this.idProduit = idProduit;
        this.adresseLivraison = adresseLivraison;
    }

    public String getAdresseLivraison() {
        return adresseLivraison;
    }

    public int getIdCommande() {
        return idCommande;
    }

    public void setIdCommande(int idCommande) {
        this.idCommande = idCommande;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public int getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(int idProduit) {
        this.idProduit = idProduit;
    }

    public void setAdresseLivraison(String adresseLivraison) {
        this.adresseLivraison = adresseLivraison;
    }
    
    
}

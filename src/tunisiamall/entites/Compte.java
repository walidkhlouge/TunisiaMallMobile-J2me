/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisiamall.entites;

import java.util.Date;


    public class Compte{
    private String nom;
    private String prenom;
    private String email;
    private String password;
    private String login;
    private String type;
    private String sexe;
    private String dateNaissance;
    private String etat;
   
    public Compte() {
    }

    public Compte(String nom, String prenom, String email, String password, String login,String type,String sexe,String dateNaissance,String etat) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.password = password;
        this.login = login;
        this.type=type;
        this.sexe=sexe;
        this.dateNaissance=dateNaissance;
        this.etat=etat;
        
    }

    public Compte(String nom, String loginE, String adresse, String prenom) {
        this.nom=nom;
        login = loginE;
        this.email = adresse;
        this.prenom=prenom;
    }

    public String getType() {
        return type;
    }

   
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public String getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(String dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

  
    public void setPassword(String password) {
        this.password = password;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getEmail() {
        return email;
    }

  

    public String getPassword() {
        return password;
    }

    public String getLogin() {
        return login;
    }
    
    public String toString() {
        return "Compte{" + "nom=" + nom + ", prenom=" + prenom + ", email=" + email + ", password=" + password + ", login=" + login + ", type=" + type + ", sexe=" + sexe + ", dateNaissance=" + dateNaissance + ", etat=" + etat + '}';
    }
}


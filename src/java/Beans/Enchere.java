/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import java.io.Serializable;
import java.util.ArrayList;
import javax.annotation.ManagedBean;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;


/**
 *
 * @author asus
 */
@Named(value = "enchere")
@ApplicationScoped
@ManagedBean 
public class Enchere implements Serializable {
    private int idEnchere  ;
    private Produit idProduit ; 
    private String date ;
    private int prix ;
    private ArrayList<Integer> ancEncheresPrix ;
    private ArrayList<String> ancEncheresDate ;
    private ArrayList<Membre> ancEncheresMembre ;

    public void setDate(String date) {
        this.date = date;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public String getDate() {
        return date;
    }

    public int getPrix() {
        return prix;
    }

    public void setId(Produit id) {
        this.idProduit = id;
    }

    public void setIdEnchere(int idEnchere) {
        this.idEnchere = idEnchere;
    }

    public void setIdProduit(Produit idProduit) {
        this.idProduit = idProduit;
    }

    public int getIdEnchere() {
        return idEnchere;
    }

    public Produit getIdProduit() {
        return idProduit;
    }

    public void setAncEncheresPrix(ArrayList<Integer> ancEncheresPrix) {
        this.ancEncheresPrix = ancEncheresPrix;
    }

    public void setAncEncheresDate(ArrayList<String> ancEncheresDate) {
        this.ancEncheresDate = ancEncheresDate;
    }

    public void setAncEncheresMembre(ArrayList<Membre> ancEncheresMembre) {
        this.ancEncheresMembre = ancEncheresMembre;
    }

    public Produit getId() {
        return idProduit;
    }

    public ArrayList<Integer> getAncEncheresPrix() {
        return ancEncheresPrix;
    }

    public ArrayList<String> getAncEncheresDate() {
        return ancEncheresDate;
    }

    public ArrayList<Membre> getAncEncheresMembre() {
        return ancEncheresMembre;
    }

    public Enchere(Produit id, ArrayList<Integer> ancEncheresPrix, ArrayList<String> ancEncheresDate, ArrayList<Membre> ancEncheresMembre) {
        this.idProduit = id;
        this.ancEncheresPrix = ancEncheresPrix;
        this.ancEncheresDate = ancEncheresDate;
        this.ancEncheresMembre = ancEncheresMembre;
    }

    /**
     * Creates a new instance of Enchere
     */
    public Enchere() {
    }
    
}

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
@Named(value = "category")
@ApplicationScoped
@ManagedBean
public class Category  implements Serializable {
    
    String nomCategory;
    ArrayList < Produit > produits ;
    Produit tselectedProduit ;

    public Produit getTselectedProduit() {
        return tselectedProduit;
    }

    public void setTselectedProduit(Produit tselectedProduit) {
        this.tselectedProduit = tselectedProduit;
    }
    

    public void setNomCategory(String nomCategory) {
        this.nomCategory = nomCategory;
    }
    
    public void maj()
    {
        Stock s = new Stock() ;
        s.maj() ; 
    } 

    public void setProduits(ArrayList<Produit> produits) {
        this.produits = produits;
    }
    
    public void addProduit (Produit p)
    {
        this.produits.add(p) ;
    }

    public String getNomCategory() {
        return nomCategory;
    }

    public ArrayList<Produit> getProduits() {
        return produits;
    }

    /**
     * Creates a new instance of Category
     */
    public Category() {
    }
    
}

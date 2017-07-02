/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import cx.Cx;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.ManagedBean;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.enterprise.context.Dependent;

/**
 *
 * @author asus
 */
@Named(value = "stock")
@ApplicationScoped 
@ManagedBean
public class Stock  implements Serializable{
    
   
    private ArrayList <Category> categories ;

    public ArrayList<Category> getCategories() {
        return categories;
    }
    public Category getCategory(String c)
    {
        int i=0 ;
        while(i<= categories.size())
        {
            if (categories.get(i).nomCategory.equals(c))
                return categories.get(i) ;
            else i++ ;
        }
        return (null) ;
    }
    public void setCategories(ArrayList<Category> categories) {
        this.categories = categories;
    }

   

    //Met à jour le stock (tous les produits) et verifie les encheres
    public void maj()
    {
        Cx c = new Cx();
        c.driver();
        c.OpenConnexion();
        //1 - Remplissage liste categories
        
        categories = new ArrayList<Category> ();
        String msg = "init\n";
        //Remplir la liste des categories => nomCateg
        try{
        ResultSet rs= c.selectExec("select distinct category from produit ");
        while (rs.next())
        {   String categ = rs.getString(1) ;
            
            
            //ajouter nouvelle categorie trouvé
            Category ncateg= new Category() ;
            ncateg.setNomCategory(categ);
            ArrayList<Produit> p = new ArrayList<Produit>() ;
            ncateg.setProduits(p);
            categories.add(ncateg) ;
            
            
            
           // msg+=nomCategory.get(nomCategory.size());
//           msg+="Categorie trouvé  :"+categ+"---" ;
        }
        
        //remplissage des Produits de chaque categorie
        int i=0 ;
  
       while (i<=categories.size()+1)
        {
          //Selectionner une categorie
          String  categ =categories.get(i).getNomCategory() ;
//          msg+= "je remplie liste produits pour :"+categ+"--" ;
       ResultSet rs2= c.selectExec("select * from produit where category="+"'"+ categ +"'");
      
       while (rs2.next())
         
      //Remplir la liste "produits" avec cet produit;
       {
        
        Produit p= new Produit() ;
        
       p.setNomProduit(rs2.getString(2));
//       msg+="J'ai trouvé le produit : "+p.getNomProduit()+" de "+categ+" ----"; 

       p.setPrix(rs2.getInt(3));
       p.setDateLimite(rs2.getString(4)); 
       p.setDescription(rs2.getString(5));
       p.setImage(rs2.getString(6)); 
       p.setCategory(categ);
//      msg+= " nomé "+p.getNomProduit()+" --- ";
        
//msg+= "--- on est à la categorie "+categories.get(i).nomCategory + " tawa est on va ajouter le produit"+p.getNomProduit();
//       
//msg+= " --- i = "+i+" ---" ;
        categories.get(i).addProduit(p); 
     //   categories.get(i).setTselectedProduit(p);
        
//        msg+= " Je l'ai ajouté ! -- " ;
            }
        i++; //aller au produits du categorie suivante
        }
       //2- Verification des encheres 
       
        LocalDate localDate = LocalDate.now();
         String d =  DateTimeFormatter.ofPattern("yyy/MM/dd").format(localDate);
         
         String reqA = "SELECT * FROM actions a WHERE a.prix = (SELECT max(b.prix) from actions b where a.nomProduit=b.nomProduit order by nomProduit ) and actions = 'enchere' and date ='"+d+"' ;"  ;
         ResultSet r = c.selectExec(reqA) ;
         
         //Date limite d'une enchere
         while (r.next()) 
         {
            Membre  p = new Membre ( );
            String nomProduit = rs.getString(2) ;
            int prix = rs.getInt(3) ;
            int idMember = rs.getInt("idMember");
           
            p.fund(idMember, prix) ;
            
              String req = "insert into actions (nomProduit , prix , date , idMember  , action ) Values ('"+nomProduit+"',"+prix+",'"+LocalDateTime.now()+"',"+idMember+",'Acheté en Enchere' )" ;
          
              int v=c.updateExec(req);  
             
         }
       
       
       
    }
        catch (Exception e){
        System.out.print(e);
        }
       
    }
    
    

    /**
     * Creates a new instance of Category
     */
    public Stock() {
    }
    
}

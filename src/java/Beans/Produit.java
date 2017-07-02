/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import cx.Cx;
import java.io.Serializable;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.ArrayList;
import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;

/**
 *
 * @author asus
 */
@Named(value = "produit")
@SessionScoped
@ManagedBean
public class Produit implements Serializable{
    
    private String nomProduit;
    private int prix;
    private String dateLimite ;
    private String description ;
    private String image ; 
    private String category ;
    private int idOwner ;
    private int dernierEnchere;

    public void setDernierEnchere(int dernierEnchere) {
        this.dernierEnchere = dernierEnchere;
    }

    public int getDernierEnchere() {
        
        return dernierEnchere;
    }
     private ArrayList<String> ancEncheresDate  ;

    public void setIdOwner(int idOwner) {
        this.idOwner = idOwner;
    }

    public int getIdOwner() {
        return idOwner;
    }

    //constructeur utilité par stock
    public Produit(String nomProduit, int prix, String dateLimite, String description, String image, String category) {
        this.nomProduit = nomProduit;
        this.prix = prix;
        this.dateLimite = dateLimite;
        this.description = description;
        this.image = image;
        this.category = category ;
        
        this.majHisto();
               
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setAncEncheresDate(ArrayList<String> ancEncheresDate) {
        this.ancEncheresDate = ancEncheresDate;
    }

    public String getDescription() {
        return description;
    }

    public String getImage() {
        return image;
    }
    
    public Produit me()
    {
//    {   ArrayList anEnc = new ArrayList<Integer>() ;
//        ArrayList anDt= new ArrayList<String>() ;
        Produit p = new Produit();
        p.setNomProduit(nomProduit);
       p.setCategory(category);
       p.setDateLimite(dateLimite);
       p.setImage(image);
      
        return p ;
    }
     

    public Produit(String nomProduit, int prix, String dateLimite, ArrayList<Integer> ancEncheresPrix, ArrayList<String> ancEncheresDate) {
        this.nomProduit = nomProduit;
        this.prix = prix;
        this.dateLimite = dateLimite;
               this.ancEncheresDate = ancEncheresDate;
    }

    

   
   

    public String getNomProduit() {
        return nomProduit;
    }

    public int getPrix() {
        return prix;
    }

    public String getDateLimite() {
        return dateLimite;
    }

    public void setNomProduit(String nomProduit) {
        this.nomProduit = nomProduit;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public void setDateLimite(String dateLimite) {
        this.dateLimite = dateLimite;
    }
    
    public String add()
    {
    //Ajouter un nouveau produit
       Cx c = new Cx();

             c.driver();
        c.OpenConnexion();
        //Retrouver id du vendeur
        
            FacesContext context = FacesContext.getCurrentInstance(); 
                                    HttpSession session = (HttpSession)   
                                    context.getExternalContext().getSession( false);
                                    
             idOwner= (int) session.getAttribute("membreID");
         String req="INSERT INTO `produit`(`nomProduit` , `prix` , `dateLimite` , `description`, `image` ,category, idOwner) VALUES ("+"'"+nomProduit+"'"+","+"'"+prix+"'"+","+"'"+dateLimite+"'"+","+"'"+description+"'"+","+"'"+image+"'"+",' "+category+"' , '"+idOwner+"')";
        int j=c.updateExec(req);
        System.out.print("baaed");
        
        if (j==0) return "404?faces-redirect=true";
        else return "listeStock?faces-redirect=true";
        
    }
    public String acheter ()
    {
     
              FacesContext context = FacesContext.getCurrentInstance(); 
                                    HttpSession session = (HttpSession)   
                                    context.getExternalContext().getSession( false); 
        
                                    
           int solde = (int)session.getAttribute("solde") ;
           int idMember = (int) session.getAttribute("membreID") ;
           if (prix < solde )
           {      
        Cx c = new Cx();
        c.driver();
        c.OpenConnexion();
        String req="Delete from produit where nomProduit ='"+this.nomProduit+"'" ;
        
        session.setAttribute("solde",solde) ;
       try{
            Membre p=new Membre() ;
        if(idOwner!=0)
        { 
           int x = p.fund(idOwner,prix) ;
               
          if (x==0) return("Probléme de payement au owner!"+idOwner);
          else System.out.print("Membre "+idOwner+"Recu la somme"+prix+"avec sucess");
        }
          int y=p.fund(idMember,-prix) ;
          if (y==0) return("Probléme de payment du somme de votre part!"+idMember);
           else System.out.print("Membre "+idMember+"Payéé la somme"+prix+"avec sucess");
       }
          catch (Exception e){}
           int j=c.updateExec(req);                            
        if (j==0) return("Probléme d'achat!");
        else 
        { 
            //Notifier le vendeur s'il existe
            int v=0;
            if (idOwner != 0)
            {String req3 = "insert into actions (nomProduit , prix , date , idMember  , action ) Values ('"+nomProduit+"',"+prix+",'"+LocalDateTime.now()+"',"+idOwner+",'Vendu' )" ;
//          
         v=c.updateExec(req3);  
            }
        //Notifier l'acheteur
        System.out.print("Id du membre achaiteur est "+idMember);
             String req4 = "insert into actions (nomProduit , prix , date , idMember  , action ) Values ('"+nomProduit+"',"+prix+",'"+LocalDateTime.now()+"',"+idMember+",'Acheté immediatement' )" ;
        int a=c.updateExec(req4) ;
        
        
        if ((v==0 && a ==0) || a==0) return "404?faces-redirect=true";
       
        else return  "member?faces-redirect=true" ;
//        {
//            //GARDER L HISTORIQUE DANS LE COOKIE
//             Cookie cookie = (Cookie) FacesContext.getCurrentInstance().getExternalContext().getRequestCookieMap().get("memberHistory");
//           String histo = cookie.getValue() ;
         //   String  histo = "  Produit "+this.nomProduit+"  Acheté directement avec sucess à "+ LocalDateTime.now() + " . " ;
//            Cookie cookie1 = new Cookie("memberHistory",histo );
          
//            cookie1.setMaxAge(999999999); 
             

        }
        
        } 
           
        else 
               return(prix+" < "+solde+"  Solde insuffisant!!");
    
    }
    
    
    //PostConstruct
     public String init()
     {
         Produit p = new Produit() ;
         p.setNomProduit(nomProduit);
         p.setCategory(category);
         p.setDateLimite(dateLimite);
         p.setDescription(description);
         p.setIdOwner(idOwner);
         p.setImage(image);
         p.setPrix(prix);
         p.setAncEncheresDate(ancEncheresDate);
         
        
       return   "produit?faces-redirect=true" ;
     }
     
     public String redirect(String id) 
     {
          //System.out.print("J ESSAYE DE TE FOURNIR "+id);
           Cx c = new Cx();
        c.driver();
        c.OpenConnexion();
          ResultSet rs2= c.selectExec("select * from produit where nomProduit="+"'"+ id +"'");
          ResultSet rs3=c.selectExec("Select max(prix) from actions where action='enchere' and nomProduit='"+id+"'");
      try  {
       while (rs2.next())
         
      //Remplir la liste "produits" avec cet produit;
       {
       this.setNomProduit(rs2.getString(2));
//       msg+="J'ai trouvé le produit : "+p.getNomProduit()+" de "+categ+" ----"; 

       this.setPrix(rs2.getInt(3));
     
      this.setDateLimite(rs2.getString(4));
       
       this.setDescription(rs2.getString(5));
       this.setImage(rs2.getString(6));
        
      
       
       }
       if (rs3.next())
       { int p = rs3.getInt(1);
       this.setDernierEnchere(p);
       }
               
      }
       catch (Exception e)
               {
               return  "404?faces-redirect=true" ;
               }
      return  "produit?faces-redirect=true" ;
     }
     
     
      public ArrayList<String> getAncEncheresDate() {
        
//          Cx c = new Cx();
//        c.driver();
//        c.OpenConnexion();
//        ArrayList<String> a = new ArrayList<String> () ;
//          ResultSet rs2= c.selectExec("select * from actions where nomProduit="+"'"+ nomProduit +"' and action='enchere' order by prix ;");
//          try{
//          while (rs2.next())
//          {
//              String d = rs2.getString("date" );
//              String p = rs2.getString("prix") ;
//              String ench = "On a proposé la somme"+prix+" à "+prix ;
//              a.add(ench) ;
//          } 
//              }
//          catch (Exception e)
//          {
//              System.out.print("ma jebtesh enchere") ;
//          }
          
          
          
        return ancEncheresDate;
    }
      
       public String enchere ()
               
       {
            Cx c = new Cx();
        c.driver();
        c.OpenConnexion();
       String req = "insert into actions (nomProduit , prix , date , idMember  , action ) Values ('"+nomProduit+"',"+dernierEnchere+",'"+LocalDateTime.now()+"',"+idOwner+",'enchere' )" ;
          
         int v=c.updateExec(req);  
           System.out.print("enregistrement d'enchere "+dernierEnchere);
           
           if  (v==0)  return "404?faces-redirect=true" ;
           else return "produit?faces-redirect=true" ;
           
       }
       
       public void majHisto()
       {
            
          Cx c = new Cx();
        c.driver();
        c.OpenConnexion();
        ArrayList<String> a = new ArrayList<String> () ;
          ResultSet rs2= c.selectExec("select * from actions where nomProduit="+"'"+ nomProduit +"' and action='enchere' order by prix ;");
          try{
          while (rs2.next())
          {
              String d = rs2.getString("date" );
              int p = rs2.getInt("prix") ;
              String ench = "On a proposé la somme :"+p+"$ à :"+d;
              
              a.add(ench) ;
              this.setDernierEnchere(p);
              System.out.print("La derniere enchere"+this.getDernierEnchere());
              
          } 
              }
          catch (Exception e)
          {
              System.out.print("ma jebtesh enchere") ;
          }
          this.setAncEncheresDate(a); 
          
          
       }
      
    
    /**
     * Creates a new instance of Produit
     */
    public Produit() {
    }
    
}

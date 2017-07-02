/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import cx.Cx ;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Map;
import javax.annotation.ManagedBean;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author asus
 */
@Named(value = "membre")
@ApplicationScoped
@ManagedBean
public class Membre implements Serializable {
    
    private int membreID ; 
    private String username ;
    private String password;
    private int solde ; 
    private ArrayList<Integer> Enchere ;
    private Cx cx ; 
    private ArrayList<String> historique ; 

    public void setHistorique(ArrayList<String> historique) {
        this.historique = historique;
    }

    public ArrayList<String> getHistorique() {
        return historique;
    }

    public void setMembreID(int membreID) {
        this.membreID = membreID;
    }
 
    public String fund()
    {
        
         Cx c = new Cx();

             c.driver();
        c.OpenConnexion();
//        System.out.print("9bal");
         String req="update  `membre` set solde= "+solde+" where username='"+username+"' ;";
        int j=c.updateExec(req);
//        System.out.print("baaed");
       if (j==0)  return "404?faces-redirect=true" ;
       else return "member?faces-redirect=true" ;
    }
    
    //Ajouter  l'argent si produit vendue
    public  int fund (int id,int money)
    {   Cx c = new Cx();
       c.driver();
        c.OpenConnexion();
        int s=0;
        try{
        //Recherche le membre
         ResultSet rs= c.selectExec("select solde from membre where MembreID="+id);
         while (rs.next())
             s=rs.getInt("solde");
         
         //ajouter l'argent
         s+=money ;
         solde = s ;
         //valider l'ajout
         String req="update  `membre` set solde = "+solde+" where MembreID ="+id;
        int j=c.updateExec(req);
         return(j) ;
        }
        catch (Exception e)
        {
            System.out.print(e) ;
            return(0) ;
        }
          
    }
    
    //reurns personne with that id
    public void select(int id)
    {
        Cx c = new Cx();
       c.driver();
        c.OpenConnexion();
        int solde=0;
         ResultSet rs= c.selectExec("select * from membre where idMember="+id);
         Membre p = new Membre() ;
         try{
         if(rs.next()){
            this.membreID = rs.getInt(1);
            this.username = username ;
            this.password = password ;
            this.solde = rs.getInt (4) ;
         }
    }
         catch (Exception e){}
         
        
    }

    public void setCx(Cx cx) {
        this.cx = cx;
    }

    public int getMembreID() {
        return membreID;
    }

    public Cx getCx() {
        return cx;
    }
    

    public void setUsername(String username) {
        
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setSolde(int solde) {
        this.solde = solde;
    }

    public void setEnchere(ArrayList<Integer> Enchere) {
        this.Enchere = Enchere;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getSolde() {
        return solde;
    }

    public ArrayList<Integer> getEnchere() {
        return Enchere;
    }

    public Membre(int membreID, String username, String password, int solde, ArrayList<Integer> Enchere) {
        //init cnxion a DB
        Cx c = new Cx();
        c.driver();
        c.OpenConnexion();
        this.cx=c ;
        
        this.username = username;
        this.password = password;
        this.solde = solde;
        this.Enchere = Enchere;
        this.membreID = membreID; 
        
        //Inscription d'u nuvo Membre
//        String req="INSERT INTO `Membre`(`username`, `password` ) VALUES ("+"'"+username+"'"+","+"'"+password+"'"+")";
//        int j=c.updateExec(req);
    }
    
    public String add()
    {
       Cx c = new Cx();

             c.driver();
        c.OpenConnexion();
//        System.out.print("9bal");
         String req="INSERT INTO `membre`(`username`, `password` ) VALUES ("+"'"+username+"'"+","+"'"+password+"'"+")";
        int j=c.updateExec(req);
//        System.out.print("baaed");
       if (j==0)  return "404?faces-redirect=true" ;
               
               
       else {
           FacesContext context = FacesContext.getCurrentInstance(); 
                                    HttpSession session = (HttpSession)   
                                    context.getExternalContext().getSession( false); 
           

           session.setAttribute("solde",solde) ;
           return"login?faces-redirect=true"  ;
       }
       
    }
    
    public String test()
    {   
        

        
        return("ca marche!") ;
    }
    
    public String login()
    {
         Cx c = new Cx();
        c.driver();
        c.OpenConnexion();
       ResultSet rs= c.selectExec("select * from membre where username="+"'"+username+"'"+"and password="+"'"+password+"'");
        try{
       if(rs.next()){
            this.membreID = rs.getInt(1);
            this.username = username ;
            this.password = password ;
            this.solde = rs.getInt (4) ;
            this.historique= new ArrayList() ;
            String b = "bienvenu: Login à "+LocalDateTime.now();
            this.updateHistorique();
            historique.add(b) ;
           
            
              FacesContext context = FacesContext.getCurrentInstance(); 
                                    HttpSession session = (HttpSession)   
              
                                            context.getExternalContext().getSession( false); 
            //Setting up session                        
           String msg="connected";
	    session.setAttribute("username",username);
            session.setAttribute("membreID",membreID);
            session.setAttribute("msg",msg);
            session.setAttribute("password",password);
            session.setAttribute("solde",solde);
            
            
         
            
            return "listeStock?faces-redirect=true";
//            response.sendRedirect("listeMsg.jsp");
        }
        }
        catch (Exception e){} 
        return "404?faces-redirect=true" ;
    }
    
    public boolean connected()
    {try{
         
              FacesContext context = FacesContext.getCurrentInstance(); 
                                    HttpSession session = (HttpSession)   
                                    context.getExternalContext().getSession( false);
           String msg=  (String) session.getAttribute("msg") ;
           return (!msg.equals("connected")) ; 
    }
    catch (NullPointerException e)
    {
        return true ;
    }
    }
    //deactive la session si non connecté
    public void wrong()
    {
         //Logs Out
              FacesContext context = FacesContext.getCurrentInstance(); 
                                    HttpSession session = (HttpSession)   
                                    context.getExternalContext().getSession( false);
                   session.invalidate();
    }
     public void updateHistorique()
             
     {   //  ArrayList<String> h = new ArrayList<String>()
        Cx c = new Cx();
        c.driver();
        c.OpenConnexion();
        ArrayList <String> h = this.getHistorique() ;
        if (this.historique==null)
        {
                 h = new ArrayList<String>() ;
        }
        
                
        String req="Select * from actions where idMember ="+this.membreID  ;
        ResultSet rs= c.selectExec(req);
        try{
            while (rs.next())
        {
            String nomProduit = rs.getString(2) ;
            int prix = rs.getInt(3) ;
            String date = rs.getString(4) ;
            String action = rs.getString(6) ;
            
            String notif = "Le produit '"+nomProduit+ "' a été "+ action + " avec la somme :"+prix+"$ à la date: "+date ;
          //Ne pas ajouter 2 fois
            if (h.contains(notif)==false)
            h.add(notif);
        }
            this.setHistorique(h);
        }
        catch (Exception e){} ;
     }
     
     
     public boolean mcookie()
             
     {
            //Checking for the cookie 
      Cookie cookie = (Cookie) FacesContext.getCurrentInstance().getExternalContext().getRequestCookieMap().get("visit");

        if (cookie == null)
        {
            String nameCookie="visit" ;
            String cookieval = "not connected " ;
            Cookie cookie1 = new Cookie(nameCookie,cookieval );
           cookie1.setPath("/");
            cookie1.setMaxAge(999999999); // Expire time. -1 = by end of current session, 0 = immediately expire it, otherwise just the lifetime in seconds.
            
          //  System.out.print("I HAD TO CHANGE THE COOKIE");
             FacesContext context = FacesContext.getCurrentInstance();
            HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
    response.addCookie(cookie1);
        return true ;
    
        }
        else
        {
            String v = cookie.getValue() ;
            System.out.println("cookie is "+v);
            boolean test = v.equals("connected") ;
          cookie.setValue("connected");
            return(test ) ;
        }
       
         
     }
     
     //sets cookie to "connected"
     public String acc()
     {
          String nameCookie="visit" ;
            String cookieval = "connected " ;
            Cookie cookie1 = new Cookie(nameCookie,cookieval );
           cookie1.setPath("/");
            cookie1.setMaxAge(999999999); // Expire time. -1 = by end of current session, 0 = immediately expire it, otherwise just the lifetime in seconds.
            
          
             FacesContext context = FacesContext.getCurrentInstance();
            HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
    response.addCookie(cookie1);
      
         
        return "listeStock?faces-redirect=true" ;
     }
     
    
    /**
     * Creates a new instance of Membre
     */
    public Membre() {
    }
    
}

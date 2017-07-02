/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cx;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.http.HttpServlet;

/**
 *
 * @author asus
 */
public class Cx extends HttpServlet {

   
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/eBay?zeroDateTimeBehavior=convertToNull";
    static final String USER = "root";
    static final String PASS = "";
    static Connection con = null;
    static ResultSet rs = null;
    static Statement stmt = null;

    public Cx() {
    }
    public boolean driver() {
        try {
            Class.forName(JDBC_DRIVER);
            System.out.println("Connexion à la base des données...");
            return true;
        } catch (Exception e) {
            System.out.println("Erreur lors du chargement du pilote :" + e.getMessage());
            return false;
        }
    }
     public boolean OpenConnexion() {
        try {
            con = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Connexion avec succès...");
            return true;

        } catch (Exception e) {

            System.out.println("Echec de l'ouverture de la connexion :" + e.getMessage());

            return false;
        }
    }

    public boolean closeConnection() {
        try {
            con.close();
            return true;
        } catch (Exception e) {
            System.out.println("Echec de la fermeture de la connexion :" + e.getMessage());
            return false;
        }
    }

    public ResultSet selectExec(String sql) {
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);
        } catch (Exception e) {
            System.out.println("Echec de l'exécution de la requête sql :" + e.getMessage());
        }
        return rs;
    }

   

    public int updateExec(String sql) {
        int i = 0;
        try {
            con.setAutoCommit(false);
            stmt = con.createStatement();
            i = stmt.executeUpdate(sql);
            con.commit();
        } catch (Exception e) {
            System.out.println("Echec de l'exécution de la requête sql :" + e.getMessage());
        }
        return i;
    }

    public boolean closeResultSet() {
        try {
            rs.close();
            return true;
        } catch (Exception e) {
            System.out.println("Echec de la fermeture de l'objet ResultSet :" + e.getMessage());
            return false;
        }
    }

    public boolean closeStatement() {
        try {
            stmt.close();
            return true;
        } catch (Exception e) {
            System.out.println("Echec de la fermeture de l'objet Statement :" + e.getMessage());
            return false;
        }
    }
    
     public String  here()
    {return("I'm here!");
    }
    
}
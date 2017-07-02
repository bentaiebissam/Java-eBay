/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Validator;

import Beans.* ;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author asus
 */
@FacesValidator("enchereValidator")
public class EnchereValidator implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    
        
        Produit p =(Produit) component.getAttributes().get("produit");
        int e=0 ;
       if (value instanceof String  )
       {
        String ch = (String)value ;
      e = Integer.parseInt(ch);
       }
       else
           e = (int) value ;
       
       
       if (e<p.getDernierEnchere())
           throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Enchaire insuffisant", "insuffisant!"));
    if (e>p.getPrix())
           throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Enchaire insuffisant", "Enchaire subsantif! \n Vous pouver tout simplement l'acheter directement!"));
    
    
    
    }
    
}
